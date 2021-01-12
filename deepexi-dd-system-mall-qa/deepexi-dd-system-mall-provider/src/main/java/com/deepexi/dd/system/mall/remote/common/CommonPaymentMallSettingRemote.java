package com.deepexi.dd.system.mall.remote.common;

import com.deepexi.dd.domain.common.api.CommonPaymentMallSettingApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author yy
 * @version 1.0
 * @date 2020-08-29 17:24
 */
@FeignClient(value = "${deepexi.dd.domain.common.name}")
public interface CommonPaymentMallSettingRemote extends CommonPaymentMallSettingApi {
}
