package com.deepexi.dd.domain.transaction.api.impl;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/08/28/16:29
 * @Description:
 */

import com.deepexi.dd.domain.transaction.api.AuthorizedPriceApi;
import com.deepexi.dd.domain.transaction.domain.dto.AuthorizedPrice.AuthorizedPriceRquestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.AuthorizedPrice.DirectSkuPriceRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.AuthorizedPrice.SkuStockRequestDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.AuthorizedPrice.AuthorizedPriceResponseDTO;
import com.deepexi.dd.domain.transaction.service.AuthorizedPriceService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AuthorizedPriceApiImpl
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/8/28
 * @Version V1.0
 **/
@RestController
public class AuthorizedPriceApiImpl implements AuthorizedPriceApi {

    @Autowired
    AuthorizedPriceService authorizedPriceService;

    @Override
    public Payload<List<AuthorizedPriceResponseDTO>> getAuthorizedPrice(@RequestBody List<AuthorizedPriceRquestDTO> list) throws Exception {
        return new Payload<>(authorizedPriceService.getAuthorizedPrice(list));
    }

    @Override
    public Map<Long, BigDecimal> getDirectSkuPrice(@RequestBody DirectSkuPriceRequestDTO directSkuPriceRequestDTO) throws Exception {
        return authorizedPriceService.getDirectSkuPrice(directSkuPriceRequestDTO);
    }

    @Override
    public Map<Long, Long> getSkuStock(@RequestBody SkuStockRequestDTO skuStockRequestDTO) throws ApplicationException {
        return authorizedPriceService.getSkuStock(skuStockRequestDTO);
    }
}
