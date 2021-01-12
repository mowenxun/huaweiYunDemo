package com.deepexi.dd.system.mall.service.customer;

import com.deepexi.dd.system.mall.domain.dto.customer.MerchantAddressCreateAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.customer.MerchantAddressDeleteAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.customer.MerchantAddressUpdateAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.query.customer.MerchantAddressAdminRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.customer.MerchantAddressResponseVO;
import com.deepexi.util.config.Payload;
import domain.query.MerchantAddressRequestQuery;

import java.util.List;

/**
 * @ClassName MerchantAddressService
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
public interface MerchantAddressService {

    List<MerchantAddressResponseVO> findList(MerchantAddressRequestQuery query) throws Exception;

    MerchantAddressResponseVO create(MerchantAddressCreateAdminRequestDTO createDTO) throws Exception;

    Payload<Boolean> update(MerchantAddressUpdateAdminRequestDTO updateDTO) throws Exception;

    Payload<Boolean> delete(MerchantAddressDeleteAdminRequestDTO deleteDTO) throws Exception;

    MerchantAddressResponseVO detail(Long id) throws Exception;
}
