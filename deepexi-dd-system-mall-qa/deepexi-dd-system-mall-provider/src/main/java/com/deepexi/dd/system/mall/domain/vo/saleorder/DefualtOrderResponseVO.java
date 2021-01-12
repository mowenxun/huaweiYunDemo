package com.deepexi.dd.system.mall.domain.vo.saleorder;

import com.deepexi.dd.middle.tool.domain.dto.ToolBilltypeResponseDTO;
import com.deepexi.dd.system.mall.domain.vo.BaseResponseVO;
import com.deepexi.dd.system.mall.domain.vo.customer.MerchantAddressResponseVO;
import com.deepexi.dd.system.mall.domain.vo.customer.MerchantInvoiceResponseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName DefualtOrderResponseVO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-05
 * @Version 1.0
 **/
@Data
@ApiModel
public class DefualtOrderResponseVO extends BaseResponseVO implements Serializable {

    private static final long serialVersionUID = 235989724106824564L;

    @ApiModelProperty(value = "发票信息")
    private MerchantInvoiceResponseVO invoiceInfo;

    @ApiModelProperty(value = "收货地址信息")
    private MerchantAddressResponseVO addressInfo;

    @ApiModelProperty(value = "订单信息")
    private OrderInfoResponseVO orderInfo;

    @ApiModelProperty(value = "订单类型信息")
    private ToolBilltypeResponseDTO billTypeInfo;
}
