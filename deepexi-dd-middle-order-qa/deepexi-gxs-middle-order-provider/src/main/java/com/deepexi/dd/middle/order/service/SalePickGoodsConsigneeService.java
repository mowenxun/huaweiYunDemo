package com.deepexi.dd.middle.order.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.deepexi.dd.middle.order.domain.SalePickGoodsConsigneeDO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsConsigneeDTO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsConsigneeQuery;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsConsigneeResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsConsigneeRequestQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * SalePickGoodsConsigneeService
 *
 * @author admin
 * @date Mon Aug 24 16:35:15 CST 2020
 * @version 1.0
 */
public interface SalePickGoodsConsigneeService {


    /**
     * 查询提货计划收货地址信息表列表
     *
     * @return
     */
    List<SalePickGoodsConsigneeDTO> listSalePickGoodsConsignees(SalePickGoodsConsigneeQuery query);

    /**
     * 分页查询提货计划收货地址信息表列表
     *
     * @return
     */
    PageBean<SalePickGoodsConsigneeDTO> listSalePickGoodsConsigneesPage(SalePickGoodsConsigneeQuery query);

    /**
     * 根据ID删除提货计划收货地址信息表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增提货计划收货地址信息表
     *
     * @param record
     * @return
     */
    SalePickGoodsConsigneeDTO insert(SalePickGoodsConsigneeDTO record);

    /**
     * 查询提货计划收货地址信息表详情
     *
     * @param id
     * @return
     */
    SalePickGoodsConsigneeDTO selectById(Long id);


    /**
     * 根据ID修改提货计划收货地址信息表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SalePickGoodsConsigneeDTO record);


    /**
     * 根据Wrappe修哈提货订单关联的收货地址信息
     */
    Integer updateByWrapper(SalePickGoodsConsigneeDTO record, Wrapper<SalePickGoodsConsigneeDO> updateWrapper);

    SalePickGoodsConsigneeResponseDTO selectByPickCode(SalePickGoodsConsigneeRequestQuery salePickGoodsConsigneeRequestQuery);
}