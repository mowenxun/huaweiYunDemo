package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.OrderInvoiceRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderInvoiceResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderInvoiceRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * OrderInvoiceApi
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
@Api(value = "订单的开票信息表管理")
@RequestMapping("/orderInvoices")
public interface OrderInvoiceApi {
    /**
     * 查询订单的开票信息表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<OrderInvoiceResponseDTO> listOrderInvoices(OrderInvoiceRequestQuery query);

    /**
     * 分页查询订单的开票信息表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<OrderInvoiceResponseDTO> listOrderInvoicesPage(OrderInvoiceRequestQuery query);


    /**
     * 根据ID删除订单的开票信息表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增订单的开票信息表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    OrderInvoiceResponseDTO insert(@RequestBody OrderInvoiceRequestDTO record);

    /**
     * 查询订单的开票信息表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    OrderInvoiceResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改订单的开票信息表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody OrderInvoiceRequestDTO record);

}