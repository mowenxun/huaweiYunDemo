package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SaleCloudInterfaceRecordApi;
import com.deepexi.dd.middle.order.domain.SaleCloudInterfaceRecordDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleCloudInterfaceRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleCloudInterfaceRecordResponseDTO;
import com.deepexi.dd.middle.order.domain.SaleCloudInterfaceRecordQuery;
import com.deepexi.dd.middle.order.domain.query.SaleCloudInterfaceRecordRequestQuery;
import com.deepexi.dd.middle.order.service.SaleCloudInterfaceRecordService;
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
 * SaleCloudInterfaceRecordApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SaleCloudInterfaceRecordApiImpl implements SaleCloudInterfaceRecordApi {

    @Autowired
    private SaleCloudInterfaceRecordService saleCloudInterfaceRecordService;

    @Override
    @ApiOperation("查询调用云仓接口记录表列表")
    public List<SaleCloudInterfaceRecordResponseDTO> listSaleCloudInterfaceRecords(SaleCloudInterfaceRecordRequestQuery query) {
        return ObjectCloneUtils
                .convertList(saleCloudInterfaceRecordService
                    .listSaleCloudInterfaceRecords(query.clone(SaleCloudInterfaceRecordQuery.class, CloneDirection.OPPOSITE)),
                                    SaleCloudInterfaceRecordResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询调用云仓接口记录表列表")
    public PageBean<SaleCloudInterfaceRecordResponseDTO> listSaleCloudInterfaceRecordsPage(SaleCloudInterfaceRecordRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(saleCloudInterfaceRecordService
                    .listSaleCloudInterfaceRecordsPage(query.clone(SaleCloudInterfaceRecordQuery.class, CloneDirection.OPPOSITE)),
                        SaleCloudInterfaceRecordResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除调用云仓接口记录表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return saleCloudInterfaceRecordService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增调用云仓接口记录表")
    public SaleCloudInterfaceRecordResponseDTO insert(@RequestBody SaleCloudInterfaceRecordRequestDTO record) {
        return saleCloudInterfaceRecordService.insert(record.clone(SaleCloudInterfaceRecordDTO.class, CloneDirection.OPPOSITE))
                .clone(SaleCloudInterfaceRecordResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询调用云仓接口记录表")
    public SaleCloudInterfaceRecordResponseDTO selectById(@PathVariable Long id) {
        SaleCloudInterfaceRecordDTO result = saleCloudInterfaceRecordService.selectById(id);
        return result != null ? result.clone(SaleCloudInterfaceRecordResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新调用云仓接口记录表")
    public Boolean updateById(@PathVariable Long id,@RequestBody SaleCloudInterfaceRecordRequestDTO record) {
        return saleCloudInterfaceRecordService.updateById(id, record.clone(SaleCloudInterfaceRecordDTO.class, CloneDirection.OPPOSITE));
    }
}
