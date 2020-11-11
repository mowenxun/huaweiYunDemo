package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.AfterSaleOrdeApi;
import com.deepexi.dd.middle.order.domain.dto.AfterSaleOrdeDTO;
import com.deepexi.dd.middle.order.domain.dto.AfterSaleOrdeRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.AfterSaleOrdeResponseDTO;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrdeQuery;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrdeRequestQuery;
import com.deepexi.dd.middle.order.service.AfterSaleOrdeService;
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
 * AfterSaleOrdeApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class AfterSaleOrdeApiImpl implements AfterSaleOrdeApi {

    @Autowired
    private AfterSaleOrdeService afterSaleOrdeService;

    @Override
    @ApiOperation("查询售后申请单列表")
    public List<AfterSaleOrdeResponseDTO> listAfterSaleOrdes(AfterSaleOrdeRequestQuery query) {
        return ObjectCloneUtils
                .convertList(afterSaleOrdeService
                    .listAfterSaleOrdes(query.clone(AfterSaleOrdeQuery.class, CloneDirection.OPPOSITE)),
                                    AfterSaleOrdeResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询售后申请单列表")
    public PageBean<AfterSaleOrdeResponseDTO> listAfterSaleOrdesPage(AfterSaleOrdeRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(afterSaleOrdeService
                    .listAfterSaleOrdesPage(query.clone(AfterSaleOrdeQuery.class, CloneDirection.OPPOSITE)),
                        AfterSaleOrdeResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除售后申请单")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return afterSaleOrdeService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增售后申请单")
    public AfterSaleOrdeResponseDTO insert(@RequestBody AfterSaleOrdeRequestDTO record) {
        return afterSaleOrdeService.insert(record.clone(AfterSaleOrdeDTO.class, CloneDirection.OPPOSITE))
                .clone(AfterSaleOrdeResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询售后申请单")
    public AfterSaleOrdeResponseDTO selectById(@PathVariable Long id) {
        AfterSaleOrdeDTO result = afterSaleOrdeService.selectById(id);
        return result != null ? result.clone(AfterSaleOrdeResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新售后申请单")
    public Boolean updateById(@PathVariable Long id,@RequestBody AfterSaleOrdeRequestDTO record) {
        return afterSaleOrdeService.updateById(id, record.clone(AfterSaleOrdeDTO.class, CloneDirection.OPPOSITE));
    }
}
