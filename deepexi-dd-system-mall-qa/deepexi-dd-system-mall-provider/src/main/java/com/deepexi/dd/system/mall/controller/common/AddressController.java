package com.deepexi.dd.system.mall.controller.common;

import com.deepexi.dd.domain.common.domain.dto.AddressResponseDTO;
import com.deepexi.dd.domain.common.domain.query.AddressRequestQuery;
import com.deepexi.dd.system.mall.domain.query.common.AddressAdminRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.common.AddressResponseVO;
import com.deepexi.dd.system.mall.service.common.AddressService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName AddressController
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-25
 * @Version 1.0
 **/
@RestController
@Api(tags = "地理信息管理")
@RequestMapping("/admin-api/v1/domain/common/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/list")
    @ApiOperation("查询地址列表")
    public Payload<List<AddressResponseVO>> listAddresss(@Valid AddressAdminRequestQuery query) throws Exception {
        List<AddressResponseDTO> result = addressService.listAddresss(query.clone(AddressRequestQuery.class));
        return new Payload<>(GeneralConvertUtils.convert2List(result, AddressResponseVO.class));
    }
}
