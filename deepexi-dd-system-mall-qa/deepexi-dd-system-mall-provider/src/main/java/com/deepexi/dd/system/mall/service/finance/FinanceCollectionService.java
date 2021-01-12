package com.deepexi.dd.system.mall.service.finance;

import com.deepexi.dd.domain.finance.domain.dto.FinanceCollectionRequestDTO;
import com.deepexi.dd.domain.finance.domain.dto.FinanceCollectionResponseDTO;
import com.deepexi.dd.domain.finance.domain.query.FinanceCollectionRequestQuery;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * @ClassName FinanceCollectionService
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-08
 * @Version 1.0
 **/
public interface FinanceCollectionService {

    Payload<List<com.deepexi.dd.domain.finance.domain.dto.FinanceCollectionResponseDTO>> listFinanceCollections(FinanceCollectionRequestQuery requestQuery) throws Exception;

    Payload<PageBean<com.deepexi.dd.domain.finance.domain.dto.FinanceCollectionResponseDTO>> listFinanceCollectionsPage(FinanceCollectionRequestQuery query) throws Exception;

    Payload<Boolean> deleteByIdIn(List<Long> id) throws Exception;

    Payload<Long> insert(FinanceCollectionRequestDTO record) throws Exception;

    Payload<Boolean> confirm(Long recordId) throws Exception;

    Payload<Boolean> cancel(Long recordId) throws Exception;

    Payload<FinanceCollectionResponseDTO> selectById(Long id) throws Exception;

    Payload<Boolean> updateById(Long id,FinanceCollectionRequestDTO record) throws Exception;

}
