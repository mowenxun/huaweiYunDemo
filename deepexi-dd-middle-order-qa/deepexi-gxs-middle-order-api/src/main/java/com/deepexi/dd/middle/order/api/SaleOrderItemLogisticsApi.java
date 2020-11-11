package com.deepexi.dd.middle.order.api;

import com.deepexi.dd.middle.order.domain.dto.SaleOrderItemLogisticsRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderItemLogisticsResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemLogisticsRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SaleOrderItemLogisticsApi
 *
 * @author admin
 * @date Sat Aug 22 16:34:04 CST 2020
 * @version 1.0
 */
@Api(value = "OMS物流信息管理")
@RequestMapping("/saleOrderItemLogisticss")
public interface SaleOrderItemLogisticsApi {
    /**
     * 查询OMS物流信息列表
     *
     * @return
     */
    @GetMapping("/list")
    List<SaleOrderItemLogisticsResponseDTO> listSaleOrderItemLogisticss(SaleOrderItemLogisticsRequestQuery query);

    /**
     * 分页查询OMS物流信息列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<SaleOrderItemLogisticsResponseDTO> listSaleOrderItemLogisticssPage(SaleOrderItemLogisticsRequestQuery query);


    /**
     * 根据ID删除OMS物流信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增OMS物流信息
     *
     * @param record
     * @return
     */
    @PostMapping("")
    Long insert(@RequestBody SaleOrderItemLogisticsRequestDTO record) throws Exception;

    /**
     * 查询OMS物流信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    SaleOrderItemLogisticsResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改OMS物流信息
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody SaleOrderItemLogisticsRequestDTO record);

}