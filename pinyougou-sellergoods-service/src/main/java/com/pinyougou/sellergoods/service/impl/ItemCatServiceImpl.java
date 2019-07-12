package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.mapper.TbItemCatMapper;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.pojo.TbItemCatExample;
import com.pinyougou.sellergoods.service.ItemCatService;
import entity.PygResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<TbItemCat> findByParentId(Long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        return itemCatMapper.selectByExample(example);
    }

    @Override
    public void insert(TbItemCat tbItemCat) {
       itemCatMapper.insert(tbItemCat);
    }

    @Override
    public void update(TbItemCat tbItemCat) {
        itemCatMapper.updateByPrimaryKey(tbItemCat);
    }

    @Override
    public TbItemCat findById(long id) {
        return itemCatMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TbItemCat> findAll() {
        List<TbItemCat> list = itemCatMapper.selectByExample(null);
        return list;
    }


}
