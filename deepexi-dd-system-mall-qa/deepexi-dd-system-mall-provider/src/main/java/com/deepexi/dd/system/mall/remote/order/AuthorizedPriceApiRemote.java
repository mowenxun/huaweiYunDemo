package com.deepexi.dd.system.mall.remote.order;

import com.deepexi.dd.domain.transaction.api.AuthorizedPriceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/08/28/19:44
 * @Description:
 */
@FeignClient(value = "${eureka.client.transaction.name}")
public interface AuthorizedPriceApiRemote extends AuthorizedPriceApi {
}
