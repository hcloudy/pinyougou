package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.group.pojo.Goods;
import com.pinyougou.mapper.TbGoodsDescMapper;
import com.pinyougou.mapper.TbGoodsMapper;
import com.pinyougou.sellergoods.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private TbGoodsMapper tbGoodsMapper;
    @Autowired
    private TbGoodsDescMapper tbGoodsDescMapper;
    @Override
    public void add(Goods goods) {
        goods.getTbGoods().setAuditStatus("0");//商品的状态 0 未审核
        tbGoodsMapper.insert(goods.getTbGoods());
        //将商品基本表id给商品扩展表
        goods.getTbGoodsDesc().setGoodsId(goods.getTbGoods().getId());
        tbGoodsDescMapper.insert(goods.getTbGoodsDesc());
    }
}
