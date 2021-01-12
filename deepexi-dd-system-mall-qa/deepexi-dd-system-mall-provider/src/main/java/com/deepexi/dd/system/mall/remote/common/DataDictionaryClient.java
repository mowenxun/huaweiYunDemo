package com.deepexi.dd.system.mall.remote.common;

import com.deepexi.dd.domain.common.api.DataDictionaryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName DataDictionaryClient
 * @Author mumu
 * @Date 2020-07-25
 * @Version 1.0
 **/
@FeignClient(value = "${deepexi.dd.domain.common.name}")
public interface DataDictionaryClient extends DataDictionaryApi {
}
