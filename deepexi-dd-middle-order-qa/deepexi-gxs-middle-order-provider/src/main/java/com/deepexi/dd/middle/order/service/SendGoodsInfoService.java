package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.SendGoodsInfoDTO;
import com.deepexi.dd.middle.order.domain.SendGoodsInfoQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * SendGoodsInfoService
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
public interface SendGoodsInfoService {


    /**
     * 查询发货表列表
     *
     * @return
     */
    List<SendGoodsInfoDTO> listSendGoodsInfos(SendGoodsInfoQuery query);

    /**
     * 分页查询发货表列表
     *
     * @return
     */
    PageBean<SendGoodsInfoDTO> listSendGoodsInfosPage(SendGoodsInfoQuery query);

    /**
     * 根据ID删除发货表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增发货表
     *
     * @param record
     * @return
     */
    SendGoodsInfoDTO insert(SendGoodsInfoDTO record);

    /**
     * 查询发货表详情
     *
     * @param id
     * @return
     */
    SendGoodsInfoDTO selectById(Long id);


    /**
     * 根据ID修改发货表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SendGoodsInfoDTO record);

}