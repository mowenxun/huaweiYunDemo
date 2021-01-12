package com.deepexi.dd.system.mall.service.customer;

import com.deepexi.dd.system.mall.domain.dto.customer.MerchantDocumentCreateAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.customer.MerchantDocumentUpdateAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.query.customer.MerchantDocumentAdminRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.customer.MerchantDocumentResponseVO;
import com.deepexi.util.config.Payload;

import java.util.List;

/**
 * @ClassName MerchantDocumentService
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
public interface MerchantDocumentService {

    MerchantDocumentResponseVO create(MerchantDocumentCreateAdminRequestDTO vo) throws Exception;

    List<MerchantDocumentResponseVO> findAll(MerchantDocumentAdminRequestQuery query) throws Exception;

    MerchantDocumentResponseVO detail(Long id) throws Exception;

    Payload<Boolean> update(MerchantDocumentUpdateAdminRequestDTO vo) throws Exception;

    Payload<Boolean> delete(Long id) throws Exception;
}
