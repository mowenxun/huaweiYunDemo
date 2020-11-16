package com.deepexi.dd.domain.transaction.remote.order.log;

import com.deepexi.dd.middle.order.api.SalePickOrderYunLogApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/08/28/9:56
 * @Description:
 */
@FeignClient(value = "${deepexi.dd.middle.order.name}")
public interface SalePickOrderYunLogApiRemote extends SalePickOrderYunLogApi {
}
