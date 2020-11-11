package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.deepexi.dd.middle.order.domain.SalePickGoodsConsigneeDO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsConsigneeQuery;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsConsigneeResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsConsigneeRequestQuery;

import java.util.List;


/**
 * SalePickGoodsConsigneeDAO
 *
 * @author admin
 * @date Mon Aug 24 16:35:15 CST 2020
 * @version 1.0
 */
public interface SalePickGoodsConsigneeDAO {

    /**
     * 查询提货计划收货地址信息表列表}
     *
     * @return
     */
    List<SalePickGoodsConsigneeDO> listSalePickGoodsConsignees(SalePickGoodsConsigneeQuery query);

    /**
     * 根据ID删除提货计划收货地址信息表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增提货计划收货地址信息表
     *
     * @param record
     * @return
     */
    int insert(SalePickGoodsConsigneeDO record);

    /**
     * 查询提货计划收货地址信息表详情
     *
     * @param id
     * @return
     */
    SalePickGoodsConsigneeDO selectById(Long id);

    /**
     * 根据ID修改提货计划收货地址信息表
     *
     * @param record
     * @return
     */
    int updateById(SalePickGoodsConsigneeDO record);

    /**
     * 根据ID修改提货计划收货地址信息表
     *
     * @param record
     * @return
     */
    int updateByWrapper(SalePickGoodsConsigneeDO record,Wrapper<SalePickGoodsConsigneeDO> updateWrapper);

    SalePickGoodsConsigneeResponseDTO selectByPickCode(SalePickGoodsConsigneeRequestQuery salePickGoodsConsigneeRequestQuery);
}