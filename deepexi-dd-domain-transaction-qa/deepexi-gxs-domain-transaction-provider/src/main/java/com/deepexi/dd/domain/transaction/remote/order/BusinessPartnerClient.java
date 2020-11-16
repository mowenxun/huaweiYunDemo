package com.deepexi.dd.domain.transaction.remote.order;

import api.BusinessPartnerApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "${deepexi.dd.domain.customer.name}")
public interface BusinessPartnerClient extends BusinessPartnerApi {
}
