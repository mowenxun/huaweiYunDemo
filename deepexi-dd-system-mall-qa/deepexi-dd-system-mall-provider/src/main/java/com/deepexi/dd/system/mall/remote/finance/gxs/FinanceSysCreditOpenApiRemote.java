package com.deepexi.dd.system.mall.remote.finance.gxs;

import com.deepexi.dd.domain.finance.api.FinanceCreditOpenApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/26/19:00
 * @Description:
 */
@FeignClient(value = "${deepexi.dd.domain.finance.name}")
public interface FinanceSysCreditOpenApiRemote extends FinanceCreditOpenApi {
}
