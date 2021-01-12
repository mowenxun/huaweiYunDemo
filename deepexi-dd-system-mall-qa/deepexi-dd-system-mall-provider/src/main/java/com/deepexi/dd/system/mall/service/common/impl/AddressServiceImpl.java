package com.deepexi.dd.system.mall.service.common.impl;

import com.deepexi.dd.domain.common.domain.dto.AddressResponseDTO;
import com.deepexi.dd.domain.common.domain.query.AddressRequestQuery;
import com.deepexi.dd.system.mall.remote.common.AddressClient;
import com.deepexi.dd.system.mall.service.common.AddressService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName AddressServiceImpl
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-25
 * @Version 1.0
 **/
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressClient addressClient;

    /**
     * @Description: 查询地理信息.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/7/25
     */
    @Override
    public List<AddressResponseDTO> listAddresss(AddressRequestQuery clone) throws Exception {
        Payload<List<AddressResponseDTO>> payload = addressClient.listAddresss(clone);
        return GeneralConvertUtils.convert2List(payload.getPayload(), AddressResponseDTO.class);
    }

}
