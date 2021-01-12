package com.deepexi.dd.system.mall.controller.gxs;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/12/16/15:24
 * @Description:
 */

import com.deepexi.commodity.dto.es.EsGoodInformationApiRequestVO;
import com.deepexi.dd.system.mall.domain.vo.commodity.EsGoodInformationApiSysResponseVO;
import com.deepexi.dd.system.mall.service.gxs.EsCommodityApiSysService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName EsCommodityApiSysController
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/12/16
 * @Version V1.0
 **/

@Slf4j
@RestController
@RequestMapping("/admin-api/v1/gxs/commodity")
@Api(value = "ShopAfterSaleController", tags = "供销社-商户端-商品列表")
public class EsCommodityApiSysController {

    @Autowired
    private EsCommodityApiSysService sysService;


    @GetMapping("/page")
    @ApiOperation("分页获得商品列表")
    public Payload<PageBean<EsGoodInformationApiSysResponseVO>> getPage(EsGoodInformationApiRequestVO request) {
        return new Payload<>(sysService.getPage(request));
    }


}
