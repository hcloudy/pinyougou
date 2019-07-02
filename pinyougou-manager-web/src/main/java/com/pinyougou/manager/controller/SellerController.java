package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;
import entity.PageResult;
import entity.PygResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Reference
    private SellerService sellerService;


    /**
     * 分页、带条件查询数据。
     * @param tbSeller
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbSeller tbSeller,int page,int size) {
        PageResult result = sellerService.search(tbSeller, page, size);
        return result;
    }

    /**
     * 根据id查询商家数据
     * @param sellerId
     * @return
     */
    @RequestMapping("/findSellerById")
    public TbSeller findSellerById(@RequestParam(value = "sellerId") String sellerId) {
        return sellerService.findSellerById(sellerId);
    }

    @RequestMapping("/updateStatus")
    public PygResult updateStatus(String sellerId,String status) {
        try {
            sellerService.updateStatus(sellerId,status);
            return new PygResult(true,"操作成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new PygResult(false,"操作失败");
        }
    }

}
