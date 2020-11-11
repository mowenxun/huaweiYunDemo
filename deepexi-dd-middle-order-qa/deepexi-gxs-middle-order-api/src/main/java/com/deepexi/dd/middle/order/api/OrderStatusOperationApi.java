package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.OrderStatusOperationRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderStatusOperationResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderStatusOperationRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * OrderStatusOperationApi
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
@Api(value = "订单 状态-操作表管理")
@RequestMapping("/orderStatusOperations")
public interface OrderStatusOperationApi {
    /**
     * 查询订单 状态-操作表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<OrderStatusOperationResponseDTO> listOrderStatusOperations(OrderStatusOperationRequestQuery query);

    /**
     * 分页查询订单 状态-操作表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<OrderStatusOperationResponseDTO> listOrderStatusOperationsPage(OrderStatusOperationRequestQuery query);


    /**
     * 根据ID删除订单 状态-操作表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增订单 状态-操作表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    OrderStatusOperationResponseDTO insert(@RequestBody OrderStatusOperationRequestDTO record);

    /**
     * 查询订单 状态-操作表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    OrderStatusOperationResponseDTO selectById(@PathVariable Long id);



}