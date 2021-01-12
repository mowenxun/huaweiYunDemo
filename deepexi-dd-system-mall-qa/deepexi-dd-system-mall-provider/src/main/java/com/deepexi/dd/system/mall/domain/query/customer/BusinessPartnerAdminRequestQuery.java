package com.deepexi.dd.system.mall.domain.query.customer;

import domain.dto.BaseRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName BusinessPartnerAdminRequestQuery
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-14
 * @Version 1.0
 **/
@Data
@ApiModel
public class BusinessPartnerAdminRequestQuery extends BaseRequestDTO {

    private static final long serialVersionUID = -876254082688912557L;

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
