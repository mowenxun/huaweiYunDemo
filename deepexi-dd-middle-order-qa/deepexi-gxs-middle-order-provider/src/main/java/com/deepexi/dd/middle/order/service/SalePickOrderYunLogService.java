package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.SalePickOrderYunLogDTO;
import com.deepexi.dd.middle.order.domain.SalePickOrderYunLogQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * SalePickOrderYunLogService
 *
 * @author admin
 * @date Thu Aug 27 21:37:43 CST 2020
 * @version 1.0
 */
public interface SalePickOrderYunLogService {


    /**
     * 查询列表
     *
     * @return
     */
    List<SalePickOrderYunLogDTO> listSalePickOrderYunLogs(SalePickOrderYunLogQuery query);

    /**
     * 分页查询列表
     *
     * @return
     */
    PageBean<SalePickOrderYunLogDTO> listSalePickOrderYunLogsPage(SalePickOrderYunLogQuery query);

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
    SalePickOrderYunLogDTO insert(SalePickOrderYunLogDTO record);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    SalePickOrderYunLogDTO selectById(Long id);


    /**
     * 根据ID修改
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SalePickOrderYunLogDTO record);

}