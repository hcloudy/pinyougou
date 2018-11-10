package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import entity.PageResult;
import entity.PygResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 品牌管理的控制层
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    @RequestMapping("/findAll")
    public List<TbBrand> findAll() {
        return brandService.findAll();
    }

    @RequestMapping("/findPage")
    public PageResult findPage(int page,int size) {
        return brandService.findPage(page,size);
    }

    @RequestMapping("/add")
    public PygResult add(@RequestBody TbBrand tbBrand) {
        try{
            brandService.add(tbBrand);
            return new PygResult(true, "添加成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new PygResult(false, "添加失败");
        }
    }

    @RequestMapping("/findById")
    public TbBrand findById(long id) {
        return brandService.findById(id);
    }

    @RequestMapping("/update")
    public PygResult update(@RequestBody TbBrand tbBrand) {
        try{
            brandService.update(tbBrand);
            return new PygResult(true, "修改成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new PygResult(false, "修改失败");
        }
    }

    @RequestMapping("/delete")
    public PygResult delete(Long[] ids) {
        try{
            brandService.delete(ids);
            return new PygResult(true, "删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new PygResult(false, "删除失败");
        }
    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody TbBrand tbBrand,int page,int size) {
        PageResult result = brandService.search(tbBrand, page, size);
        return result;
    }
}
