package com.deepexi.dd.system.mall.service.customer;

import com.deepexi.dd.system.mall.domain.vo.customer.MerchantInvoiceResponseVO;
import com.deepexi.util.config.Payload;
import domain.dto.MerchantInvoiceCreateRequestDTO;
import domain.dto.MerchantInvoiceResponseDTO;
import domain.dto.MerchantInvoiceUpdateRequestDTO;
import domain.query.MerchantInvoiceRequestQuery;

import java.util.List;

/**
 * @ClassName MerchantInvoiceService
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
public interface MerchantInvoiceService {

    List<MerchantInvoiceResponseVO> findAll(MerchantInvoiceRequestQuery query) throws Exception;

    MerchantInvoiceResponseVO detail(Long pk) throws Exception;

    Payload<Boolean> update(MerchantInvoiceUpdateRequestDTO dto) throws Exception;

    MerchantInvoiceResponseVO create(MerchantInvoiceCreateRequestDTO dto) throws Exception;

    Payload<Boolean> delete(Long id) throws Exception;
}
