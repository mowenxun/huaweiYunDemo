package com.deepexi.dd.system.mall.controller.gxs.order;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/13/19:17
 * @Description:
 */

import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.transaction.domain.dto.gxs.order.ShopOrderAddDTO;
import com.deepexi.dd.domain.transaction.domain.dto.gxs.order.ShopOrderDetailVO;
import com.deepexi.dd.domain.transaction.domain.query.ShopOrderDomainRequestQuery;
import com.deepexi.dd.domain.transaction.domain.responseDto.ShopOrderDomainResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.gxs.order.PayBackVO;
import com.deepexi.dd.system.mall.domain.dto.gxs.order.RequestIdsPara;
import com.deepexi.dd.system.mall.domain.dto.gxs.order.ShopOrderSysAddDTO;
import com.deepexi.dd.system.mall.enums.GxsSysResultEnum;
import com.deepexi.dd.system.mall.enums.ResultEnum;
import com.deepexi.dd.system.mall.service.ShopOrderSysService;
import com.deepexi.dd.system.mall.util.ValidableList;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName ShopOrderController
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/10/13
 * @Version V1.0
 **/
@RestController
@Api(tags = "店铺订单接口")
@RequestMapping("/admin-api/v1/sys/shop/order")
public class ShopOrderController {

    @Autowired
    private ShopOrderSysService shopOrderSysService;

    @ApiOperation("我的订单-列表接口")
    @GetMapping("/page")
    public Payload<PageBean<ShopOrderDomainResponseDTO>> listShopOrdersPage(@Valid ShopOrderDomainRequestQuery query) throws Exception {
        return new Payload<>(shopOrderSysService.listShopOrdersPage(query));
    }

    @ApiOperation("购物车-提交接口")
    @PostMapping("")
    public Payload<List<ShopOrderAddDTO>> listShopOrdersPage(@RequestBody @Valid ValidableList<ShopOrderSysAddDTO> shopOrderSysAddDTOS) throws Exception {
        return new Payload<>(shopOrderSysService.addShopOrder(shopOrderSysAddDTOS));
    }

    @ApiOperation("我的订单-取消订单")
    @GetMapping("/cancel/{id}")
    public Payload<Boolean> canCel(@PathVariable Long id) throws Exception {
        return new Payload<>(shopOrderSysService.cancelShopOrderById(id));
    }

    @ApiOperation("我的订单-订单详情查看")
    @GetMapping("/{id}")
    public Payload<ShopOrderDetailVO> getDetailById(@PathVariable Long id) throws Exception {
        return new Payload<>(shopOrderSysService.getShopOrderDetailById(id));
    }

    @ApiOperation("门店订单支付接口")
    @PostMapping("/paySuccessUpdateStatus")
    public Payload<PayBackVO> paySuccessUpdateStatus(@RequestBody @Valid RequestIdsPara requestIdsPara) throws Exception {
        return new Payload<>(shopOrderSysService.paySuccessUpdateStatus(requestIdsPara.getIds()));
    }


    @ApiOperation("我的订单-订单详情-确定本人签收接口")
    @GetMapping("sign/{id}")
    public Payload<Boolean> signById(@PathVariable Long id) throws Exception {
        return new Payload<>(shopOrderSysService.signById(id));
    }
}
