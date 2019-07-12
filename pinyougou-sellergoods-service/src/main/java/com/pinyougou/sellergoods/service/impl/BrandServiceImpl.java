package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.sellergoods.service.BrandService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper tbBrandMapper;

    public List<TbBrand> findAll() {
        return tbBrandMapper.selectByExample(null);
    }

    //品牌列表
    public PageResult findPage(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbBrand> page = (Page<TbBrand>) tbBrandMapper.selectByExample(null);
        return new PageResult(page.getTotal(),page.getResult());
    }

    //品牌添加
    public void add(TbBrand tbBrand) {
        tbBrandMapper.insert(tbBrand);
    }

    public TbBrand findById(long id) {
        return tbBrandMapper.selectByPrimaryKey(id);
    }


    public void update(TbBrand tbBrand) {
        tbBrandMapper.updateByPrimaryKey(tbBrand);
    }

    public void delete(Long[] ids) {
        for(Long id : ids) {
            tbBrandMapper.deleteByPrimaryKey(id);
        }
    }

    //品牌查询
    public PageResult search(TbBrand tbBrand, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbBrandExample example = new TbBrandExample();
        TbBrandExample.Criteria criteria = example.createCriteria();
        if(tbBrand != null) {
            if(tbBrand.getName() != null && tbBrand.getName().length() > 0) {
                criteria.andNameLike("%"+tbBrand.getName()+"%");
            }
            if(tbBrand.getFirstChar() != null && tbBrand.getFirstChar().length() > 0) {
                criteria.andFirstCharLike("%"+tbBrand.getFirstChar()+"%");
            }
        }
        Page<TbBrand> page = (Page<TbBrand>) tbBrandMapper.selectByExample(example);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public List<Map> brandOptionList() {
        List<Map> maps = tbBrandMapper.brandOptionList();
        return maps;
    }
}
