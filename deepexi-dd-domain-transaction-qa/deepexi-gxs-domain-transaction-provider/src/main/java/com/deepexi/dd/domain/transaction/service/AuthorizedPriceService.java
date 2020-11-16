package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.domain.transaction.domain.dto.AuthorizedPrice.AuthorizedPriceRquestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.AuthorizedPrice.DirectSkuPriceRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.AuthorizedPrice.SkuStockRequestDTO;
import com.deepexi.dd.domain.transaction.domain.query.CommodityQuery;
import com.deepexi.dd.domain.transaction.domain.responseDto.AuthorizedPrice.AuthorizedPriceResponseDTO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/08/28/16:33
 * @Description:
 */
public interface AuthorizedPriceService {

    List<AuthorizedPriceResponseDTO> getAuthorizedPrice(List<AuthorizedPriceRquestDTO> list) throws Exception;

    Map<Long, BigDecimal> getDirectSkuPrice(DirectSkuPriceRequestDTO directSkuPriceRequestDTO) throws Exception;

    Map<Long, Long> getSkuStock(SkuStockRequestDTO skuStockRequestDTO) throws ApplicationException;

}
