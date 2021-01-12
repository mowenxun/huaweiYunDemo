package com.deepexi.dd.system.mall.domain.dto.gxs.order;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/19/16:43
 * @Description:
 */

import com.deepexi.dd.middle.order.domain.dto.OrderConsigneeAddressResponseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**PayBackVO支付成功之后返回的数据
 * @ClassName RequestIdsPara
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/10/19
 * @Version V1.0
 **/
@Data
public class PayBackVO {

    @ApiModelProperty(value = "是否支付成功")
    private Boolean isSuccess;

    @ApiModelProperty(value = "支付信息")
    private String message;

    @ApiModelProperty(value = "id集合")
    private List<Long> ids;

    @ApiModelProperty(value = "支付单单号")
    private String payOrderCode;

    @ApiModelProperty(value = "订货数")
    private Long totalCommodityNum;

    @ApiModelProperty(value = "支付金额")
    private BigDecimal payMoney;

    @ApiModelProperty(value = "种类")
    private Long type;

    @ApiModelProperty(value = "要求送达日期")
    private Date arriveDate;

    @ApiModelProperty(value = "收货信息实体")
    private OrderConsigneeAddressResponseDTO orderConsigneeAddressResponseDTO;
}
