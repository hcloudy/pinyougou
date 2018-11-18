package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.group.pojo.Specification;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.sellergoods.service.SpecificationService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class SpecificationImpl implements SpecificationService{

    @Autowired
    private TbSpecificationMapper tbSpecificationMapper;
    @Autowired
    private TbSpecificationOptionMapper tbSpecificationOptionMapper;

    /**
     * 可带查询条件并分页展示数据
     * @param tbSpecification
     * @param page
     * @param size
     * @return
     */
    public PageResult search(TbSpecification tbSpecification, int page, int size) {
        PageHelper.startPage(page,size);
        TbSpecificationExample example = new TbSpecificationExample();
        TbSpecificationExample.Criteria criteria = example.createCriteria();
        if(tbSpecification != null) {
//            if(tbSpecification.getSpecName().length() > 0 && tbSpecification.getSpecName() != null) {
            if(tbSpecification.getSpecName() != null && tbSpecification.getSpecName().length() > 0) {
                criteria.andSpecNameLike("%"+tbSpecification.getSpecName()+"%");
            }
        }
        Page<TbSpecification> pa = (Page<TbSpecification>) tbSpecificationMapper.selectByExample(example);
        return new PageResult(pa.getTotal(),pa.getResult());
    }

    /**
     * 添加规格参数和规格参数选项
     * @param specification
     */
    public void add(Specification specification) {
        //插入规格名称表
        TbSpecification tbSpecification = specification.getSpecification();
        tbSpecificationMapper.insert(tbSpecification);
        //循环插入规格属性表，并设置规格名称表的id
        List<TbSpecificationOption> specificationOptionList = specification.getSpecificationOptionList();
        for (TbSpecificationOption option : specificationOptionList) {
            option.setSpecId(tbSpecification.getId());
            tbSpecificationOptionMapper.insert(option);
        }
    }

    /**
     * 根据规格id查询规格项和规格明细
     * @param id
     * @return
     */
    @Override
    public Specification findById(Long id) {
        Specification specification = new Specification();
        //设置规格项
        TbSpecification tbSpecification = tbSpecificationMapper.selectByPrimaryKey(id);
        specification.setSpecification(tbSpecification);
        //设置规格明细
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);
        List<TbSpecificationOption> tbSpecificationOptions = tbSpecificationOptionMapper.selectByExample(example);
        specification.setSpecificationOptionList(tbSpecificationOptions);
        return specification;
    }

    @Override
    public void update(Specification specification) {
        //插入规格名称表
        TbSpecification tbSpecification = specification.getSpecification();
        tbSpecificationMapper.updateByPrimaryKey(tbSpecification);

        //插入规格属性前先删除原来的属性
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(tbSpecification.getId());
        tbSpecificationOptionMapper.deleteByExample(example);

        //循环插入规格属性表，并设置规格名称表的id
        List<TbSpecificationOption> specificationOptionList = specification.getSpecificationOptionList();
        for (TbSpecificationOption option : specificationOptionList) {
            option.setSpecId(tbSpecification.getId());
            tbSpecificationOptionMapper.insert(option);
        }
    }

    @Override
    public void delete(Long[] ids) {
        for(Long id : ids) {
            //删除规格项
            tbSpecificationMapper.deleteByPrimaryKey(id);
            //删除规格项属性
            TbSpecificationOptionExample example = new TbSpecificationOptionExample();
            TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(id);
            tbSpecificationOptionMapper.deleteByExample(example);

        }
    }

    @Override
    public List<Map> selectSpecificationLists() {
        return tbSpecificationMapper.selectSpecificationLists();
    }
}
