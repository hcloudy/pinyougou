package com.pinyougou.sellergoods.service;

import com.pinyougou.group.pojo.Specification;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationOption;
import entity.PageResult;
import entity.PygResult;

import java.util.List;
import java.util.Map;

public interface SpecificationService {

    /**
     * 展示规格列表带分页
     * @param page
     * @param size
     * @return
     */
    public PageResult search(TbSpecification tbSpecification,int page, int size);

    /**
     * 添加规格包含规格选项
     * @param specification
     * @return
     */
    public void add(Specification specification);

    /**
     * 根据id返回规格项和规格明细
     * @param id
     * @return
     */
    public Specification findById(Long id);

    /**
     * 更新商品
     * @param specification
     */
    public void update(Specification specification);

    /**
     * 删除数据
     * @param ids
     */
    public void delete(Long[] ids);

    /**
     * 规格下拉框列表
     * @return
     */
    public List<Map> selectSpecificationLists();
}
