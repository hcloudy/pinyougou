package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbTypeTemplate;
import entity.PageResult;
import entity.PygResult;

import java.security.acl.LastOwnerException;
import java.util.List;
import java.util.Map;

public interface TypeTemplateService {

    /**
     * 展示模板列表
     * @param tbTypeTemplate
     * @param page
     * @param size
     * @return
     */
    public PageResult search(TbTypeTemplate tbTypeTemplate, int page, int size);

    /**
     * 添加模板
     * @param tbTypeTemplate
     * @return
     */
    public void add(TbTypeTemplate tbTypeTemplate);

    /**
     * 根据id读取模板信息
     * @param id
     * @return
     */
    public TbTypeTemplate findById(long id);

    /**
     * 更新模板
     * @param tbTypeTemplate
     */
    public void update(TbTypeTemplate tbTypeTemplate);

    /**
     * 删除或者批量删除
     * @param ids
     */
    public void delete(Long[] ids);

    /**
     * 获取类型模板下拉列表
     * @return
     */
    public List<Map> typeTemplateOptionList();
}
