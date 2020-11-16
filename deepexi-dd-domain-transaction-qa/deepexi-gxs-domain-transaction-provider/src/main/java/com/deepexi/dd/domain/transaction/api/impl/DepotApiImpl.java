package com.deepexi.dd.domain.transaction.api.impl;

import com.deepexi.dd.domain.transaction.api.DepotApi;
import com.deepexi.dd.domain.transaction.domain.dto.DepotResponseDTO;
import com.deepexi.dd.domain.transaction.service.SaleOrderInfoService;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName : DepotApiImpl
 * @Description : 仓库接口实现
 * @Author : yuanzaishun
 * @Date: 2020-08-07 14:59
 */
@RestController
public class DepotApiImpl implements DepotApi {
    @Autowired
    SaleOrderInfoService saleOrderInfoService;

    @ApiOperation("查询仓库列表")
    @Override
    @GetMapping("/getDepotList/{appId}")
    public Payload<List<DepotResponseDTO>> getAllDepotList(@PathVariable("appId") Long appId, @RequestParam(name = "isolationId", required = false) String isolationId) {
        List<DepotResponseDTO> list = saleOrderInfoService.getAllDepotList(appId, isolationId);
        return new Payload<>(list);
    }


}
