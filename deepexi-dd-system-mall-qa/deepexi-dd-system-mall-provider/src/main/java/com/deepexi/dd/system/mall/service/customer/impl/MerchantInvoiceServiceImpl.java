package com.deepexi.dd.system.mall.service.customer.impl;

import com.alibaba.fastjson.JSONObject;
import com.deepexi.dd.domain.common.constant.CommonConstant;
import com.deepexi.dd.domain.common.util.CommonUtils;
import com.deepexi.dd.system.mall.domain.vo.customer.MerchantInvoiceResponseVO;
import com.deepexi.dd.system.mall.enums.ResultEnum;
import com.deepexi.dd.system.mall.remote.customer.MerchantInvoiceClient;
import com.deepexi.dd.system.mall.service.customer.BaseService;
import com.deepexi.dd.system.mall.service.customer.MerchantInvoiceService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import domain.dto.MerchantInvoiceCreateRequestDTO;
import domain.dto.MerchantInvoiceResponseDTO;
import domain.dto.MerchantInvoiceUpdateRequestDTO;
import domain.query.MerchantInvoiceRequestQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName MerchantInvoiceServiceImpl
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
@Service
public class MerchantInvoiceServiceImpl extends BaseService implements MerchantInvoiceService {

    private static final Logger LOG = LoggerFactory.getLogger(MerchantInvoiceServiceImpl.class);
    @Autowired
    private MerchantInvoiceClient invoiceClient;

    public List<MerchantInvoiceResponseVO> findAll(MerchantInvoiceRequestQuery query) throws Exception {
        initQuery(query);
        Payload<List<MerchantInvoiceResponseDTO>> payload = invoiceClient.findAll(query.clone(MerchantInvoiceRequestQuery.class));
        if (!"0".equals(payload.getCode())) {
            throw new ApplicationException(payload.getMsg());
        }
        return GeneralConvertUtils.convert2List(payload.getPayload(), MerchantInvoiceResponseVO.class);
    }

    private void initQuery(MerchantInvoiceRequestQuery query) throws Exception {
        query.setIsolationId(getIsolationId());
        query.setIsCustomer(Boolean.FALSE);
    }

    public MerchantInvoiceResponseVO detail(Long pk) throws Exception {
        Payload<MerchantInvoiceResponseDTO> payload = invoiceClient.detail(pk);
        if (!"0".equals(payload.getCode())) {
            throw new ApplicationException(payload.getMsg());
        }
        return GeneralConvertUtils.conv(payload.getPayload(), MerchantInvoiceResponseVO.class);
    }

    public Payload<Boolean> update(MerchantInvoiceUpdateRequestDTO dto) throws Exception {
        if (!checkMobile(dto.getContactPhone())) {
            throw new ApplicationException(ResultEnum.CONTACT_NUMBER_ERROR);
        }
        initModify(dto);
        Payload<Boolean> payload = invoiceClient.update(dto.clone(MerchantInvoiceUpdateRequestDTO.class));
        if (!"0".equals(payload.getCode())) {
            throw new ApplicationException(payload.getMsg());
        }
        return payload;
    }

    /**
     * @Description: 校验电话号码.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/7/27
     */
    private boolean checkMobile(String mobile) {
        if (CommonUtils.isNotEmpty(mobile)) {
            return CommonUtils.checkExpression(mobile, CommonConstant.MOBILE_PATTERN) ||
                    CommonUtils.checkExpression(mobile, CommonConstant.LANDLINE_PATTERN) ||
                    CommonUtils.checkExpression(mobile, CommonConstant.AREA_LANDLINE_PATTERN);
        }
        return true;
    }

    public MerchantInvoiceResponseVO create(MerchantInvoiceCreateRequestDTO dto) throws Exception {
        if (!checkMobile(dto.getContactPhone())) {
            throw new ApplicationException(ResultEnum.CONTACT_NUMBER_ERROR);
        }
        initCreate(dto);
        LOG.info(JSONObject.toJSONString(dto));
        Payload<MerchantInvoiceResponseDTO> payload = invoiceClient.create(dto.clone(MerchantInvoiceCreateRequestDTO.class));
        if (!"0".equals(payload.getCode())) {
            throw new ApplicationException(payload.getMsg());
        }
        return GeneralConvertUtils.conv(payload.getPayload(), MerchantInvoiceResponseVO.class);
    }

    public Payload<Boolean> delete(Long id) throws Exception {
        Payload<Boolean> payload = invoiceClient.delete(id);
        if (!"0".equals(payload.getCode())) {
            throw new ApplicationException(payload.getMsg());
        }
        return payload;
    }
}
