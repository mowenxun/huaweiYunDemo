package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.SalePickGoodsInfoDTO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsInfoQuery;
import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsInfoRequestQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * SalePickGoodsInfoService
 *
 * @author admin
 * @date Wed Aug 12 19:18:27 CST 2020
 * @version 1.0
 */
public interface SalePickGoodsInfoService {


    /**
     * 查询提货单据表列表
     *
     * @return
     */
    List<SalePickGoodsInfoDTO> listSalePickGoodsInfos(SalePickGoodsInfoQuery query);

    /**
     * 分页查询提货单据表列表
     *
     * @return
     */
    PageBean<SalePickGoodsInfoDTO> listSalePickGoodsInfosPage(SalePickGoodsInfoQuery query);

    /**
     * 根据ID删除提货单据表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增提货单据表
     *
     * @param record
     * @return
     */
    SalePickGoodsInfoDTO insert(SalePickGoodsInfoDTO record);

    /**
     * 查询提货单据表详情
     *
     * @param id
     * @return
     */
    SalePickGoodsInfoDTO selectById(Long id);

    /**
     * 根据提货单号获取提货计划信息
     * @param code
     * @return
     */
    SalePickGoodsInfoResponseDTO getSalePickGoodsInfoByCode( String code);

    /**
     * 根据ID修改提货单据表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SalePickGoodsInfoDTO record);
    /**
     * @Description:  审批通过.
     * @Param: [dto]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/13
     */
    Boolean examinePass(SalePickGoodsInfoRequestDTO dto) throws Exception;
    /**
     * @param dto
     * @Description: 审批驳回.
     * @Param: [dto]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/8/30
     */
    Boolean examineReject(SalePickGoodsInfoRequestDTO dto) throws Exception;
    /**
     * @Description:  提货计划的发货.
     * @Param: [dto]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/8/15
     */
    Boolean pickPlanDeliveryGoods(SalePickDeliveryInfoMiddleRequestDTO dto) throws Exception;


    Boolean updateOrderStatus(SaleOrderInfoStatusEditRequestDTO saleOrderInfoStatusEditRequestDTO);

    /**
     * 更新提货计划金额
     * @param salePickGoodsInfoAmountEditDTO
     * @return
     */
    Boolean updateOrderAmount(SalePickGoodsInfoAmountEditDTO salePickGoodsInfoAmountEditDTO);

    /**
     * 根据订单号查提货计划详情
     * @param saleOrderId
     * @param page
     * @param size
     * @return
     */
    PageBean<SalePickGoodsOrderSkuResponseDTO> searchPickGoodsList(Long saleOrderId, Integer page, Integer size);

    /**
     * @Description:  提货单的出库单签收.
     * @Param: [saleOutTaskMiddleRequestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/18
     */
    Boolean sign(SaleOutTaskMiddleRequestDTO dto) throws Exception;

    SalePickGoodsInfoResponseDTO saveOrEdit(SalePickGoodsInfoTransferDTO dateDTO);

    SalePickGoodsInfoResponseDTO doCancelPickGoods(SalePickGoodsInfoTransferDTO dateDTO);

    /**
     * @Description:  根据提货单ID查询商品sku列表.
     * @Param: [id]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SalePickGoodsDetailInfoMiddleResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/21
     */
    List<SalePickGoodsDetailInfoMiddleResponseDTO> searchSkuDetailList(Long id) throws Exception;

    SalePickGoodsInfoResponseDTO selectByPickCode(SalePickGoodsInfoRequestQuery query);
}