package com.deepexi.dd.system.mall.service.app;

import com.deepexi.util.config.Payload;
import domain.dto.CustomerProjectApiResponse; /**
 * @author yp
 * @version 1.0
 * @date 2020-09-27 14:52
 */
public interface CategoryBackService {
    /**
     * 查寻类目列表
     * @param payload
     * @return
     */
    Payload<CustomerProjectApiResponse> listBackCategory(CustomerProjectApiResponse payload);
}
