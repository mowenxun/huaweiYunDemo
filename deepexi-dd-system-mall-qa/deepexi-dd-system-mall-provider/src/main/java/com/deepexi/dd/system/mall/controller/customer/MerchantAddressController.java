package com.deepexi.dd.system.mall.controller.customer;

import com.deepexi.dd.system.mall.domain.dto.customer.MerchantAddressCreateAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.customer.MerchantAddressDeleteAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.customer.MerchantAddressUpdateAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.query.customer.MerchantAddressAdminRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.customer.MerchantAddressResponseVO;
import com.deepexi.dd.system.mall.service.customer.MerchantAddressService;
import com.deepexi.util.config.Payload;
import domain.dto.MerchantAddressResponseDTO;
import domain.query.MerchantAddressRequestQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName MerchantAddressController
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
@Api(value = "客户收货地址管理", tags = "客户收货地址管理")
@RequestMapping("/admin-api/v1/domain/customer/address")
@RestController
public class MerchantAddressController {

    @Autowired
    private MerchantAddressService addressService;

    @GetMapping("/find/list")
    @ApiOperation(value = "查询客户收货地址列表", nickname = "findList")
    public Payload<List<MerchantAddressResponseVO>> findList(@Valid MerchantAddressAdminRequestQuery query) throws Exception {
        List<MerchantAddressResponseVO> result = addressService.findList(query.clone(MerchantAddressRequestQuery.class));
        return new Payload<>(result);
    }

    @PostMapping
    @ApiOperation(value = "创建客户收货地址信息", nickname = "createAddress")
    public Payload<MerchantAddressResponseVO> create(@Valid @RequestBody MerchantAddressCreateAdminRequestDTO createDTO) throws Exception {
        MerchantAddressResponseVO result = addressService.create(createDTO);
        return new Payload<>(result);
    }

    @PutMapping
    @ApiOperation(value = "修改客户收货地址", nickname = "updateAddress")
    public Payload<Boolean> update(@Valid @RequestBody MerchantAddressUpdateAdminRequestDTO updateDTO) throws Exception {
        return addressService.update(updateDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除客户收货地址",nickname="deletedAddress")
    public Payload<Boolean> delete(@PathVariable(value = "id") Long id) throws Exception {
        MerchantAddressDeleteAdminRequestDTO requestDTO = new MerchantAddressDeleteAdminRequestDTO();
        requestDTO.setId(id);
        return addressService.delete(requestDTO);
    }

    @ApiOperation(value = "查询客户详情", nickname = "detail")
    @GetMapping("/detail")
    public Payload<MerchantAddressResponseVO> detail(@RequestParam(value = "id", required = true) Long id) throws Exception {
        MerchantAddressResponseVO result = addressService.detail(id);
        return new Payload<>(result);
    }
}
