package com.deepexi.dd.system.mall.service.finance.impl;

import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.finance.domain.dto.FinanceCollectionRequestDTO;
import com.deepexi.dd.domain.finance.domain.dto.FinanceCollectionResponseDTO;
import com.deepexi.dd.domain.finance.domain.query.FinanceCollectionRequestQuery;
import com.deepexi.dd.system.mall.remote.finance.FinanceCollectionClient;
import com.deepexi.dd.system.mall.service.finance.FinanceCollectionService;
import com.deepexi.util.StringUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName FinanceCollectionServiceImpl
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-08
 * @Version 1.0
 **/
@Service
public class FinanceCollectionServiceImpl implements FinanceCollectionService {

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;
    @Autowired
    private FinanceCollectionClient collectionClient;

    public Payload<List<com.deepexi.dd.domain.finance.domain.dto.FinanceCollectionResponseDTO>> listFinanceCollections(FinanceCollectionRequestQuery requestQuery) throws Exception {
        return collectionClient.listFinanceCollections(requestQuery);
    }

    public Payload<PageBean<com.deepexi.dd.domain.finance.domain.dto.FinanceCollectionResponseDTO>> listFinanceCollectionsPage(FinanceCollectionRequestQuery query) throws Exception {
        return collectionClient.listFinanceCollectionsPage(query);
    }

    public Payload<Boolean> deleteByIdIn(List<Long> id) throws Exception {
        return collectionClient.deleteByIdIn(id);
    }

    public Payload<Long> insert(FinanceCollectionRequestDTO record) throws Exception {
        record.setIsPaymentRecords(0);
        record.setType(0);
        record.setReceiptsTime(new Date());
        record.setStatus(22);
        record.setHandlersId(appRuntimeEnv.getUserId());
        record.setHandlersName(appRuntimeEnv.getUsername());
        //若支付凭证为空设置为空字符串
        if (StringUtil.isEmpty(record.getCredentialsUrl()))  {
            record.setCredentialsUrl("");
        }
        return collectionClient.insert(record);
    }

    public Payload<Boolean> confirm(Long recordId) throws Exception {
        return collectionClient.confirm(recordId);
    }

    public Payload<Boolean> cancel(Long recordId) throws Exception {
        return collectionClient.cancel(recordId);
    }

    public Payload<FinanceCollectionResponseDTO> selectById(Long id) throws Exception {
        return collectionClient.selectById(id);
    }

    public Payload<Boolean> updateById(Long id,FinanceCollectionRequestDTO record) throws Exception {
        return collectionClient.updateById(id, record);
    }
}
