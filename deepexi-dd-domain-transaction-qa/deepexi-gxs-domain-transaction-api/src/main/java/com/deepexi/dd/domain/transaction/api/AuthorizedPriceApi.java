package com.deepexi.dd.domain.transaction.api;

import com.deepexi.dd.domain.transaction.domain.dto.AuthorizedPrice.AuthorizedPriceRquestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.AuthorizedPrice.DirectSkuPriceRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.AuthorizedPrice.SkuStockRequestDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.AuthorizedPrice.AuthorizedPriceResponseDTO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/08/28/16:22
 * @Description:
 */
@Api(value = "获得商品价格授权")
@RequestMapping("/open-api/v1/domain/transaction/authorizedPrice")
public interface AuthorizedPriceApi {

    @PostMapping("/getAuthorizedPrice")
    public Payload<List<AuthorizedPriceResponseDTO>> getAuthorizedPrice(@RequestBody List<AuthorizedPriceRquestDTO> list) throws Exception;

    @PostMapping("/getDirectSkuPrice")
    public Map<Long, BigDecimal> getDirectSkuPrice(@RequestBody DirectSkuPriceRequestDTO directSkuPriceRequestDTO) throws Exception;

    @PostMapping("/getSkuStock")
    Map<Long, Long> getSkuStock(@RequestBody SkuStockRequestDTO skuStockRequestDTO) throws ApplicationException;
}
