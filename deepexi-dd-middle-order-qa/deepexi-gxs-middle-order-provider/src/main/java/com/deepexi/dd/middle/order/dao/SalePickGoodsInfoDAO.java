package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.*;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoStatusEditRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoAmountEditDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsInfoRequestQuery;

import java.util.List;


/**
 * SalePickGoodsInfoDAO
 *
 * @author admin
 * @date Wed Aug 12 19:18:27 CST 2020
 * @version 1.0
 */
public interface SalePickGoodsInfoDAO extends IService<SalePickGoodsInfoDO>{

    /**
     * 查询提货单据表列表}
     *
     * @return
     */
    List<SalePickGoodsInfoDO> listSalePickGoodsInfos(SalePickGoodsInfoQuery query);

    /**
     * 根据ID删除提货单据表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增提货单据表
     *
     * @param record
     * @return
     */
    int insert(SalePickGoodsInfoDO record);


    /**
     * 查询提货单据表详情
     *
     * @param id
     * @return
     */
    SalePickGoodsInfoDO selectById(Long id);

    /**
     * 根据ID修改提货单据表
     *
     * @param record
     * @return
     */
    boolean updateById(SalePickGoodsInfoDO record);

    int updateOrderStatus(SaleOrderInfoStatusEditRequestDTO saleOrderInfoStatusEditRequestDTO);

    /**
     * 更新提货计划金额
     * @param salePickGoodsInfoAmountEditDTO
     * @return
     */
    int updateOrderAmount(SalePickGoodsInfoAmountEditDTO salePickGoodsInfoAmountEditDTO);

    /**
     * 根据订单号查提货计划详情
     * @param saleOrderId
     * @return
     */
    List<SalePickGoodsOrderSkuResponseDTO> searchPickGoodsList(Long saleOrderId);

    SalePickGoodsInfoResponseDTO selectByPickCode(SalePickGoodsInfoRequestQuery query);
}