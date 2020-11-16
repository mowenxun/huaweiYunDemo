package com.deepexi.dd.domain.transaction.remote.business;

import com.deepexi.dd.domain.business.api.BusinessCommodityRelationshipApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/30/17:06
 * @Description:
 */
@FeignClient(name = "deepexi.dd.domain.business")
public interface BusinessCommodityRelationshipDomainRemote extends BusinessCommodityRelationshipApi {
}
