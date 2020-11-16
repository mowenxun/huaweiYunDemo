package com.deepexi.dd.domain.transaction.remote.gxs;

import com.deepexi.dd.middle.order.api.ShopOrderItemApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/14/10:25
 * @Description:
 */
@FeignClient(name = "${deepexi.dd.middle.order.name}")
public interface ShopOrderItemDomainRemote extends ShopOrderItemApi {
}
