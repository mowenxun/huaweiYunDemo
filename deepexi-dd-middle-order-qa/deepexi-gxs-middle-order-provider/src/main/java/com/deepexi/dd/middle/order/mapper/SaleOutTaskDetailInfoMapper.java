package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SaleOutTaskDetailInfoDO;
import com.deepexi.dd.middle.order.domain.query.SaleOutTaskDetailInfoQuery;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SaleOutTaskDetailInfoDO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @ClassName SaleOutTaskDetailInfoMapper
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-07
 * @Version 1.0
 **/
@Mapper
public interface SaleOutTaskDetailInfoMapper extends BaseMapper<SaleOutTaskDetailInfoDO> {
    /**
     * 查询销售出库商品明细表
     *
     * @param query
     * @return
     */
    List<SaleOutTaskDetailInfoDO> findAll(SaleOutTaskDetailInfoQuery query);

    /**
     * 删除销售出库商品明细表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新销售出库商品明细表
     *
     * @param list
     * @return
     */
    int updateBatch(List<SaleOutTaskDetailInfoDO> list);

    /**
     * 批量新增销售出库商品明细表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SaleOutTaskDetailInfoDO> list);
}
