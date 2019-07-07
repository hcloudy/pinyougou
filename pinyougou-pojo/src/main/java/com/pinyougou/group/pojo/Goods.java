package com.pinyougou.group.pojo;

import com.pinyougou.pojo.TbGoods;
import com.pinyougou.pojo.TbGoodsDesc;
import com.pinyougou.pojo.TbItem;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品组合类
 */
@Data
public class Goods implements Serializable {
    private TbGoods tbGoods;//商品基本spu信息
    private TbGoodsDesc tbGoodsDesc;//spu
    private List<TbItem> tbItems;//商品sku列表
}
