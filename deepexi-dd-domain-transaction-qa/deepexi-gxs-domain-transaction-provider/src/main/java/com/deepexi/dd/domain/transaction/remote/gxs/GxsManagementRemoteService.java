package com.deepexi.dd.domain.transaction.remote.gxs;

import com.deepexi.middle.merchant.api.GxsManagementApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author huanghuai
 * @date 2020/10/13
 */
@FeignClient("${deepexi.dd.middle.merchant.name}")
public interface GxsManagementRemoteService extends GxsManagementApi {

}
