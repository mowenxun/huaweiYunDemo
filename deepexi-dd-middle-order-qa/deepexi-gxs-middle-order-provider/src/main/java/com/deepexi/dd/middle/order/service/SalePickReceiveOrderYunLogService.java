package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.SalePickReceiveOrderYunLogDTO;
import com.deepexi.dd.middle.order.domain.SalePickReceiveOrderYunLogQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * SalePickReceiveOrderYunLogService
 *
 * @author admin
 * @date Wed Sep 23 13:47:55 CST 2020
 * @version 1.0
 */
public interface SalePickReceiveOrderYunLogService {


    /**
     * 查询列表
     *
     * @return
     */
    List<SalePickReceiveOrderYunLogDTO> listSalePickReceiveOrderYunLogs(SalePickReceiveOrderYunLogQuery query);

    /**
     * 分页查询列表
     *
     * @return
     */
    PageBean<SalePickReceiveOrderYunLogDTO> listSalePickReceiveOrderYunLogsPage(SalePickReceiveOrderYunLogQuery query);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    SalePickReceiveOrderYunLogDTO insert(SalePickReceiveOrderYunLogDTO record);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    SalePickReceiveOrderYunLogDTO selectById(Long id);


    /**
     * 根据ID修改
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SalePickReceiveOrderYunLogDTO record);

}