package com.deepexi.dd.system.mall.service.customer.impl;

import com.deepexi.dd.domain.common.constant.CommonConstant;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.CommonUtils;
import com.deepexi.dd.middle.finance.domain.dto.FinanceBankAccountResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.customer.MerchantAddressCreateAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.customer.MerchantAddressDeleteAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.customer.MerchantAddressUpdateAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.vo.customer.CustomerWarehouseResponseVO;
import com.deepexi.dd.system.mall.domain.vo.customer.MerchantAddressResponseVO;
import com.deepexi.dd.system.mall.enums.ResultEnum;
import com.deepexi.dd.system.mall.remote.customer.CustomerPickUpWarehouseClient;
import com.deepexi.dd.system.mall.remote.customer.MerchantAddressClient;
import com.deepexi.dd.system.mall.service.customer.BaseService;
import com.deepexi.dd.system.mall.service.customer.CustomerPickUpWarehourseService;
import com.deepexi.dd.system.mall.service.customer.MerchantAddressService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import domain.dto.*;
import domain.query.MerchantAddressRequestQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName MerchantAddressServiceImpl
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
@Service
public class CustomerPickUpWarehouseServiceImpl extends BaseService implements CustomerPickUpWarehourseService {

    @Autowired
    private CustomerPickUpWarehouseClient customerPickUpWarehouseClient;

    @Override
    public List<CustomerWarehouseResponseVO> orglist(CustomerPickUpOrgWarehouseDomainQuery query) throws Exception{
        Payload<List<CustomerWarehouseResponseDTO>> payload = customerPickUpWarehouseClient.orglist(query);
        if (!"0".equals(payload.getCode())) {
            throw new ApplicationException(payload.getMsg());
        }
        return GeneralConvertUtils.convert2List(payload.getPayload(), CustomerWarehouseResponseVO.class);

    }
}
