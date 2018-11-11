package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbTypeTemplate;
import entity.PageResult;

public interface TypeTemplateService {

    public PageResult search(TbTypeTemplate tbTypeTemplate, int page, int size);
}
