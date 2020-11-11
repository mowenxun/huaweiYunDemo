package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SaleOutTaskDO;

import com.deepexi.dd.middle.order.domain.query.SaleOutTaskMiddleRequestQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * SaleOutTaskMapper
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
@Mapper
public interface SaleOutTaskMapper extends BaseMapper<SaleOutTaskDO> {

    /**
     * 查询销售出库单（出库任务单）
     *
     * @param query
     * @return
     */
    List<SaleOutTaskDO> findAll(SaleOutTaskMiddleRequestQuery query);

    /**
     * 删除销售出库单（出库任务单）
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新销售出库单（出库任务单）
     *
     * @param list
     * @return
     */
    int updateBatch(List<SaleOutTaskDO> list);

    /**
     * 批量新增销售出库单（出库任务单）
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SaleOutTaskDO> list);
    /**
     * @param day
     * @Description: 根据天数查出自动签收出库单的数据.
     * @Param: [day]
     * @return: java.util.List<java.lang.Long>
     * @Author: SongTao
     * @Date: 2020/7/21
     */
    List<Long> selectIdListForAutoSign(@Param("day") Integer day);
    /**
     * 根据订单ID查询列表
     * @param saleOrderId
     * @return
     */
    List<SaleOutTaskDO> findSaleOutTaskByIds(@Param("saleOrderId")List<Long> saleOrderId);
}