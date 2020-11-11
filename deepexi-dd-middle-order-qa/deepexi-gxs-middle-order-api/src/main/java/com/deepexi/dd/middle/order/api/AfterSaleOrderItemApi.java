package com.deepexi.dd.middle.order.api;
import com.deepexi.dd.middle.order.domain.dto.AfterSaleOrderItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.AfterSaleOrderItemResponseDTO;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrderItemRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * AfterSaleOrderItemApi
 *
 * @author admin
 * @date Fri Oct 16 14:17:13 CST 2020
 * @version 1.0
 */
@Api(value = "售后订单明细表管理")
@RequestMapping("/afterSaleOrderItems")
public interface AfterSaleOrderItemApi {
    /**
     * 查询售后订单明细表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<AfterSaleOrderItemResponseDTO> listAfterSaleOrderItems(AfterSaleOrderItemRequestQuery query);

    /**
     * 分页查询售后订单明细表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<AfterSaleOrderItemResponseDTO> listAfterSaleOrderItemsPage(AfterSaleOrderItemRequestQuery query);


    /**
     * 根据ID删除售后订单明细表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增售后订单明细表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    AfterSaleOrderItemResponseDTO insert(@RequestBody AfterSaleOrderItemRequestDTO record);

    /**
     * 查询售后订单明细表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    AfterSaleOrderItemResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改售后订单明细表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody AfterSaleOrderItemRequestDTO record);

}