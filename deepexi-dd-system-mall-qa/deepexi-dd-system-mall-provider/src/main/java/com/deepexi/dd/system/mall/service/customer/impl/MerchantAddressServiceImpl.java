package com.deepexi.dd.system.mall.service.customer.impl;

import com.deepexi.dd.domain.common.constant.CommonConstant;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.CommonUtils;
import com.deepexi.dd.system.mall.domain.dto.customer.MerchantAddressCreateAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.customer.MerchantAddressDeleteAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.customer.MerchantAddressUpdateAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.query.customer.MerchantAddressAdminRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.customer.MerchantAddressResponseVO;
import com.deepexi.dd.system.mall.enums.ResultEnum;
import com.deepexi.dd.system.mall.remote.customer.MerchantAddressClient;
import com.deepexi.dd.system.mall.service.customer.BaseService;
import com.deepexi.dd.system.mall.service.customer.MerchantAddressService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import domain.dto.MerchantAddressCreateRequestDTO;
import domain.dto.MerchantAddressDeleteRequestDTO;
import domain.dto.MerchantAddressResponseDTO;
import domain.dto.MerchantAddressUpdateRequestDTO;
import domain.query.MerchantAddressRequestQuery;
import domain.query.MerchantInvoiceRequestQuery;
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
public class MerchantAddressServiceImpl extends BaseService implements MerchantAddressService {

    @Autowired
    private MerchantAddressClient addressClient;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    public List<MerchantAddressResponseVO> findList(MerchantAddressRequestQuery query) throws Exception {
        initQuery(query);
        Payload<List<MerchantAddressResponseDTO>> payload = addressClient.findList(query);
        if (!"0".equals(payload.getCode())) {
            throw new ApplicationException(payload.getMsg());
        }
        return GeneralConvertUtils.convert2List(payload.getPayload(), MerchantAddressResponseVO.class);
    }

    private void initQuery(MerchantAddressRequestQuery query) throws Exception {
        query.setIsolationId(getIsolationId());
        if (Objects.nonNull(query.getMerchantId())) {
            query.setIsCustomer(Boolean.TRUE);
        } else {
            query.setIsCustomer(Boolean.FALSE);
        }
    }

    public MerchantAddressResponseVO create(MerchantAddressCreateAdminRequestDTO createDTO) throws Exception {
        if (!checkMobile(createDTO.getPhone())) {
            throw new ApplicationException(ResultEnum.CONTACT_NUMBER_ERROR);
        }
        initCreate(createDTO);
        createDTO.setOrgId(Long.valueOf(getCurrenOrgId()));
        Payload<MerchantAddressResponseDTO> payload = addressClient.create(createDTO.clone(MerchantAddressCreateRequestDTO.class));
        if (!"0".equals(payload.getCode())) {
            throw new ApplicationException(payload.getMsg());
        }
        return GeneralConvertUtils.conv(payload.getPayload(), MerchantAddressResponseVO.class);
    }

    public Payload<Boolean> update(MerchantAddressUpdateAdminRequestDTO updateDTO) throws Exception {
        if (!checkMobile(updateDTO.getPhone())) {
            throw new ApplicationException(ResultEnum.CONTACT_NUMBER_ERROR);
        }
        initModify(updateDTO);
        Payload<Boolean> payload = addressClient.update(updateDTO.clone(MerchantAddressUpdateRequestDTO.class));
        if (!"0".equals(payload.getCode())) {
            throw new ApplicationException(payload.getMsg());
        }
        return payload;
    }

    public Payload<Boolean> delete(MerchantAddressDeleteAdminRequestDTO deleteDTO) throws Exception {
        deleteDTO.setTenantId(appRuntimeEnv.getTenantId());
        Payload<Boolean> payload = addressClient.delete(deleteDTO.clone(MerchantAddressDeleteRequestDTO.class));
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

    public MerchantAddressResponseVO detail(Long id) throws Exception {
        Payload<MerchantAddressResponseDTO> payload = addressClient.detail(id);
        if (!"0".equals(payload.getCode())) {
            throw new ApplicationException(payload.getMsg());
        }
        return GeneralConvertUtils.conv(payload.getPayload(), MerchantAddressResponseVO.class);
    }
}
