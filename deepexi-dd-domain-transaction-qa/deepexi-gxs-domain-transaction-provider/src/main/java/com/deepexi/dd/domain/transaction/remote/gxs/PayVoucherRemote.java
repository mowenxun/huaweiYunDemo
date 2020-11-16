package com.deepexi.dd.domain.transaction.remote.gxs;

import com.deepexi.dd.middle.order.api.PayVoucherApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-10-16 9:49
 */
//@FeignClient(name = "${deepexi.dd.middle.order.name}",url = "127.0.0.1:8501")
@FeignClient(name = "${deepexi.dd.middle.order.name}")
public interface PayVoucherRemote extends PayVoucherApi {
}
