package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.pinyougou.group.pojo.Goods;
import com.pinyougou.mapper.*;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private TbGoodsMapper tbGoodsMapper;
    @Autowired
    private TbGoodsDescMapper tbGoodsDescMapper;
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Autowired
    private TbBrandMapper tbBrandMapper;
    @Autowired
    private TbSellerMapper tbSellerMapper;
    @Override
    public void add(Goods goods) {
        goods.getTbGoods().setAuditStatus("0");//商品的状态 0 未审核
        tbGoodsMapper.insert(goods.getTbGoods());
        //将商品基本表id给商品扩展表
        goods.getTbGoodsDesc().setGoodsId(goods.getTbGoods().getId());
        tbGoodsDescMapper.insert(goods.getTbGoodsDesc());

        List<TbItem> items = goods.getTbItems();
        if("1".equals(goods.getTbGoods().getIsEnableSpec())) {
            for (TbItem item : items) {
                //标题
                String title = goods.getTbGoods().getGoodsName();
                Map<String,Object> map = JSON.parseObject(item.getSpec());
                for (String key : map.keySet()) {
                    title += " " + map.get(key);
                }
                item.setTitle(title);
                //图片：把第一个图片取出来存到tb_item表
                List<Map> list = JSON.parseArray(goods.getTbGoodsDesc().getItemImages(), Map.class);
                if(list.size() > 0) {
                    item.setImage((String) list.get(0).get("url"));
                }
                setItem(item,goods);
            }
        }else {//没有启用规则
            TbItem item = new TbItem();
            item.setTitle(goods.getTbGoods().getGoodsName());//标题
            item.setPrice(goods.getTbGoods().getPrice());//价格
            item.setNum(9999);//库存数量
            item.setStatus("1");//状态
            item.setSpec("{}");
            setItem(item,goods);
        }

    }

    private void setItem(TbItem item,Goods goods) {
        //三级分类
        item.setCategoryid(goods.getTbGoods().getCategory3Id());
        item.setStatus("1");//状态
        item.setCreateTime(new Date());
        item.setUpdateTime(new Date());
        item.setGoodsId(goods.getTbGoods().getId());
        item.setSellerId(goods.getTbGoods().getSellerId());
        //分类名称
        TbItemCat itemCat = tbItemCatMapper.selectByPrimaryKey(goods.getTbGoods().getCategory3Id());
        item.setCategory(itemCat.getName());
        //品牌名称
        TbBrand tbBrand = tbBrandMapper.selectByPrimaryKey(goods.getTbGoods().getBrandId());
        item.setBrand(tbBrand.getName());
        //商家名称(店铺名称)
        TbSeller seller = tbSellerMapper.selectByPrimaryKey(goods.getTbGoods().getSellerId());
        item.setSeller(seller.getNickName());
        tbItemMapper.insert(item);
    }
}
