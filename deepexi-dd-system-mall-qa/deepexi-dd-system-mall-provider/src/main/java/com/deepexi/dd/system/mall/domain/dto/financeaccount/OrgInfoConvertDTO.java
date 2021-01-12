package com.deepexi.dd.system.mall.domain.dto.financeaccount;

import com.deepexi.dd.middle.finance.domain.dto.FinanceAmountResponseDTO;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrgInfoConvertDTO extends AbstractObject implements Serializable {

    @ApiModelProperty("name")
    private String name;

    @ApiModelProperty("组织id，隔离id")
    private String orgId;

    @ApiModelProperty("组织账户信息")
    private FinanceAmountResponseDTO financeAccountInfo;

}
