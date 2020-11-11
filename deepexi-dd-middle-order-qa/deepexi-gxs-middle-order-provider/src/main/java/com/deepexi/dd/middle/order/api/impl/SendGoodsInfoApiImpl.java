package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SendGoodsInfoApi;
import com.deepexi.dd.middle.order.domain.SendGoodsInfoDTO;
import com.deepexi.dd.middle.order.domain.dto.SendGoodsInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SendGoodsInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.SendGoodsInfoQuery;
import com.deepexi.dd.middle.order.domain.query.SendGoodsInfoRequestQuery;
import com.deepexi.dd.middle.order.service.SendGoodsInfoService;
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
 * SendGoodsInfoApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SendGoodsInfoApiImpl implements SendGoodsInfoApi {

    @Autowired
    private SendGoodsInfoService sendGoodsInfoService;

    @Override
    @ApiOperation("查询发货表列表")
    public List<SendGoodsInfoResponseDTO> listSendGoodsInfos(SendGoodsInfoRequestQuery query) {
        return ObjectCloneUtils
                .convertList(sendGoodsInfoService
                    .listSendGoodsInfos(query.clone(SendGoodsInfoQuery.class, CloneDirection.OPPOSITE)),
                                    SendGoodsInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询发货表列表")
    public PageBean<SendGoodsInfoResponseDTO> listSendGoodsInfosPage(SendGoodsInfoRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(sendGoodsInfoService
                    .listSendGoodsInfosPage(query.clone(SendGoodsInfoQuery.class, CloneDirection.OPPOSITE)),
                        SendGoodsInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除发货表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return sendGoodsInfoService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增发货表")
    public SendGoodsInfoResponseDTO insert(@RequestBody SendGoodsInfoRequestDTO record) {
        return sendGoodsInfoService.insert(record.clone(SendGoodsInfoDTO.class, CloneDirection.OPPOSITE))
                .clone(SendGoodsInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询发货表")
    public SendGoodsInfoResponseDTO selectById(@PathVariable Long id) {
        SendGoodsInfoDTO result = sendGoodsInfoService.selectById(id);
        return result != null ? result.clone(SendGoodsInfoResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新发货表")
    public Boolean updateById(@PathVariable Long id,@RequestBody SendGoodsInfoRequestDTO record) {
        return sendGoodsInfoService.updateById(id, record.clone(SendGoodsInfoDTO.class, CloneDirection.OPPOSITE));
    }
}
