package com.deepexi.dd.system.mall.remote.common;

import com.deepexi.dd.domain.common.api.AddressApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName AddressClient
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-25
 * @Version 1.0
 **/
@FeignClient(value = "${deepexi.dd.domain.common.name}")
public interface AddressClient extends AddressApi {
}
