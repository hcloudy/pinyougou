package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.group.pojo.Goods;
import com.pinyougou.sellergoods.service.GoodService;
import entity.PygResult;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/good")
public class GoodController {

    @Reference
    private GoodService goodService;

    @RequestMapping("/save")
    public PygResult addGood(@RequestBody Goods goods) {
        String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
        goods.getTbGoods().setSellerId(sellerId);
        try{
            goodService.add(goods);
            return new PygResult(true,"添加商品成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new PygResult(false,"添加商品失败");
        }
    }
}
