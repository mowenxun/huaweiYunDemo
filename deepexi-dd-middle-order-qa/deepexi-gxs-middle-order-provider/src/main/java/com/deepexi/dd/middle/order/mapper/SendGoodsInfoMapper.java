package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SendGoodsInfoDO;
import com.deepexi.dd.middle.order.domain.SendGoodsInfoQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * SendGoodsInfoMapper
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
@Mapper
public interface SendGoodsInfoMapper extends BaseMapper<SendGoodsInfoDO> {

    /**
     * 查询发货表
     *
     * @param query
     * @return
     */
    List<SendGoodsInfoDO> findAll(SendGoodsInfoQuery query);

    /**
     * 删除发货表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新发货表
     *
     * @param list
     * @return
     */
    int updateBatch(List<SendGoodsInfoDO> list);

    /**
     * 批量新增发货表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SendGoodsInfoDO> list);
}