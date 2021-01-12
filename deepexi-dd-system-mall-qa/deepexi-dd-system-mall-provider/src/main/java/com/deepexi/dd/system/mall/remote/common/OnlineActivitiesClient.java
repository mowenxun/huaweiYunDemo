package com.deepexi.dd.system.mall.remote.common;

import com.deepexi.dd.domain.common.api.OnlineActivitiesApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName OnlineActivitiesClient
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-19
 * @Version 1.0
 **/
@FeignClient(value = "${deepexi.dd.domain.common.name}")
public interface OnlineActivitiesClient extends OnlineActivitiesApi {
}
