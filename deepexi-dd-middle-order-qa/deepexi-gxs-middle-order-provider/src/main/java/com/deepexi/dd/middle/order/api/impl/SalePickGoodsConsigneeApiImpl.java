package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SalePickGoodsConsigneeApi;
import com.deepexi.dd.middle.order.domain.SalePickGoodsConsigneeDTO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsConsigneeQuery;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsConsigneeRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsConsigneeResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsConsigneeRequestQuery;
import com.deepexi.dd.middle.order.service.SalePickGoodsConsigneeService;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * SalePickGoodsConsigneeApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SalePickGoodsConsigneeApiImpl implements SalePickGoodsConsigneeApi {

    @Autowired
    private SalePickGoodsConsigneeService salePickGoodsConsigneeService;

    @Override
    @ApiOperation("查询提货计划收货地址信息表列表")
    public List<SalePickGoodsConsigneeResponseDTO> listSalePickGoodsConsignees(SalePickGoodsConsigneeRequestQuery query) {
        return ObjectCloneUtils
                .convertList(salePickGoodsConsigneeService
                    .listSalePickGoodsConsignees(query.clone(SalePickGoodsConsigneeQuery.class, CloneDirection.OPPOSITE)),
                                    SalePickGoodsConsigneeResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询提货计划收货地址信息表列表")
    public PageBean<SalePickGoodsConsigneeResponseDTO> listSalePickGoodsConsigneesPage(SalePickGoodsConsigneeRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(salePickGoodsConsigneeService
                    .listSalePickGoodsConsigneesPage(query.clone(SalePickGoodsConsigneeQuery.class, CloneDirection.OPPOSITE)),
                        SalePickGoodsConsigneeResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除提货计划收货地址信息表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return salePickGoodsConsigneeService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增提货计划收货地址信息表")
    public SalePickGoodsConsigneeResponseDTO insert(@RequestBody SalePickGoodsConsigneeRequestDTO record) {
        return salePickGoodsConsigneeService.insert(record.clone(SalePickGoodsConsigneeDTO.class, CloneDirection.OPPOSITE))
                .clone(SalePickGoodsConsigneeResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询提货计划收货地址信息表")
    public SalePickGoodsConsigneeResponseDTO selectById(@PathVariable Long id) {
        SalePickGoodsConsigneeDTO result = salePickGoodsConsigneeService.selectById(id);
        return result != null ? result.clone(SalePickGoodsConsigneeResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新提货计划收货地址信息表")
    public Boolean updateById(@PathVariable Long id,@RequestBody SalePickGoodsConsigneeRequestDTO record) {
        return salePickGoodsConsigneeService.updateById(id, record.clone(SalePickGoodsConsigneeDTO.class, CloneDirection.OPPOSITE));
    }

    @Override
    @ApiOperation("根据提货编码查收货信息")
    public SalePickGoodsConsigneeResponseDTO selectByPickCode(@RequestBody SalePickGoodsConsigneeRequestQuery salePickGoodsConsigneeRequestQuery) {
        return salePickGoodsConsigneeService.selectByPickCode(salePickGoodsConsigneeRequestQuery);
    }
}
