package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;
import entity.PygResult;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Reference
    private SellerService sellerService;

    @RequestMapping("/add")
    public PygResult save(@RequestBody TbSeller tbSeller) {
        //使用Bycript对密码进行加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(tbSeller.getPassword());
        tbSeller.setPassword(encode);
        try{
            sellerService.add(tbSeller);
            return new PygResult(true,"添加商家成功");
        }catch (Exception e){
            e.printStackTrace();
            return new PygResult(false,"添加商家失败");
        }
    }
}
