package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SaleCloudInterfaceRecordDO;
import com.deepexi.dd.middle.order.domain.SaleCloudInterfaceRecordQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * SaleCloudInterfaceRecordMapper
 *
 * @author admin
 * @date Wed Aug 26 19:53:05 CST 2020
 * @version 1.0
 */
@Mapper
public interface SaleCloudInterfaceRecordMapper extends BaseMapper<SaleCloudInterfaceRecordDO> {

    /**
     * 查询调用云仓接口记录表
     *
     * @param query
     * @return
     */
    List<SaleCloudInterfaceRecordDO> findAll(SaleCloudInterfaceRecordQuery query);

    /**
     * 删除调用云仓接口记录表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新调用云仓接口记录表
     *
     * @param list
     * @return
     */
    int updateBatch(List<SaleCloudInterfaceRecordDO> list);

    /**
     * 批量新增调用云仓接口记录表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SaleCloudInterfaceRecordDO> list);
}