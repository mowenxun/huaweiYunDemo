package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.SaleCloudInterfaceRecordDTO;
import com.deepexi.dd.middle.order.domain.SaleCloudInterfaceRecordQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * SaleCloudInterfaceRecordService
 *
 * @author admin
 * @date Wed Aug 26 19:14:46 CST 2020
 * @version 1.0
 */
public interface SaleCloudInterfaceRecordService {


    /**
     * 查询调用云仓接口记录表列表
     *
     * @return
     */
    List<SaleCloudInterfaceRecordDTO> listSaleCloudInterfaceRecords(SaleCloudInterfaceRecordQuery query);

    /**
     * 分页查询调用云仓接口记录表列表
     *
     * @return
     */
    PageBean<SaleCloudInterfaceRecordDTO> listSaleCloudInterfaceRecordsPage(SaleCloudInterfaceRecordQuery query);

    /**
     * 根据ID删除调用云仓接口记录表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增调用云仓接口记录表
     *
     * @param record
     * @return
     */
    SaleCloudInterfaceRecordDTO insert(SaleCloudInterfaceRecordDTO record);

    /**
     * 查询调用云仓接口记录表详情
     *
     * @param id
     * @return
     */
    SaleCloudInterfaceRecordDTO selectById(Long id);


    /**
     * 根据ID修改调用云仓接口记录表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SaleCloudInterfaceRecordDTO record);

}