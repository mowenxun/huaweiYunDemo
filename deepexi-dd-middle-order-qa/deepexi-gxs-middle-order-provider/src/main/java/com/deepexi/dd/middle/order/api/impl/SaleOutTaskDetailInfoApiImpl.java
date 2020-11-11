package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SaleOutTaskDetailInfoApi;
import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskDetailInfoDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskDetailInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskDetailInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOutTaskDetailInfoQuery;
import com.deepexi.dd.middle.order.domain.query.SaleOutTaskDetailInfoRequestQuery;
import com.deepexi.dd.middle.order.service.SaleOutTaskDetailInfoService;
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
 * SaleOutTaskDetailInfoApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SaleOutTaskDetailInfoApiImpl implements SaleOutTaskDetailInfoApi {

    @Autowired
    private SaleOutTaskDetailInfoService saleOutTaskDetailInfoService;

    @Override
    @ApiOperation("查询销售出库商品明细表列表")
    public List<SaleOutTaskDetailInfoResponseDTO> listSaleOutTaskDetailInfos(SaleOutTaskDetailInfoRequestQuery query) {
        return ObjectCloneUtils
                .convertList(saleOutTaskDetailInfoService
                    .listSaleOutTaskDetailInfos(query.clone(SaleOutTaskDetailInfoQuery.class, CloneDirection.OPPOSITE)),
                                    SaleOutTaskDetailInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询销售出库商品明细表列表")
    public PageBean<SaleOutTaskDetailInfoResponseDTO> listSaleOutTaskDetailInfosPage(SaleOutTaskDetailInfoRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(saleOutTaskDetailInfoService
                    .listSaleOutTaskDetailInfosPage(query.clone(SaleOutTaskDetailInfoQuery.class, CloneDirection.OPPOSITE)),
                        SaleOutTaskDetailInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除销售出库商品明细表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return saleOutTaskDetailInfoService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增销售出库商品明细表")
    public SaleOutTaskDetailInfoResponseDTO insert(@RequestBody SaleOutTaskDetailInfoRequestDTO record) {
        return saleOutTaskDetailInfoService.insert(record.clone(SaleOutTaskDetailInfoDTO.class, CloneDirection.OPPOSITE))
                .clone(SaleOutTaskDetailInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询销售出库商品明细表")
    public SaleOutTaskDetailInfoResponseDTO selectById(@PathVariable Long id) {
        SaleOutTaskDetailInfoDTO result = saleOutTaskDetailInfoService.selectById(id);
        return result != null ? result.clone(SaleOutTaskDetailInfoResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新销售出库商品明细表")
    public Boolean updateById(@PathVariable Long id,@RequestBody SaleOutTaskDetailInfoRequestDTO record) {
        return saleOutTaskDetailInfoService.updateById(id, record.clone(SaleOutTaskDetailInfoDTO.class, CloneDirection.OPPOSITE));
    }
}
