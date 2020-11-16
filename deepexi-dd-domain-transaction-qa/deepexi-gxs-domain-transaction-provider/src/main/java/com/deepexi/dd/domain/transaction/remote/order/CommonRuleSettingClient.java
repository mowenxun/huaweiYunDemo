package com.deepexi.dd.domain.transaction.remote.order;

import com.deepexi.dd.domain.common.api.CommonRuleSettingApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName CommonRuleSettingClient
 * @Description 规则设置接口
 * @Author SongTao
 * @Date 2020-07-20
 * @Version 1.0
 **/
@FeignClient(name = "${deepexi.dd.domain.common.name}")
public interface CommonRuleSettingClient extends CommonRuleSettingApi {
}
