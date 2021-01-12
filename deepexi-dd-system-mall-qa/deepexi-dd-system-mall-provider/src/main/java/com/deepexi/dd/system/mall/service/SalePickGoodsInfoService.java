package com.deepexi.dd.system.mall.service;

import com.deepexi.dd.domain.transaction.domain.dto.PickGoodStoreHouseReq;
import com.deepexi.dd.domain.transaction.domain.dto.PickGoodStoreHouseResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SalePickGoodsInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SalePickGoodsPlanInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.examine.ExaminePickGoodsInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.examine.ExamineRejectInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.operation.center.SalePickGoodsInfoDetailResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.operation.center.SalePickGoodsInfoOperationResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickDeliveryInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickDeliveryInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickGoodsDetailInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.SalePickGoodsInfoReqtQuery;
import com.deepexi.dd.middle.order.domain.query.operation.center.SalePickGoodsInfoOperationRequestQuery;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsOrderSkuRequestQuery;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsOrderSkuRequestQuery;
import com.deepexi.dd.system.mall.domain.dto.SaleOutTaskInfoSignRequestDTO;
import com.deepexi.dd.system.mall.domain.query.saleorder.SalePickGoodsInfoOperationAdminReqeustQuery;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


/**
 * SalePickGoodsInfoService
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 12 19:18:27 CST 2020
 */
public interface SalePickGoodsInfoService {

    Payload<SalePickGoodsInfoResponseDTO> createPickGoods(SalePickGoodsPlanInfoRequestDTO record) throws Exception;


    Payload<PageBean<SalePickGoodsInfoResponseDTO>> listPage(SalePickGoodsInfoReqtQuery query) throws Exception;


    /**
     * 编辑并提交提货订单
     *
     * @param record
     * @return
     */
    Payload<SalePickGoodsInfoResponseDTO> editPickGoods(SalePickGoodsPlanInfoRequestDTO record) throws Exception;


    /**
     * @Description: 取消提货订单
     */
    Payload<Boolean> cancelPickGoods(SalePickGoodsPlanInfoRequestDTO record) throws Exception;

    /**
     * @Description: 关闭提货订单
     */
    Payload<Boolean> closePickGoods(SalePickGoodsPlanInfoRequestDTO record) throws Exception;

    /**
     * 查看详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SalePickGoodsInfoDetailResponseDTO getDetailById(Long id) throws Exception;

    /**
     * @Description: 提货单签收.
     * @Param: [dto]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/19
     */
    Boolean sign(SaleOutTaskInfoSignRequestDTO dto) throws Exception;

    /**
     * @Description: 提货计划中的发货.
     * @Param: [requestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/13
     */
    Boolean pickPlanDeliveryGoods(SalePickDeliveryInfoRequestDTO dto) throws Exception;

    /**
     * 通过订单Id查询订单的提货计划列表
     *
     * @param saleOrderId
     * @param salePickGoodsOrderSkuRequestQuery
     * @return
     * @throws Exception
     */
    Payload<PageBean<SalePickGoodsOrderSkuResponseDTO>> searchPickGoodsList(Long saleOrderId,
                                                                            SalePickGoodsOrderSkuRequestQuery salePickGoodsOrderSkuRequestQuery) throws Exception;

    /**
     * 运营中心分页查询提货单据订单列表
     * @param query
     * @return
     */
    PageBean<SalePickGoodsInfoOperationResponseDTO> listOperationPage(SalePickGoodsInfoOperationRequestQuery query) throws Exception;

    /**
     * 确认提货计划
     * @param clone
     * @return
     */
    Boolean examinePass(ExaminePickGoodsInfoRequestDTO clone) throws Exception;

    /**
     * 驳回提货计划
     * @param clone
     * @return
     */
    Boolean examineReject(ExamineRejectInfoRequestDTO clone) throws Exception;

    Payload<Boolean> judgeGetAll() throws Exception;

    /**
    * @Description: 查询商品列表.
    * @Param:
    * @return:
    * @Author: JiangHaoWei
    * @Date: 2020/9/11
    */
    List<SalePickGoodsDetailInfoResponseDTO> searchSkuDetailList(Long salePickGoodsId) throws Exception;


    /**
     * 获取仓库列表
     * @param dto
     * @return
     * @throws Exception
     */
    public Payload<PickGoodStoreHouseResponseDTO> getStorehouseList(PickGoodStoreHouseReq dto) throws Exception;

}