package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SalePickGoodsInfoDO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsInfoQuery;

import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderSkuDO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsInfoRequestQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * SalePickGoodsInfoMapper
 *
 * @author admin
 * @date Wed Aug 12 19:18:27 CST 2020
 * @version 1.0
 */
@Mapper
public interface SalePickGoodsInfoMapper extends BaseMapper<SalePickGoodsInfoDO> {

    /**
     * 查询提货单据表
     *
     * @param query
     * @return
     */
    List<SalePickGoodsInfoDO> findAll(SalePickGoodsInfoQuery query);

    /**
     * 删除提货单据表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新提货单据表
     *
     * @param list
     * @return
     */
    int updateBatch(List<SalePickGoodsInfoDO> list);

    /**
     * 批量新增提货单据表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SalePickGoodsInfoDO> list);

    int updateOrderStatus(@Param("editStatusObj") SaleOrderInfoStatusEditRequestDTO saleOrderInfoStatusEditRequestDTO);

    /**
     * 更新订单状态和金额
     * @param saleOrderInfoAmountEditDTO
     * @return
     */
    public int updateOrderAmount(@Param("dto") SalePickGoodsInfoAmountEditDTO saleOrderInfoAmountEditDTO);

    /**
     * 根据订单号查提货计划
     * @param saleOrderId
     * @return
     */
    List<SalePickGoodsOrderSkuResponseDTO> searchPickGoodsList(@Param("saleOrderId") Long saleOrderId);

    SalePickGoodsInfoResponseDTO selectByPickCode(SalePickGoodsInfoRequestQuery query);
}