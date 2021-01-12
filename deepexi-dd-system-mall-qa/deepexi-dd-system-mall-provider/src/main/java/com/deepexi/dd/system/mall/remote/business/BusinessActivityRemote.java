package com.deepexi.dd.system.mall.remote.business;

import com.deepexi.dd.domain.business.api.BusinessActivityApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description 活动相关远程调用接口
 * @Author hujinhua
 * @Date 2020/12/31 16:36
 */
@FeignClient(value = "${deepexi.dd.domain.business.name}")
public interface BusinessActivityRemote extends BusinessActivityApi {
}
