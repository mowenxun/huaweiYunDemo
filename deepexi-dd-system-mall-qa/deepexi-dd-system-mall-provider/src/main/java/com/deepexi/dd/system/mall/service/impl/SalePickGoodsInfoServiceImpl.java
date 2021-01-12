package com.deepexi.dd.system.mall.service.impl;

import com.deepexi.dd.domain.transaction.domain.dto.PickGoodStoreHouseReq;
import com.deepexi.dd.domain.transaction.domain.dto.PickGoodStoreHouseResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SalePickGoodsInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SalePickGoodsPlanInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.examine.ExaminePickGoodsInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.examine.ExamineRejectInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.operation.center.SalePickGoodsInfoDetailResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.operation.center.SalePickGoodsInfoOperationResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskSignRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickDeliveryInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickGoodsDetailInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.SalePickGoodsInfoReqtQuery;
import com.deepexi.dd.middle.order.domain.query.operation.center.SalePickGoodsInfoOperationRequestQuery;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsOrderSkuRequestQuery;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsOrderSkuRequestQuery;
import com.deepexi.dd.system.mall.domain.dto.SaleOutTaskInfoSignRequestDTO;
import com.deepexi.dd.system.mall.remote.order.SalePickGoodsInfoRemote;
import com.deepexi.dd.system.mall.service.SalePickGoodsInfoService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * SalePickGoodsInfoServiceImpl
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 12 19:18:27 CST 2020
 */
@Service
@Slf4j
public class SalePickGoodsInfoServiceImpl implements SalePickGoodsInfoService {


    @Autowired
    SalePickGoodsInfoRemote salePickGoodsInfoRemote;

    @Override
    public Payload<SalePickGoodsInfoResponseDTO> createPickGoods(SalePickGoodsPlanInfoRequestDTO record) throws Exception {
        Payload<SalePickGoodsInfoResponseDTO> payload = salePickGoodsInfoRemote.createPickGoods(record);
        return payload;
    }


    @Override
    public Payload<PageBean<SalePickGoodsInfoResponseDTO>> listPage(SalePickGoodsInfoReqtQuery query) throws Exception {
        if(query.getCreateDayBeginStr() != null){
            log.info("query.getCreateDayBeginStr():"+query.getCreateDayBeginStr());
        }
        if(query.getCreateDayEndStr() != null){
            log.info("query.getCreateDayEndStr():"+query.getCreateDayEndStr());
        }
        Payload<PageBean<SalePickGoodsInfoResponseDTO>> pageBeanPayload = salePickGoodsInfoRemote.listPage(query);
        return pageBeanPayload;
    }


    @Override
    public Payload<SalePickGoodsInfoResponseDTO> editPickGoods(SalePickGoodsPlanInfoRequestDTO record) throws Exception {
        Payload<SalePickGoodsInfoResponseDTO> payload = salePickGoodsInfoRemote.editPickGoods(record);
        return payload;

    }




    /**
     * 取消提货订单
     * @param record
     * @return
     */
    @Override
    public Payload<Boolean> cancelPickGoods(SalePickGoodsPlanInfoRequestDTO record) throws Exception {
        Payload<Boolean> payload = salePickGoodsInfoRemote.cancelPickGoods(record);
        return payload;
    }


    /**
     * 关闭提货订单
     * @param record
     * @return
     */
    @Override
    public Payload<Boolean> closePickGoods(SalePickGoodsPlanInfoRequestDTO record) throws Exception {
        Payload<Boolean> payload = salePickGoodsInfoRemote.closePickGoods(record);
        return payload;
    }


    /**
     * @param dto
     * @Description: 提货单签收.
     * @Param: [dto]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/19
     */
    @Override
    public Boolean sign(SaleOutTaskInfoSignRequestDTO dto) throws Exception {
        Payload<Boolean> payload = salePickGoodsInfoRemote.sign(dto.clone(SaleOutTaskSignRequestDTO.class));
        return payload.getPayload();
    }

    /**
     * @param dto
     * @Description: 提货计划中的发货.
     * @Param: [requestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/13
     */
    @Override
    public Boolean pickPlanDeliveryGoods(SalePickDeliveryInfoRequestDTO dto) throws Exception {
        Payload<Boolean> payload = salePickGoodsInfoRemote.pickPlanDeliveryGoods(dto);
        return payload.getPayload();
    }

    @Override
    public Payload<PageBean<SalePickGoodsOrderSkuResponseDTO>> searchPickGoodsList(Long saleOrderId, SalePickGoodsOrderSkuRequestQuery salePickGoodsOrderSkuRequestQuery) throws Exception {
        return salePickGoodsInfoRemote.searchPickGoodsList(saleOrderId,salePickGoodsOrderSkuRequestQuery);
    }

    @Override
    public SalePickGoodsInfoDetailResponseDTO getDetailById(Long id) throws Exception {
        Payload<SalePickGoodsInfoDetailResponseDTO> payload = salePickGoodsInfoRemote.getDetailById(id);
        return GeneralConvertUtils.conv(payload.getPayload(), SalePickGoodsInfoDetailResponseDTO.class);
    }

    @Override
    public PageBean<SalePickGoodsInfoOperationResponseDTO> listOperationPage(SalePickGoodsInfoOperationRequestQuery query) throws Exception {
        Payload<PageBean<SalePickGoodsInfoOperationResponseDTO>> payload = salePickGoodsInfoRemote.listOperationPage(query);
        return GeneralConvertUtils.convert2PageBean(payload.getPayload(), SalePickGoodsInfoOperationResponseDTO.class);
    }

    @Override
    public Boolean examinePass(ExaminePickGoodsInfoRequestDTO clone) throws Exception{
        Payload<Boolean> payload = salePickGoodsInfoRemote.examinePass(clone);
        return payload.getPayload();
    }

    @Override
    public Boolean examineReject(ExamineRejectInfoRequestDTO clone) throws Exception {
        Payload<Boolean> payload = salePickGoodsInfoRemote.examineReject(clone);
        return payload.getPayload();
    }

    @Override
    public Payload<Boolean> judgeGetAll() throws Exception {
        return salePickGoodsInfoRemote.judgeGetAll();
    }

    /**
     * @Description: 查询商品列表.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/11
     */
    @Override
    public List<SalePickGoodsDetailInfoResponseDTO> searchSkuDetailList(Long salePickGoodsId) throws Exception {
        Payload<List<SalePickGoodsDetailInfoResponseDTO>> payload = salePickGoodsInfoRemote.searchSkuDetailList(salePickGoodsId);
        return GeneralConvertUtils.convert2List(payload.getPayload(), SalePickGoodsDetailInfoResponseDTO.class);
    }

    @Override
    public Payload<PickGoodStoreHouseResponseDTO> getStorehouseList(PickGoodStoreHouseReq dto) throws Exception {
        return salePickGoodsInfoRemote.getStorehouseList(dto);
    }
}
