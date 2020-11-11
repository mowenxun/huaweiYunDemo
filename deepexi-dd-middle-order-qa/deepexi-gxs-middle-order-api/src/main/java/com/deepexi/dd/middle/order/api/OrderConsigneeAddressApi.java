package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.OrderConsigneeAddressRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderConsigneeAddressResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderConsigneeAddressRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * OrderConsigneeAddressApi
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
@Api(value = "订单收货地址信息表管理")
@RequestMapping("/orderConsigneeAddresss")
public interface OrderConsigneeAddressApi {
    /**
     * 查询订单收货地址信息表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<OrderConsigneeAddressResponseDTO> listOrderConsigneeAddresss(OrderConsigneeAddressRequestQuery query);

    /**
     * 分页查询订单收货地址信息表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<OrderConsigneeAddressResponseDTO> listOrderConsigneeAddresssPage(OrderConsigneeAddressRequestQuery query);


    /**
     * 根据ID删除订单收货地址信息表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增订单收货地址信息表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    OrderConsigneeAddressResponseDTO insert(@RequestBody OrderConsigneeAddressRequestDTO record);

    /**
     * 查询订单收货地址信息表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    OrderConsigneeAddressResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改订单收货地址信息表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody OrderConsigneeAddressRequestDTO record);

}