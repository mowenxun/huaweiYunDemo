package com.deepexi.dd.system.mall.controller.customer;

import com.deepexi.dd.system.mall.domain.query.customer.CustomerPickUpWarehouseAdminRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.customer.CustomerWarehouseResponseVO;
import com.deepexi.dd.system.mall.service.customer.CustomerPickUpWarehourseService;
import com.deepexi.util.config.Payload;
import domain.dto.CustomerPickUpOrgWarehouseDomainQuery;
import domain.dto.CustomerPickUpWarehouseDomainQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(value = "客户仓库管理", tags = "客户仓库管理")
@RequestMapping("/admin-api/v1/domain/customer/warehouse")
@RestController
public class CustomerPickUpWarehouseController {

    @Autowired
    private CustomerPickUpWarehourseService customerPickUpWarehourseService;

    @GetMapping("/orglist")
    @ApiOperation(value = "根据伙伴获取组织客户的授权仓库", nickname = "根据伙伴获取组织客户的授权仓库")
    public Payload<List<CustomerWarehouseResponseVO>> orglist(@Valid CustomerPickUpWarehouseAdminRequestQuery query) throws Exception{
         return new Payload<>(customerPickUpWarehourseService.orglist(query.clone(CustomerPickUpOrgWarehouseDomainQuery.class)));
    }
}
