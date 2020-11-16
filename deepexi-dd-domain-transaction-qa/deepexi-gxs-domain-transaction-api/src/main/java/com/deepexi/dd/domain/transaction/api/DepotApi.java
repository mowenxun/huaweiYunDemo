package com.deepexi.dd.domain.transaction.api;

import com.deepexi.dd.domain.transaction.domain.dto.DepotResponseDTO;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * DepotApi
 *
 * @author yuanzaishun
 * @version 1.0
 * @date Fri Jun 19 17:38:17 CST 2020
 */
@Api(value = "仓库接口")
@RequestMapping("/open-api/v1/domain/transaction/depot")
public interface DepotApi {
    /**
     * 查询应用下的仓库
     *
     * @param appId
     * @return
     */
    @GetMapping("/getDepotList/{appId}")
    public Payload<List<DepotResponseDTO>> getAllDepotList(@PathVariable("appId") Long appId, @RequestParam(name = "isolationId", required = false) String isolationId);
}
