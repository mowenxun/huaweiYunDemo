package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.SaleOutTaskDetailInfoDO;
import com.deepexi.dd.middle.order.domain.query.SaleOutTaskDetailInfoQuery;

import java.util.List;

/**
 * @ClassName SaleOutTaskDetailInfoDAO
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-07
 * @Version 1.0
 **/
public interface SaleOutTaskDetailInfoDAO  extends IService<SaleOutTaskDetailInfoDO> {
    /**
     * 查询销售出库商品明细表列表}
     *
     * @return
     */
    List<SaleOutTaskDetailInfoDO> listSaleOutTaskDetailInfos(SaleOutTaskDetailInfoQuery query);

    /**
     * 根据ID删除销售出库商品明细表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增销售出库商品明细表
     *
     * @param record
     * @return
     */
    int insert(SaleOutTaskDetailInfoDO record);

    /**
     * 查询销售出库商品明细表详情
     *
     * @param id
     * @return
     */
    SaleOutTaskDetailInfoDO selectById(Long id);




}
