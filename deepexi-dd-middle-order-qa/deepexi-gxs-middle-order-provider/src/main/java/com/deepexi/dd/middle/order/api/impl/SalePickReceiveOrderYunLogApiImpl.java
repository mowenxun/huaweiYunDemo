package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SalePickReceiveOrderYunLogApi;
import com.deepexi.dd.middle.order.domain.SalePickReceiveOrderYunLogDTO;
import com.deepexi.dd.middle.order.domain.SalePickReceiveOrderYunLogQuery;
import com.deepexi.dd.middle.order.domain.dto.SalePickReceiveOrderYunLogRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickReceiveOrderYunLogResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickReceiveOrderYunLogRequestQuery;
import com.deepexi.dd.middle.order.service.SalePickReceiveOrderYunLogService;
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
 * SalePickReceiveOrderYunLogApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SalePickReceiveOrderYunLogApiImpl implements SalePickReceiveOrderYunLogApi {

    @Autowired
    private SalePickReceiveOrderYunLogService salePickReceiveOrderYunLogService;

    @Override
    @ApiOperation("查询列表")
    public List<SalePickReceiveOrderYunLogResponseDTO> listSalePickReceiveOrderYunLogs(SalePickReceiveOrderYunLogRequestQuery query) {
        return ObjectCloneUtils
                .convertList(salePickReceiveOrderYunLogService
                    .listSalePickReceiveOrderYunLogs(query.clone(SalePickReceiveOrderYunLogQuery.class, CloneDirection.OPPOSITE)),
                                    SalePickReceiveOrderYunLogResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询列表")
    public PageBean<SalePickReceiveOrderYunLogResponseDTO> listSalePickReceiveOrderYunLogsPage(SalePickReceiveOrderYunLogRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(salePickReceiveOrderYunLogService
                    .listSalePickReceiveOrderYunLogsPage(query.clone(SalePickReceiveOrderYunLogQuery.class, CloneDirection.OPPOSITE)),
                        SalePickReceiveOrderYunLogResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return salePickReceiveOrderYunLogService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增")
    public SalePickReceiveOrderYunLogResponseDTO insert(@RequestBody SalePickReceiveOrderYunLogRequestDTO record) {
        return salePickReceiveOrderYunLogService.insert(record.clone(SalePickReceiveOrderYunLogDTO.class, CloneDirection.OPPOSITE))
                .clone(SalePickReceiveOrderYunLogResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询")
    public SalePickReceiveOrderYunLogResponseDTO selectById(@PathVariable Long id) {
        SalePickReceiveOrderYunLogDTO result = salePickReceiveOrderYunLogService.selectById(id);
        return result != null ? result.clone(SalePickReceiveOrderYunLogResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新")
    public Boolean updateById(@PathVariable Long id,@RequestBody SalePickReceiveOrderYunLogRequestDTO record) {
        return salePickReceiveOrderYunLogService.updateById(id, record.clone(SalePickReceiveOrderYunLogDTO.class, CloneDirection.OPPOSITE));
    }
}
