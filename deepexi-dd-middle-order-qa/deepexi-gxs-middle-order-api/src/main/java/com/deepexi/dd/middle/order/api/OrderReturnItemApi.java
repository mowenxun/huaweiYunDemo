package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.OrderReturnItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderReturnItemResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderReturnItemRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * OrderReturnItemApi
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
@Api(value = "销售订单退货明细表管理")
@RequestMapping("/orderReturnItems")
public interface OrderReturnItemApi {
    /**
     * 查询销售订单退货明细表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<OrderReturnItemResponseDTO> listOrderReturnItems(OrderReturnItemRequestQuery query);

    /**
     * 分页查询销售订单退货明细表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<OrderReturnItemResponseDTO> listOrderReturnItemsPage(OrderReturnItemRequestQuery query);


    /**
     * 根据ID删除销售订单退货明细表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增销售订单退货明细表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    OrderReturnItemResponseDTO insert(@RequestBody OrderReturnItemRequestDTO record);

    /**
     * 查询销售订单退货明细表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    OrderReturnItemResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改销售订单退货明细表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody OrderReturnItemRequestDTO record);

}