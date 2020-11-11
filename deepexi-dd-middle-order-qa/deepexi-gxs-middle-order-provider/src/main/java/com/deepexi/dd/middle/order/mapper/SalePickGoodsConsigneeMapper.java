package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SalePickGoodsConsigneeDO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsConsigneeQuery;

import com.deepexi.dd.middle.order.domain.query.SalePickGoodsConsigneeRequestQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * SalePickGoodsConsigneeMapper
 *
 * @author admin
 * @date Mon Aug 24 16:35:15 CST 2020
 * @version 1.0
 */
@Mapper
public interface SalePickGoodsConsigneeMapper extends BaseMapper<SalePickGoodsConsigneeDO> {

    /**
     * 查询提货计划收货地址信息表
     *
     * @param query
     * @return
     */
    List<SalePickGoodsConsigneeDO> findAll(SalePickGoodsConsigneeQuery query);

    /**
     * 删除提货计划收货地址信息表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新提货计划收货地址信息表
     *
     * @param list
     * @return
     */
    int updateBatch(List<SalePickGoodsConsigneeDO> list);

    /**
     * 批量新增提货计划收货地址信息表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SalePickGoodsConsigneeDO> list);

    SalePickGoodsConsigneeDO selectByPickCode(SalePickGoodsConsigneeRequestQuery salePickGoodsConsigneeRequestQuery);
}