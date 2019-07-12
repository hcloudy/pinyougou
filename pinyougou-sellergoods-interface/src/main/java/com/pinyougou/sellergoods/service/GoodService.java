package com.pinyougou.sellergoods.service;

import com.pinyougou.group.pojo.Goods;
import com.pinyougou.pojo.TbGoods;
import entity.PageResult;

import java.util.List;

public interface GoodService {
    /**
     * 商品添加
     * @param goods
     */
    public void add(Goods goods);
    //商品列表展示
    public PageResult findPage(String selleId,int pageNum,int pageSize);
    //查询商品列表
    public PageResult search(TbGoods goods,int pageNum,int pageSize);

}
