package com.deepexi.dd.middle.order.api;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderAmountStatuEditDTO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderSplitRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderSplitRecordResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderSplitRecordRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * SaleOrderSplitRecordApi
 *
 * @author admin
 * @date Wed Aug 12 20:24:31 CST 2020
 * @version 1.0
 */
@Api(value = "销售订单拆单记录表管理")
@RequestMapping("/middle/v1/saleOrderSplitRecords")
public interface SaleOrderSplitRecordApi {
    /**
     * 查询销售订单拆单记录表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<SaleOrderSplitRecordResponseDTO> listSaleOrderSplitRecords(SaleOrderSplitRecordRequestQuery query);

    /**
     * 分页查询销售订单拆单记录表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<SaleOrderSplitRecordResponseDTO> listSaleOrderSplitRecordsPage(SaleOrderSplitRecordRequestQuery query);


    /**
     * 根据ID删除销售订单拆单记录表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增销售订单拆单记录表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    SaleOrderSplitRecordResponseDTO insert(@RequestBody SaleOrderSplitRecordRequestDTO record);

    /**
     * 查询销售订单拆单记录表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    SaleOrderSplitRecordResponseDTO selectById(@PathVariable Long id);


    /**
     * 查询销售订单拆单记录表详情
     *
     * @param code
     * @return
     */
    @GetMapping("/code/{code}")
    Payload<SaleOrderSplitRecordResponseDTO> selectByCode(@PathVariable String code);

    /**
     * 父订单支付信息和子订单支付信息更新
     * @param editDto
     * @return
     */
    @PostMapping("/updateOrderAmountAndStatus")
    Boolean updateOrderAmountAndStatus(@RequestBody SaleOrderAmountStatuEditDTO editDto);


    /**
     * 根据ID修改销售订单拆单记录表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody SaleOrderSplitRecordRequestDTO record);

}