package com.deepexi.dd.system.mall.controller;

import com.deepexi.dd.domain.common.domain.dto.SuppliersResponseDTO;
import com.deepexi.dd.domain.common.domain.query.SuppliersRequestQuery;
import com.deepexi.dd.system.mall.remote.common.SuppliersClient;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yp
 * @version 1.0
 * @date 2020-09-09 14:18
 */
@RestController
@Api(tags = "供应商远程接口")
@RequestMapping("/admin-api/v1/domain/transaction/suppliers")
public class SuppliersController {
    Logger logger = LoggerFactory.getLogger(SuppliersController.class);

    @Autowired
    private SuppliersClient suppliersClient;

    @ApiOperation(value = "查询上游供应商", nickname = "findUpstreamSuppliers")
    @PostMapping("/findUpstreamSuppliers")
    public Payload<List<SuppliersResponseDTO>> findUpstreamSuppliers(@RequestBody SuppliersRequestQuery dto) throws Exception {
        try {
            return suppliersClient.findUpstreamSuppliers(dto);
        } catch (Exception e) {
            logger.info("common域:查询上游供应商异常:{}\n" + e.getMessage());
            return new Payload(null, "500", e.getMessage());
        }
    }
}
