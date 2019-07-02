package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbSeller;
import entity.PageResult;

/**
 * 商家接口
 */
public interface SellerService {

    public void add(TbSeller tbSeller);

    public PageResult search(TbSeller tbSeller, int pageNum, int pageSize);

    public TbSeller findSellerById(String id);

    public void updateStatus(String sellerId,String status);
}
