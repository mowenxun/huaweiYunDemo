package com.deepexi.dd.system.mall.service.customer.impl;

import com.deepexi.dd.system.mall.domain.dto.customer.MerchantDocumentCreateAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.customer.MerchantDocumentUpdateAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.query.customer.MerchantDocumentAdminRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.customer.MerchantDocumentResponseVO;
import com.deepexi.dd.system.mall.remote.customer.MerchantDocumentClient;
import com.deepexi.dd.system.mall.service.customer.MerchantDocumentService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import domain.dto.MerchantDocumentCreateRequestDTO;
import domain.dto.MerchantDocumentResponseDTO;
import domain.dto.MerchantDocumentUpdateRequestDTO;
import domain.query.MerchantDocumentRequestQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName MerchantDocumentServiceImpl
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
@Service
public class MerchantDocumentServiceImpl implements MerchantDocumentService {

    @Autowired
    private MerchantDocumentClient documentClient;

    public MerchantDocumentResponseVO create(MerchantDocumentCreateAdminRequestDTO vo) throws Exception {
        Payload<MerchantDocumentResponseDTO> payload = documentClient.create(vo.clone(MerchantDocumentCreateRequestDTO.class));
        return GeneralConvertUtils.conv(payload.getPayload(), MerchantDocumentResponseVO.class);
    }

    public List<MerchantDocumentResponseVO> findAll(MerchantDocumentAdminRequestQuery query) throws Exception {
        Payload<List<MerchantDocumentResponseDTO>> payload = documentClient.findAll(query.clone(MerchantDocumentRequestQuery.class));
        return GeneralConvertUtils.convert2List(payload.getPayload(), MerchantDocumentResponseVO.class);
    }

    public MerchantDocumentResponseVO detail(Long id) throws Exception {
        Payload<MerchantDocumentResponseDTO> payload = documentClient.detail(id);
        return GeneralConvertUtils.conv(payload.getPayload(), MerchantDocumentResponseVO.class);
    }

    public Payload<Boolean> update(MerchantDocumentUpdateAdminRequestDTO vo) throws Exception {
        return documentClient.update(vo.clone(MerchantDocumentUpdateRequestDTO.class));
    }

    public Payload<Boolean> delete(Long id) throws Exception {
        return documentClient.delete(id);
    }
}
