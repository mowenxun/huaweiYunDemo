package com.deepexi.dd.system.mall.remote.commodity;

import com.deepexi.commodity.api.EsCommodityApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/12/16/14:27
 * @Description:
 */
@FeignClient(value = "deepexi-domain-commodity")
public interface EsCommodityApiSysRemote extends EsCommodityApi {
}
