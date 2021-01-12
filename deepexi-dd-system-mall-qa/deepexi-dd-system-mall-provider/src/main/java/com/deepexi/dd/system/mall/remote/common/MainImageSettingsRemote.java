package com.deepexi.dd.system.mall.remote.common;

import com.deepexi.dd.domain.common.api.CommonMainImageSettingsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhouquan
 * @version 1.0
 * @date 2020-07-24 14:49
 */
@FeignClient(value = "${deepexi.dd.domain.common.name}")
public interface MainImageSettingsRemote extends CommonMainImageSettingsApi {
}
