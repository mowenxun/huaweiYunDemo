package com.deepexi.dd.system.mall.remote.business;

import com.deepexi.dd.domain.business.api.BusinessCommodityRelationshipApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/12/16/15:41
 * @Description:
 */
@FeignClient(name = "deepexi.dd.domain.business")
public interface BusinessCommodityRelationshipDomainRemote extends BusinessCommodityRelationshipApi {
}
