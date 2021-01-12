package com.deepexi.dd.system.mall.service.common;

import com.deepexi.dd.domain.common.domain.dto.DataDictionaryResponseDTO;
import com.deepexi.dd.domain.common.domain.query.DataDictionaryRequestQuery;

import java.util.List;

/**
 * @Author: mumu
 * @Datetime: 2020/8/29   16:23
 * @version: v1.0
 */
public interface DataDictionaryService {

    /**
     * 查询列表
     * @return
     */
    List<DataDictionaryResponseDTO> listDataDictionarys(DataDictionaryRequestQuery query) throws Exception;

}
