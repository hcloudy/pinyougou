package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbTypeTemplateMapper;
import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.pojo.TbTypeTemplateExample;
import com.pinyougou.sellergoods.service.TypeTemplateService;
import entity.PageResult;
import entity.PygResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {

    @Autowired
    TbTypeTemplateMapper tbTypeTemplateMapper;

    /**
     * 展示首页数据,不带条件或者带条件
     * @param tbTypeTemplate
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult search(TbTypeTemplate tbTypeTemplate, int page, int size) {
        PageHelper.startPage(page,size);
        TbTypeTemplateExample example = new TbTypeTemplateExample();
        TbTypeTemplateExample.Criteria criteria = example.createCriteria();
        if(tbTypeTemplate != null){
            if(tbTypeTemplate.getName() != null && tbTypeTemplate.getName().length() > 0) {
                criteria.andNameLike("%" + tbTypeTemplate.getName() + "%");
            }
        }
        Page<TbTypeTemplate> pa = (Page<TbTypeTemplate>) tbTypeTemplateMapper.selectByExample(example);
        return new PageResult(pa.getTotal(),pa.getResult());
    }

    /**
     * 添加模板
     * @param tbTypeTemplate
     */
    @Override
    public void add(TbTypeTemplate tbTypeTemplate) {
        tbTypeTemplateMapper.insert(tbTypeTemplate);
    }

    @Override
    public TbTypeTemplate findById(long id) {
        return tbTypeTemplateMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(TbTypeTemplate tbTypeTemplate) {
        tbTypeTemplateMapper.updateByPrimaryKey(tbTypeTemplate);
    }

    @Override
    public void delete(Long[] ids) {
        for(Long id : ids) {
            tbTypeTemplateMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public List<Map> typeTemplateOptionList() {
        return tbTypeTemplateMapper.typeTemplateOptionList();
    }
}
