package com.deepexi.dd.middle.order.dao;

import com.deepexi.dd.middle.order.domain.SaleCloudInterfaceRecordDO;
import com.deepexi.dd.middle.order.domain.SaleCloudInterfaceRecordQuery;

import java.util.List;


/**
 * SaleCloudInterfaceRecordDAO
 *
 * @author admin
 * @date Wed Aug 26 19:14:46 CST 2020
 * @version 1.0
 */
public interface SaleCloudInterfaceRecordDAO {

    /**
     * 查询调用云仓接口记录表列表}
     *
     * @return
     */
    List<SaleCloudInterfaceRecordDO> listSaleCloudInterfaceRecords(SaleCloudInterfaceRecordQuery query);

    /**
     * 根据ID删除调用云仓接口记录表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增调用云仓接口记录表
     *
     * @param record
     * @return
     */
    int insert(SaleCloudInterfaceRecordDO record);

    /**
     * 查询调用云仓接口记录表详情
     *
     * @param id
     * @return
     */
    SaleCloudInterfaceRecordDO selectById(Long id);

    /**
     * 根据ID修改调用云仓接口记录表
     *
     * @param record
     * @return
     */
    int updateById(SaleCloudInterfaceRecordDO record);

}