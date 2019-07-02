package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSellerMapper;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.pojo.TbSellerExample;
import com.pinyougou.sellergoods.service.SellerService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private TbSellerMapper tbSellerMapper;

    //添加商家信息
    @Override
    public void add(TbSeller tbSeller) {
        //0 未审核 1 审核通过 2 审核不通过 3 关闭
        tbSeller.setStatus("0");
        tbSeller.setCreateTime(new Date());
        tbSellerMapper.insert(tbSeller);
    }

    //查询商家列表
    @Override
    public PageResult search(TbSeller tbSeller, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        TbSellerExample example = new TbSellerExample();
        TbSellerExample.Criteria criteria = example.createCriteria();
        if(null != tbSeller) {
            if(tbSeller.getName() != null && tbSeller.getName().length() > 0) {
                criteria.andNameLike("%"+tbSeller.getName()+"%");
            }
            if(tbSeller.getNickName() != null && tbSeller.getNickName().length() > 0) {
                criteria.andNickNameLike("%" + tbSeller.getNickName() + "%");
            }
            if(tbSeller.getStatus() != null && tbSeller.getStatus().length() > 0) {
                criteria.andStatusEqualTo(tbSeller.getStatus());
            }
        }
        Page<TbSeller> page = (Page<TbSeller>) tbSellerMapper.selectByExample(example);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public TbSeller findSellerById(String sellerId) {
        TbSeller tbSeller = tbSellerMapper.selectByPrimaryKey(sellerId);
        return tbSeller;
    }

    @Override
    public void updateStatus(String sellerId, String status) {
        TbSeller tbSeller = tbSellerMapper.selectByPrimaryKey(sellerId);
        tbSeller.setStatus(status);
        tbSellerMapper.updateByPrimaryKey(tbSeller);
    }
}
