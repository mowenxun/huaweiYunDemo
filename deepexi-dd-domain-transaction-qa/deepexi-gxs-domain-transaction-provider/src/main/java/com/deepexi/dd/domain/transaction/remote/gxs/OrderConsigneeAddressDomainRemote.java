package com.deepexi.dd.domain.transaction.remote.gxs;

import com.deepexi.dd.middle.order.api.OrderConsigneeAddressApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/14/16:37
 * @Description:
 */
//@FeignClient(name = "${deepexi.dd.middle.order.name}",url = "127.0.0.1:8501")
@FeignClient(name = "${deepexi.dd.middle.order.name}")
public interface OrderConsigneeAddressDomainRemote extends OrderConsigneeAddressApi {
}
