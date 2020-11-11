package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskDetailInfoDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskDetailInfoMiddleRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskDetailInfoMiddleResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOutTaskDetailInfoQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * @ClassName SaleOutTaskDetailInfoService
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-07
 * @Version 1.0
 **/
public interface SaleOutTaskDetailInfoService {
    /**
     * @Description:  出库单新增时 新增关联的商品信息.
     * @Param: [dto]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/7
     */
    Boolean insert(List<SaleOutTaskDetailInfoMiddleRequestDTO> dto);
    /**
     * @Description: 根据动态字段查出库单关联的商品信息.
     * @Param: [field, value]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SaleOutTaskDetailInfoMiddleResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/10
     */
    List<SaleOutTaskDetailInfoMiddleResponseDTO> searchSaleOutTaskDetailInfo(Object field, Object value);



    /**
     * 查询销售出库商品明细表列表
     *
     * @return
     */
    List<SaleOutTaskDetailInfoDTO> listSaleOutTaskDetailInfos(SaleOutTaskDetailInfoQuery query);

    /**
     * 分页查询销售出库商品明细表列表
     *
     * @return
     */
    PageBean<SaleOutTaskDetailInfoDTO> listSaleOutTaskDetailInfosPage(SaleOutTaskDetailInfoQuery query);

    /**
     * 根据ID删除销售出库商品明细表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增销售出库商品明细表
     *
     * @param record
     * @return
     */
    SaleOutTaskDetailInfoDTO insert(SaleOutTaskDetailInfoDTO record);

    /**
     * 查询销售出库商品明细表详情
     *
     * @param id
     * @return
     */
    SaleOutTaskDetailInfoDTO selectById(Long id);


    /**
     * 根据ID修改销售出库商品明细表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SaleOutTaskDetailInfoDTO record);


}
