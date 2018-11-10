package com.pinyougou.group.pojo;

import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationOption;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 规格参数名称和规格属性组合pojo
 */
@Data
public class Specification implements Serializable {

    private TbSpecification specification;

    private List<TbSpecificationOption> specificationOptionList;
}
