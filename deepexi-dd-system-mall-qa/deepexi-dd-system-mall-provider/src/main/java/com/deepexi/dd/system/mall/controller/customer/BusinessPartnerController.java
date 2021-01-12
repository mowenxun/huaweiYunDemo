package com.deepexi.dd.system.mall.controller.customer;

import com.deepexi.dd.system.mall.domain.query.customer.BusinessPartnerAdminRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.customer.BusinessPartnerResponseVO;
import com.deepexi.dd.system.mall.service.customer.BusinessPartnerService;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName BusinessPartnerController
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-14
 * @Version 1.0
 **/
@Api(value = "业务伙伴管理", tags = "业务伙伴管理")
@RequestMapping("/admin-api/v1/domain/customer/partner")
@RestController
public class BusinessPartnerController {

    @Autowired
    private BusinessPartnerService partnerService;

    @GetMapping("/toplevel/partner")
    @ApiOperation(value = "获取最顶级的业务伙伴信息", nickname = "getTopLevelPartner")
    public Payload<BusinessPartnerResponseVO> getTopLevelPartner(@Valid BusinessPartnerAdminRequestQuery partnerQuery) throws Exception {
        BusinessPartnerResponseVO responseVO = partnerService.getTopLevelPartner(partnerQuery);
        return new Payload<>(responseVO);
    }

    @GetMapping("/getPartner")
    @ApiOperation(value = "获取业务伙伴信息", nickname = "getPartner")
    public Payload<BusinessPartnerResponseVO> getPartner(@Valid BusinessPartnerAdminRequestQuery partnerQuery) throws Exception {
        BusinessPartnerResponseVO responseVO = partnerService.getPartner(partnerQuery);
        return new Payload<>(responseVO);
    }

    @GetMapping("/findList")
    @ApiOperation(value = "获取业务伙伴列表", nickname = "findList")
    public Payload<List<BusinessPartnerResponseVO>> findList(@Valid BusinessPartnerAdminRequestQuery partnerQuery) throws Exception {
        List<BusinessPartnerResponseVO> result = partnerService.findList(partnerQuery);
        return new Payload<>(result);
    }

    @GetMapping("/findALL")
    @ApiOperation(value = "查询所有业务伙伴", nickname = "findALL")
    public Payload<List<BusinessPartnerResponseVO>> findAll(@Valid BusinessPartnerAdminRequestQuery partnerQuery) throws Exception {
        List<BusinessPartnerResponseVO> result = partnerService.findALL(partnerQuery);
        return new Payload<>(result);
    }
}
