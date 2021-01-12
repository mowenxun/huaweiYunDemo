package com.deepexi.dd.system.mall.controller;

import com.deepexi.dd.domain.transaction.domain.dto.DepotResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.DepotAppResponseDTO;
import com.deepexi.dd.system.mall.remote.order.DepotRemote;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : DepotController
 * @Description : 仓库接口
 * @Author : yuanzaishun
 * @Date: 2020-07-22 17:30
 */
@RestController
@Api(tags = "仓库接口")
@RequestMapping("/admin-api/v1/domain/transaction/depot")
public class DepotController {
    Logger logger = LoggerFactory.getLogger(DepotController.class);
    private static String SUCCESS = "0";
    @Autowired
    DepotRemote depotRemote;

    @GetMapping("/getDepotList/{appId}")
    @ApiOperation("查询仓库列表")
    public Payload<List<DepotAppResponseDTO>> getAllDepotList(@PathVariable("appId") Long appId,
                                                              @RequestParam(name = "isolationId", required = false) String isolationId) throws ApplicationException {
        try {
            Payload<List<DepotResponseDTO>> payload = depotRemote.getAllDepotList(appId, isolationId);
            if (SUCCESS.equals(payload.getCode())) {
                List<DepotResponseDTO> list = GeneralConvertUtils.convert2List(payload.getPayload(), DepotResponseDTO.class);
                if (CollectionUtil.isNotEmpty(list)) {
                    return new Payload<>(GeneralConvertUtils.convert2List(list, DepotAppResponseDTO.class));
                }
            }
        } catch (Exception e) {
            logger.error("查询仓库列表失败:", e);
            throw new ApplicationException("查询仓库列表失败");
        }
        return new Payload<>(new ArrayList<>());
    }


}
