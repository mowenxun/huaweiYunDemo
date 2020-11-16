package com.deepexi.dd.domain.transaction.remote.common;

import com.deepexi.dd.domain.common.api.OnlineActivitiesApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-21 20:37
 */
@FeignClient(value = "${deepexi.dd.domain.common.name}")
public interface OnlineActivitiesClient extends OnlineActivitiesApi {
}
