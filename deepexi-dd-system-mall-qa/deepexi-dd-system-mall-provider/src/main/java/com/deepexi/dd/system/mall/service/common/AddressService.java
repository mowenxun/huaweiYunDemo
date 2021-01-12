package com.deepexi.dd.system.mall.service.common;

import com.deepexi.dd.domain.common.domain.dto.AddressResponseDTO;
import com.deepexi.dd.domain.common.domain.query.AddressRequestQuery;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @ClassName AddressService
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-25
 * @Version 1.0
 **/
public interface AddressService {
    
    /**
    * @Description: 查询地理信息.
    * @Param:
    * @return: 
    * @Author: JiangHaoWei
    * @Date: 2020/7/25
    */
    List<AddressResponseDTO> listAddresss(AddressRequestQuery clone) throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException, IOException, Exception;
}
