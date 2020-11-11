package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SalePickOrderYunLogApi;
import com.deepexi.dd.middle.order.domain.SalePickOrderYunLogDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickOrderYunLogRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickOrderYunLogResponseDTO;
import com.deepexi.dd.middle.order.domain.SalePickOrderYunLogQuery;
import com.deepexi.dd.middle.order.domain.query.SalePickOrderYunLogRequestQuery;
import com.deepexi.dd.middle.order.service.SalePickOrderYunLogService;
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
 * SalePickOrderYunLogApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SalePickOrderYunLogApiImpl implements SalePickOrderYunLogApi {

    @Autowired
    private SalePickOrderYunLogService salePickOrderYunLogService;

    @Override
    @ApiOperation("查询列表")
    public List<SalePickOrderYunLogResponseDTO> listSalePickOrderYunLogs(SalePickOrderYunLogRequestQuery query) {
        return ObjectCloneUtils
                .convertList(salePickOrderYunLogService
                    .listSalePickOrderYunLogs(query.clone(SalePickOrderYunLogQuery.class, CloneDirection.OPPOSITE)),
                                    SalePickOrderYunLogResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询列表")
    public PageBean<SalePickOrderYunLogResponseDTO> listSalePickOrderYunLogsPage(SalePickOrderYunLogRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(salePickOrderYunLogService
                    .listSalePickOrderYunLogsPage(query.clone(SalePickOrderYunLogQuery.class, CloneDirection.OPPOSITE)),
                        SalePickOrderYunLogResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return salePickOrderYunLogService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增")
    public SalePickOrderYunLogResponseDTO insert(@RequestBody SalePickOrderYunLogRequestDTO record) {
        return salePickOrderYunLogService.insert(record.clone(SalePickOrderYunLogDTO.class, CloneDirection.OPPOSITE))
                .clone(SalePickOrderYunLogResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询")
    public SalePickOrderYunLogResponseDTO selectById(@PathVariable Long id) {
        SalePickOrderYunLogDTO result = salePickOrderYunLogService.selectById(id);
        return result != null ? result.clone(SalePickOrderYunLogResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新")
    public Boolean updateById(@PathVariable Long id,@RequestBody SalePickOrderYunLogRequestDTO record) {
        return salePickOrderYunLogService.updateById(id, record.clone(SalePickOrderYunLogDTO.class, CloneDirection.OPPOSITE));
    }
}
