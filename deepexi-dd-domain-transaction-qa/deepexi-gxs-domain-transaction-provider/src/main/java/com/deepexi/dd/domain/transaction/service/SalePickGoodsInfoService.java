package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.domain.transaction.controller.AppRunEnvForPick;
import com.deepexi.dd.domain.transaction.domain.dto.PickGoodStoreHouseReq;
import com.deepexi.dd.domain.transaction.domain.dto.PickGoodStoreHouseResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SalePickGoodsPlanInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SalePickGoodsInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.examine.ExaminePickGoodsInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.examine.ExamineRejectInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.operation.center.SalePickGoodsInfoDetailResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.operation.center.SalePickGoodsInfoOperationResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskSignRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickDeliveryInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickGoodsDetailInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsInfoRequestQuery;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsOrderSkuRequestQuery;
import com.deepexi.dd.middle.order.domain.query.operation.center.SalePickGoodsInfoOperationRequestQuery;
import com.deepexi.util.config.Payload;
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

    void savePickGoods(SalePickGoodsPlanInfoRequestDTO record);

    /**
     * 创建并提交收货订单
     * @param record
     * @return
     * @throws Exception
     */
    Payload<SalePickGoodsInfoResponseDTO> createPickGoods(SalePickGoodsPlanInfoRequestDTO record) throws Exception;


    /**
     * 通过订单Id查询订单的提货计划列表
     * @param saleOrderId
     * @param salePickGoodsOrderSkuRequestQuery
     * @return
     * @throws Exception
     */
    PageBean<SalePickGoodsOrderSkuResponseDTO> searchPickGoodsList(Long saleOrderId, SalePickGoodsOrderSkuRequestQuery salePickGoodsOrderSkuRequestQuery) throws Exception;

    /**
     * 在线订购提货订单列表
     * @param query
     * @return
     * @throws Exception
     */
    PageBean<SalePickGoodsInfoResponseDTO> listPage(SalePickGoodsInfoRequestQuery query) throws Exception;

    /**
     * 运营中心列表
     * @param query
     * @return
     * @throws Exception
     */
    PageBean<SalePickGoodsInfoOperationResponseDTO> listOperationPage(SalePickGoodsInfoOperationRequestQuery query) throws Exception;


    /**
     * 编辑并提交提货订单
     * @param record
     * @return
     */
    Payload<SalePickGoodsInfoResponseDTO> editPickGoods(SalePickGoodsPlanInfoRequestDTO record) throws Exception;

    /**
     * @Description:  审批通过.
     * @Param: [requestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/13
     */
    Boolean examinePass(ExaminePickGoodsInfoRequestDTO requestDTO) throws Exception;
    /**
     * @Description:  审批驳回.
     * @Param: [requestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/13
     */
    Boolean examineReject(ExamineRejectInfoRequestDTO requestDTO) throws Exception;
    /**
     * @Description:  提货计划中的发货.
     * @Param: [requestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/13
     */
    Boolean pickPlanDeliveryGoods(SalePickDeliveryInfoRequestDTO dto) throws Exception;


    /**
     * 查看详情
     * @param id
     * @return
     * @throws Exception
     */
    SalePickGoodsInfoDetailResponseDTO getDetailById(Long id)throws  Exception;

    /**
     * 根据提货单id获取与之关联的提货单sku
     */
    List<SalePickGoodsOrderSkuResponseDTO> getSkuByInfoId(Long id);

    /**
     * @Description:  提货单的签收.
     * @Param: [dto]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/18
     */
    Boolean sign(SaleOutTaskSignRequestDTO dto) throws Exception;


    /**
     * @Description:  取消提货订单
     * @Param: [dto]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/18
     */
    Payload<Boolean> cancelPickGoods(SalePickGoodsPlanInfoRequestDTO record) throws Exception;
    /**
     * @Description:  根据提货单ID查询发货的sku详情.
     * @Param: [salePickGoodsId]
     * @return: java.util.List<com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickGoodsDetailInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/21
     */
    List<SalePickGoodsDetailInfoResponseDTO> searchSkuDetailList(Long salePickGoodsId) throws Exception;


    /**
     * 根据pickCode查提货单
     * @param query
     * @return
     */
    SalePickGoodsInfoResponseDTO selectByPickCode(SalePickGoodsInfoRequestQuery query) throws Exception;


    /**
     * 用于临时判断当前登陆账号是否能获取所有产品线
     */
    Boolean judgeGetAll() throws Exception;

    /**
     * 根据产品线、供应商id、客户等等数据展示仓库列表
     * @param dto
     * @return
     */
    PickGoodStoreHouseResponseDTO getStorehouseList(PickGoodStoreHouseReq dto) throws Exception;

    AppRunEnvForPick getAppRuntimeEnvByToken();


}