package com.deepexi.dd.domain.transaction.api;

import com.deepexi.dd.domain.transaction.domain.dto.OrderDeliverySelfRaisingInfoReqDTO;
import com.deepexi.dd.domain.transaction.domain.dto.OrderDeliverySelfRaisingInfoRespDTO;
import com.deepexi.dd.domain.transaction.domain.query.OrderDeliverySelfRaisingInfoReqQuery;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.annotations.Api;

/**
 * OrderDeliverySelfRaisingInfoApi
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 26 16:41:35 CST 2020
 */
@Api(value = "出库单自提地址信息表管理")
@RequestMapping("/open-api/v1/domain/middle/orderDeliverySelfRaisingInfos")
public interface OrderDeliverySelfRaisingInfoApi {
    /**
     * 查询出库单自提地址信息表列表
     *
     * @return
     */
    @GetMapping("/list")
    Payload<List<OrderDeliverySelfRaisingInfoRespDTO>> listOrderDeliverySelfRaisingInfos(OrderDeliverySelfRaisingInfoReqQuery query) throws Exception;

    /**
     * 分页查询出库单自提地址信息表列表
     *
     * @return
     */
    @GetMapping("/page")
    Payload<PageBean<OrderDeliverySelfRaisingInfoRespDTO>> listOrderDeliverySelfRaisingInfosPage(OrderDeliverySelfRaisingInfoReqQuery query) throws Exception;

    /**
     * 根据ID删除出库单自提地址信息表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Payload<Boolean> deleteByIdIn(@RequestBody List<Long> id) throws Exception;

    /**
     * 新增出库单自提地址信息表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    Payload<OrderDeliverySelfRaisingInfoRespDTO> insert(@RequestBody OrderDeliverySelfRaisingInfoReqDTO record) throws Exception;

    /**
     * 查询出库单自提地址信息表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Payload<OrderDeliverySelfRaisingInfoRespDTO> selectById(@PathVariable Long id) throws Exception;

    /**
     * 根据ID修改出库单自提地址信息表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Payload<Boolean> updateById(@PathVariable Long id, @RequestBody OrderDeliverySelfRaisingInfoReqDTO record) throws Exception;

}