package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SupplerOrderItemApi;
import com.deepexi.dd.middle.order.domain.SupplerOrderItemDTO;
import com.deepexi.dd.middle.order.domain.dto.SupplerOrderItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SupplerOrderItemResponseDTO;
import com.deepexi.dd.middle.order.domain.SupplerOrderItemQuery;
import com.deepexi.dd.middle.order.domain.query.SupplerOrderItemRequestQuery;
import com.deepexi.dd.middle.order.service.SupplerOrderItemService;
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
 * SupplerOrderItemApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SupplerOrderItemApiImpl implements SupplerOrderItemApi {

    @Autowired
    private SupplerOrderItemService supplerOrderItemService;

    @Override
    @ApiOperation("查询已分发订单明细表列表")
    public List<SupplerOrderItemResponseDTO> listSupplerOrderItems(SupplerOrderItemRequestQuery query) {
        return ObjectCloneUtils
                .convertList(supplerOrderItemService
                                .listSupplerOrderItems(query.clone(SupplerOrderItemQuery.class,
                                        CloneDirection.OPPOSITE)),
                        SupplerOrderItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询已分发订单明细表列表")
    public PageBean<SupplerOrderItemResponseDTO> listSupplerOrderItemsPage(SupplerOrderItemRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(supplerOrderItemService
                                .listSupplerOrderItemsPage(query.clone(SupplerOrderItemQuery.class,
                                        CloneDirection.OPPOSITE)),
                        SupplerOrderItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除已分发订单明细表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return supplerOrderItemService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增已分发订单明细表")
    public SupplerOrderItemResponseDTO insert(@RequestBody SupplerOrderItemRequestDTO record) {
        return supplerOrderItemService.insert(record.clone(SupplerOrderItemDTO.class, CloneDirection.OPPOSITE))
                .clone(SupplerOrderItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询已分发订单明细表")
    public SupplerOrderItemResponseDTO selectById(@PathVariable Long id) {
        SupplerOrderItemDTO result = supplerOrderItemService.selectById(id);
        return result != null ? result.clone(SupplerOrderItemResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新已分发订单明细表")
    public Boolean updateById(@PathVariable Long id, @RequestBody SupplerOrderItemRequestDTO record) {
        return supplerOrderItemService.updateById(id, record.clone(SupplerOrderItemDTO.class, CloneDirection.OPPOSITE));
    }

    @Override
    public boolean saveBatch(@RequestBody List<SupplerOrderItemRequestDTO> list) {
        List<SupplerOrderItemDTO> list1 = ObjectCloneUtils.convertList(list, SupplerOrderItemDTO.class);
        return supplerOrderItemService.saveBatch(list1);
    }

    @Override
    public boolean updateBatchById(@RequestBody List<SupplerOrderItemRequestDTO> list) {
        List<SupplerOrderItemDTO> list1 = ObjectCloneUtils.convertList(list, SupplerOrderItemDTO.class);
        return supplerOrderItemService.updateBatchById(list1);
    }
}
