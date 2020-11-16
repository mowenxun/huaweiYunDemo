package com.deepexi.dd.domain.transaction.api;

import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.query.OrderReturnInfoRequestQuery;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-08 20:11
 */
@Api(value = "退货单模块rpc接口")
@RequestMapping("/open-api/v1/domain/orderReturnInfo")
public interface OrderReturnInfoApi {
    /**
     * 查询销售订单列表
     *
     * @param query 查询条件
     * @return 分页数据
     */
    @PostMapping("/page")
    Payload<PageBean<OrderReturnInfoResponseDTO>> listOrderReturnInfosPage(@RequestBody @Valid OrderReturnInfoRequestQuery query) throws Exception;

    /**
     * 创建订单
     * @param record
     * @return
     */
    @PostMapping("")
    Payload<OrderReturnInfoAddResponseDTO> createOrderReturn(@RequestBody @Valid OrderReturnInfoRequestDTO record) throws Exception;

    /**
     * 根据ID查询退货单
     * @param id
     * @return
     */
    @ApiOperation("根据ID查询退货单")
    @GetMapping("/{id}")
    Payload<OrderReturnInfoResponseDTO> selectById(@PathVariable("id") Long id) throws Exception;
}
