package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.sellergoods.service.TypeTemplateService;
import entity.PageResult;
import entity.PygResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {
    @Reference
    private TypeTemplateService typeTemplateService;

    /**
     * 展示首页数据
     * @param tbTypeTemplate
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbTypeTemplate tbTypeTemplate,int page,int size) {
        PageResult search = typeTemplateService.search(tbTypeTemplate, page, size);
        return search;
    }

    /**
     * 添加模板
     * @param tbTypeTemplate
     * @return
     */
    @RequestMapping("/add")
    public PygResult add(@RequestBody TbTypeTemplate tbTypeTemplate) {
        try {
            typeTemplateService.add(tbTypeTemplate);
            return new PygResult(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new PygResult(false,"添加失败");
        }
    }

    @RequestMapping("/findTypeTemplateById")
    public TbTypeTemplate findTypeTemplateById(long id) {
        TbTypeTemplate tbTypeTemplate = typeTemplateService.findById(id);
        return tbTypeTemplate;
    }

    @RequestMapping("/update")
    public PygResult update(@RequestBody TbTypeTemplate tbTypeTemplate) {
        try{
            typeTemplateService.update(tbTypeTemplate);
            return new PygResult(true,"更新成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new PygResult(false,"更新失败");
        }
    }

    @RequestMapping("/delete")
    public PygResult delete(Long[] ids) {
        try{
            typeTemplateService.delete(ids);
            return new PygResult(true,"删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new PygResult(false,"删除失败");
        }
    }
}
