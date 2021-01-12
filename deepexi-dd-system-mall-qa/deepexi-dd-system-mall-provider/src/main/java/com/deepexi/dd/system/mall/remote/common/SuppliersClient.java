package com.deepexi.dd.system.mall.remote.common;

import com.deepexi.dd.domain.common.api.SuppliersApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName SuppliersClient
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-28
 * @Version 1.0
 **/
@FeignClient(value = "${deepexi.dd.domain.common.name}")
public interface SuppliersClient extends SuppliersApi {
}
