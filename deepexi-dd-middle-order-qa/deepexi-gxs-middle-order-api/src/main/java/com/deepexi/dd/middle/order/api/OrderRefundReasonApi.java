package com.deepexi.dd.middle.order.api;

import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonFindResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderRefundReasonRequestQuery;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * OrderRefundReasonApi
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 19 16:00:27 CST 2020
 */
@Api(value = "退货原因管理")
@RequestMapping("/open-api-middle-order/middle/orderRefundReasons")
public interface OrderRefundReasonApi {
    /**
     * 查询列表
     *
     * @return
     */
    @GetMapping("/list")
    Payload<List<OrderRefundReasonResponseDTO>> listOrderRefundReasons(OrderRefundReasonRequestQuery query);

    /**
     * 校验编码
     *
     * @return
     */
    @GetMapping("/CheckCode")
    Payload<List<OrderRefundReasonResponseDTO>> CheckCode(OrderRefundReasonRequestQuery query);

    /**
     * 分页查询列表
     *
     * @return
     */
    @GetMapping("/page")
    Payload<PageBean<OrderRefundReasonResponseDTO>> listOrderRefundReasonsPage(OrderRefundReasonRequestQuery query);


    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Payload<Boolean> deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    @PostMapping("")
    Payload<OrderRefundReasonResponseDTO> insert(@RequestBody OrderRefundReasonRequestDTO record);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Payload<OrderRefundReasonResponseDTO> selectById(@PathVariable Long id);


    /**
     * 根据ID修改
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Payload<Boolean> updateById(@PathVariable Long id, @RequestBody OrderRefundReasonRequestDTO record);

    /**
     * 批量修改状态
     *
     * @param record
     * @return
     * @throws Exception
     */
    @PostMapping("updateStatus")
    Payload<Boolean> updateStatus(@RequestBody OrderRefundReasonRequestDTO record);

    @PutMapping("/cancel/{id}")
    Payload<Boolean> cancel(@PathVariable Long id);

    @PostMapping("/findOrderRefundReason")
    Payload<List<OrderRefundReasonFindResponseDTO>> findOrderRefundReason(@RequestBody List<String> orderCodeList);

}