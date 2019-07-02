package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbItemCat;
import entity.PygResult;

import java.util.List;
import java.util.Map;

/**
 * itemCat分类表
 */
public interface ItemCatService {

    public List<TbItemCat> findByParentId(Long parentId);

    //新增分类
    public void insert(TbItemCat tbItemCat);

    public void update(TbItemCat tbItemCat);

    public TbItemCat findById(long id);

}
