package com.deepexi.dd.system.mall.domain.query.customer;

import domain.query.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName BusinessPartnerAdminRequestQueryPage
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-18
 * @Version 1.0
 **/
@Data
@ApiModel
public class BusinessPartnerAdminRequestQueryPage extends BasePage {

    private static final long serialVersionUID = 4979819974564260230L;

    @ApiModelProperty("业务伙伴名称")
    private String name;

    @ApiModelProperty("信用代码")
    private String creditCode;;

    @ApiModelProperty("业务后边编码")
    private String code;

    @ApiModelProperty(value = "业务伙伴组织")
    private Long orgId;

    @ApiModelProperty(value = "业务伙伴id")
    private Long id;

    @ApiModelProperty(value = "业务伙伴ids")
    private List<Long> ids;
}
