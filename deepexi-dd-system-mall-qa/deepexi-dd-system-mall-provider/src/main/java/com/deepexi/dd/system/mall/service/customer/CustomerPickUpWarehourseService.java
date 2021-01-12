package com.deepexi.dd.system.mall.service.customer;

import com.deepexi.dd.system.mall.domain.vo.customer.CustomerWarehouseResponseVO;
import com.deepexi.dd.system.mall.domain.vo.customer.MerchantInvoiceResponseVO;
import com.deepexi.util.config.Payload;
import domain.dto.*;
import domain.query.MerchantInvoiceRequestQuery;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @ClassName MerchantInvoiceService
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
public interface CustomerPickUpWarehourseService {
    List<CustomerWarehouseResponseVO> orglist(CustomerPickUpOrgWarehouseDomainQuery query) throws Exception;
}
