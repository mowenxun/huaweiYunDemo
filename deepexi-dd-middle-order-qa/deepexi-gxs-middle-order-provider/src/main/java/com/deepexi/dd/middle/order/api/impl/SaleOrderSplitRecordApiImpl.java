package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SaleOrderSplitRecordApi;
import com.deepexi.dd.middle.order.domain.SaleOrderSplitRecordDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderAmountStatuEditDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderSplitRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderSplitRecordResponseDTO;
import com.deepexi.dd.middle.order.domain.SaleOrderSplitRecordQuery;
import com.deepexi.dd.middle.order.domain.query.SaleOrderSplitRecordRequestQuery;
import com.deepexi.dd.middle.order.service.SaleOrderSplitRecordService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.deepexi.util.pojo.CloneDirection;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * SaleOrderSplitRecordApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SaleOrderSplitRecordApiImpl implements SaleOrderSplitRecordApi {

    @Autowired
    private SaleOrderSplitRecordService saleOrderSplitRecordService;

    @Override
    @ApiOperation("查询销售订单拆单记录表列表")
    public List<SaleOrderSplitRecordResponseDTO> listSaleOrderSplitRecords(SaleOrderSplitRecordRequestQuery query) {
        return ObjectCloneUtils
                .convertList(saleOrderSplitRecordService
                    .listSaleOrderSplitRecords(query.clone(SaleOrderSplitRecordQuery.class, CloneDirection.OPPOSITE)),
                                    SaleOrderSplitRecordResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询销售订单拆单记录表列表")
    public PageBean<SaleOrderSplitRecordResponseDTO> listSaleOrderSplitRecordsPage(SaleOrderSplitRecordRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(saleOrderSplitRecordService
                    .listSaleOrderSplitRecordsPage(query.clone(SaleOrderSplitRecordQuery.class, CloneDirection.OPPOSITE)),
                        SaleOrderSplitRecordResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除销售订单拆单记录表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return saleOrderSplitRecordService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增销售订单拆单记录表")
    public SaleOrderSplitRecordResponseDTO insert(@RequestBody SaleOrderSplitRecordRequestDTO record) {
        return saleOrderSplitRecordService.insert(record.clone(SaleOrderSplitRecordDTO.class, CloneDirection.OPPOSITE))
                .clone(SaleOrderSplitRecordResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询销售订单拆单记录表")
    public SaleOrderSplitRecordResponseDTO selectById(@PathVariable Long id) {
        SaleOrderSplitRecordDTO result = saleOrderSplitRecordService.selectById(id);
        return result != null ? result.clone(SaleOrderSplitRecordResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新销售订单拆单记录表")
    public Boolean updateById(@PathVariable Long id,@RequestBody SaleOrderSplitRecordRequestDTO record) {
        return saleOrderSplitRecordService.updateById(id, record.clone(SaleOrderSplitRecordDTO.class, CloneDirection.OPPOSITE));
    }

    @Override
    public Payload<SaleOrderSplitRecordResponseDTO> selectByCode(@PathVariable String code) {
        return new Payload<>(saleOrderSplitRecordService.selectByCode(code));
    }

    @Override
    @ApiOperation("")
    public Boolean updateOrderAmountAndStatus(@RequestBody SaleOrderAmountStatuEditDTO editDto) {
        return saleOrderSplitRecordService.updateOrderAmountAndStatus(editDto);
    }
}
