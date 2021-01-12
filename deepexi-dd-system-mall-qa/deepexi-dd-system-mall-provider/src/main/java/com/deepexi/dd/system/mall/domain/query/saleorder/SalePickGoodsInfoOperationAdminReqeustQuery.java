package com.deepexi.dd.system.mall.domain.query.saleorder;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class SalePickGoodsInfoOperationAdminReqeustQuery extends AbstractObject {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("页码")
    private Integer page = 1;
    @ApiModelProperty("页数")
    private Integer size = 10;
    @ApiModelProperty("租户ID")
    private String tenantId;
    @ApiModelProperty("APP标识")
    private Long appId;
    @ApiModelProperty("提货单编号")
    private String pickGoodsCode;
    @ApiModelProperty("提货计划状态")
    private Integer status;
    @ApiModelProperty("提货方式：自提PICK_MYSELF，外部仓库OUTER_WAREHOUSE，工地BUILD_PLACE，小库SMALL_WAREHOUSE")
    private String pickGoodsWayZt;
    @ApiModelProperty("提货人手机号")
    private String contactWay;
    @ApiModelProperty("隔离标识")
    private String isolationId;
    @ApiModelProperty("所属一级组织ID")
    private Long ascriptionOrgId;
    @ApiModelProperty("客户名称")
    private String customerName;
    @ApiModelProperty("过滤状态列表")
    private List<Integer> exceptStatusList;
    @ApiModelProperty("过滤状态列表s")
    private String exceptStatus;
    @ApiModelProperty("单据日期开始时间")
    private String createTimeFrom;
    @ApiModelProperty("单据日期结束时间")
    private String createTimeTo;
    @ApiModelProperty("要求交货日期开始时间")
    private String requiredTimeFrom;
    @ApiModelProperty("要求交货日期结束时间")
    private String requiredTimeTo;
}
