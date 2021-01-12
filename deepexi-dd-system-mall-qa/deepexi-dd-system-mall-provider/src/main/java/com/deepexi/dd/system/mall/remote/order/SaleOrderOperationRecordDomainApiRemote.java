package com.deepexi.dd.system.mall.remote.order;

import com.deepexi.dd.domain.transaction.api.SaleOrderOperationRecordDomainApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/09/11/18:00
 * @Description:
 */
@FeignClient(value = "${eureka.client.transaction.name}")
public interface SaleOrderOperationRecordDomainApiRemote extends SaleOrderOperationRecordDomainApi {
}
