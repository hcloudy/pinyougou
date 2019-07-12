package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.container.page.Page;
import com.pinyougou.group.pojo.Goods;
import com.pinyougou.pojo.TbGoods;
import com.pinyougou.sellergoods.service.GoodService;
import entity.PageResult;
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

    //添加商品
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

    //商品列表展示,按照商家名称
    @RequestMapping("/findPage")
    public PageResult findPage(int pageNum,int pageSize) {
        String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
        PageResult result = goodService.findPage(sellerId,pageNum, pageSize);
        return result;
    }
    //根据条件查询
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbGoods goods, int page, int size) {
        String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
        goods.setSellerId(sellerId);
        PageResult result = goodService.search(goods, page, size);
        return result;
    }


}
