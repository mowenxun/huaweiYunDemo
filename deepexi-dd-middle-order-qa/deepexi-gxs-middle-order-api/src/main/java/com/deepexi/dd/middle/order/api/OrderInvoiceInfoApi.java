package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.OrderInvoiceInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderInvoiceInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderInvoiceInfoRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * OrderInvoiceInfoApi
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
@Api(value = "订单的开票信息表管理")
@RequestMapping("/orderInvoiceInfos")
public interface OrderInvoiceInfoApi {
    /**
     * 查询订单的开票信息表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<OrderInvoiceInfoResponseDTO> listOrderInvoiceInfos(OrderInvoiceInfoRequestQuery query);

    /**
     * 分页查询订单的开票信息表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<OrderInvoiceInfoResponseDTO> listOrderInvoiceInfosPage(OrderInvoiceInfoRequestQuery query);


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
    OrderInvoiceInfoResponseDTO insert(@RequestBody OrderInvoiceInfoRequestDTO record);

    /**
     * 查询订单的开票信息表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    OrderInvoiceInfoResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改订单的开票信息表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody OrderInvoiceInfoRequestDTO record);

}