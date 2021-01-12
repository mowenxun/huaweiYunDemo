package com.deepexi.dd.system.mall.remote.shop.order;

import com.deepexi.dd.domain.transaction.api.ShopOrderDomainApi;
import com.deepexi.dd.domain.transaction.domain.query.ShopOrderDomainRequestQuery;
import com.deepexi.dd.domain.transaction.domain.responseDto.ShopOrderDomainResponseDTO;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/13/17:49
 * @Description:
 */
@FeignClient(value = "${eureka.client.transaction.name}")
public interface ShopOrderSysRemote extends ShopOrderDomainApi {

}
