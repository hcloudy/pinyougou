package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.group.pojo.Specification;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.sellergoods.service.SpecificationService;
import entity.PageResult;
import entity.PygResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 规格管理的控制层
 */
@RestController
@RequestMapping("/specification")
public class SpecificationController {

    @Reference
    private SpecificationService specificationService;

    /**
     * 展示规格列表数据
     * @param tbSpecification
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbSpecification tbSpecification,int page,int size) {
        PageResult result = specificationService.search(tbSpecification, page, size);
        return result;
    }

    /**
     * 添加规格项和规格明细
     * @param specification
     * @return
     */
    @RequestMapping("/add")
    public PygResult add(@RequestBody Specification specification) {
        try{
            specificationService.add(specification);
            return new PygResult(true,"添加成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new PygResult(false,"添加失败");
        }
    }

    /**
     * 根据id查找规格项和规格属性
     * @param id
     * @return
     */
    @RequestMapping("/findSpecById")
    public Specification findSpecById(Long id) {
        Specification specification = specificationService.findById(id);
        return specification;
    }

    /**
     * 更新规格项和规格属性
     * @param specification
     * @return
     */
    @RequestMapping("/update")
    public PygResult update(@RequestBody Specification specification) {
        try{
            specificationService.update(specification);
            return new PygResult(true,"更新成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new PygResult(false,"更新失败");
        }
    }

    @RequestMapping("/delete")
    public PygResult delete(Long[] ids) {
        try{
            specificationService.delete(ids);
            return new PygResult(true, "删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new PygResult(false, "删除失败");
        }
    }

    /**
     * 获取规格下拉列表
     * @return
     */
    @RequestMapping("/selectSpecificationLists")
    public List<Map> selectSpecificationLists() {
        return specificationService.selectSpecificationLists();
    }

}
