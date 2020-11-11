package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SaleOrderInfoDO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoAmountEditDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoPayOrderCodeEditDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoStatusEditRequestDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderInfoQuery;
import com.deepexi.dd.middle.order.domain.query.SaleOrderInfoResQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SaleOrderInfoMapper
 *
 * @author admin
 * @version 1.0
 * @date Tue Jun 30 11:43:59 CST 2020
 */
@Mapper
public interface SaleOrderInfoMapper extends BaseMapper<SaleOrderInfoDO> {

    /**
     * 查询销售订单表
     *
     * @param query
     * @return
     */
    List<SaleOrderInfoDO> findAll(SaleOrderInfoQuery query);

    /**
     * 删除销售订单表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新销售订单表
     *
     * @param list
     * @return
     */
    int updateBatch(List<SaleOrderInfoDO> list);

    /**
     * 批量新增销售订单表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SaleOrderInfoDO> list);

    int updateOrderStatus(@Param("editStatusObj") SaleOrderInfoStatusEditRequestDTO saleOrderInfoStatusEditRequestDTO);

    /**
     * 更新订单状态和金额
     *
     * @param saleOrderInfoAmountEditDTO
     * @return
     */
    public int updateOrderAmount(@Param("dto") SaleOrderInfoAmountEditDTO saleOrderInfoAmountEditDTO);

    Boolean updatePayOrderCode(@Param("dto") SaleOrderInfoPayOrderCodeEditDTO saleOrderInfoPayOrderCodeEditDTO);

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