package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.sellergoods.service.ItemCatService;
import entity.PygResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("itemCat")
public class ItemCatController {

    @Reference
    private ItemCatService itemCatService;

    @RequestMapping("/findByParentId")
    public List<TbItemCat> findByParentId(Long parentId) {
        return itemCatService.findByParentId(parentId);
    }

    @RequestMapping("/add")
    public PygResult add(@RequestBody TbItemCat tbItemCat) {
        try {
            itemCatService.insert(tbItemCat);
            return new PygResult(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new PygResult(false,"添加失败");
        }
    }

    @RequestMapping("/update")
    public PygResult update(@RequestBody TbItemCat tbItemCat) {
        try {
            itemCatService.update(tbItemCat);
            return new PygResult(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new PygResult(false,"修改失败");
        }
    }

    @RequestMapping("/findItemCat")
    public TbItemCat findItemCatById(long id) {
        return itemCatService.findById(id);
    }
}
