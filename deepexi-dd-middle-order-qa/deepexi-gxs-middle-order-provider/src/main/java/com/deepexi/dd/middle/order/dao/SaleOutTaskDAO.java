package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.SaleOutTaskDO;
import com.deepexi.dd.middle.order.domain.query.SaleOutTaskMiddleRequestQuery;

import java.util.List;


/**
 * SaleOutTaskDAO
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
public interface SaleOutTaskDAO  extends IService<SaleOutTaskDO> {

    /**
     * 查询销售出库单（出库任务单）列表}
     *
     * @return
     */
    List<SaleOutTaskDO> listSaleOutTasks(SaleOutTaskMiddleRequestQuery query);

    /**
     * 根据ID删除销售出库单（出库任务单）
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增销售出库单（出库任务单）
     *
     * @param record
     * @return
     */
    int insert(SaleOutTaskDO record);

    /**
     * 查询销售出库单（出库任务单）详情
     *
     * @param id
     * @return
     */
    SaleOutTaskDO selectById(Long id);

    /**
     * 根据ID修改销售出库单（出库任务单）
     *
     * @param record
     * @return
     */
//    int updateById(SaleOutTaskDO record);
    /**
     * @Description:  根据天数查出自动签收出库单的数据.
     * @Param: [day]
     * @return: java.util.List<java.lang.Long>
     * @Author: SongTao
     * @Date: 2020/7/21
     */
    List<Long> selectIdListForAutoSign(Integer day);

    /**
     * 根据订单ID查询列表
     * @param saleOrderId
     * @return
     */
    List<SaleOutTaskDO> findSaleOutTaskByIds(List<Long> saleOrderId);
}