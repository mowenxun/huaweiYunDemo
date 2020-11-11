package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.SaleOrderInfoDO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoAmountEditDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoPayOrderCodeEditDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoStatusEditRequestDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderInfoQuery;
import com.deepexi.dd.middle.order.domain.query.SaleOrderInfoResQuery;

import java.util.List;


/**
 * SaleOrderInfoDAO
 *
 * @author admin
 * @version 1.0
 * @date Tue Jun 30 11:43:59 CST 2020
 */
public interface SaleOrderInfoDAO extends IService<SaleOrderInfoDO> {

    /**
     * 查询销售订单表列表}
     *
     * @return
     */
    List<SaleOrderInfoDO> listSaleOrderInfos(SaleOrderInfoQuery query);

    /**
     * 根据ID删除销售订单表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增销售订单表
     *
     * @param record
     * @return
     */
    int insert(SaleOrderInfoDO record);

    /**
     * 查询销售订单表详情
     *
     * @param id
     * @return
     */
    SaleOrderInfoDO selectById(Long id);

    /**
     * 根据ID修改销售订单表
     *
     * @param record
     * @return
     */
//    int updateById(SaleOrderInfoDO record);


    int updateOrderStatus(SaleOrderInfoStatusEditRequestDTO saleOrderInfoStatusEditRequestDTO);

    /**
     * 更新订单金额状态
     *
     * @param saleOrderInfoAmountEditDTO
     * @return
     */
    int updateOrderAmount(SaleOrderInfoAmountEditDTO saleOrderInfoAmountEditDTO);

    Boolean updatePayOrderCode(SaleOrderInfoPayOrderCodeEditDTO saleOrderInfoPayOrderCodeEditDTO);

    /**
     * 合同, 项目专用查询列表
     *
     * @param query
     * @return
     */
    List<SaleOrderInfoResponseDTO> findList(SaleOrderInfoResQuery query);

    /**
     * 获取上月的计划订单
     * @return
     */
    List<SaleOrderInfoDO> getPreMonthPlanOrder();
}