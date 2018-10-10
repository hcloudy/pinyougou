package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import entity.PageResult;
import entity.PygResult;

import java.util.List;

public interface BrandService {

    public List<TbBrand> findAll();

    /**
     * 品牌列表页面展示，带分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult findPage(int pageNum,int pageSize);

    /**
     * 品牌添加
     * @param tbBrand
     * @return
     */
    public void add(TbBrand tbBrand);

    /**
     * 根据id查询品牌
     * @param id
     * @return
     */
    public TbBrand findById(long id);

    /**
     * 更新品牌
     * @param tbBrand
     */
    public void update(TbBrand tbBrand);

    /**
     * 删除品牌功能
     */
    public void delete(Long[] ids);

    /**
     * 根据输入品牌名称和首字母进行模糊查询品牌功能
     * @param tbBrand
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult search(TbBrand tbBrand,int pageNum,int pageSize);
}
