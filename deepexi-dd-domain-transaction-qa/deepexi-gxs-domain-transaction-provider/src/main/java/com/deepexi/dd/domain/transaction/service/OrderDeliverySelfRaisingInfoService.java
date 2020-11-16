package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.middle.order.domain.dto.OrderDeliverySelfRaisingInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderDeliverySelfRaisingInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderDeliverySelfRaisingInfoRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * OrderDeliverySelfRaisingInfoService
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 26 16:41:35 CST 2020
 */
public interface OrderDeliverySelfRaisingInfoService {
    /**
     * 查询出库单自提地址信息表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<OrderDeliverySelfRaisingInfoResponseDTO> listOrderDeliverySelfRaisingInfos(OrderDeliverySelfRaisingInfoRequestQuery query);

    /**
     * 分页查询出库单自提地址信息表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<OrderDeliverySelfRaisingInfoResponseDTO> listOrderDeliverySelfRaisingInfosPage(OrderDeliverySelfRaisingInfoRequestQuery query);

    Boolean deleteByIdIn(@RequestBody List<Long> id) throws Exception;

    /**
     * 新增出库单自提地址信息表
     *
     * @param record
     * @return
     */
    OrderDeliverySelfRaisingInfoResponseDTO insert(@RequestBody OrderDeliverySelfRaisingInfoRequestDTO record);

    /**
     * 查询出库单自提地址信息表详情
     *
     * @param id
     * @return
     */
    OrderDeliverySelfRaisingInfoResponseDTO selectById(@PathVariable Long id);

    /**
     * 根据ID修改出库单自提地址信息表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(@PathVariable Long id, @RequestBody OrderDeliverySelfRaisingInfoRequestDTO record);

}