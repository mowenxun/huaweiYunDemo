package com.deepexi.dd.system.mall.service.common.impl;

import com.deepexi.dd.domain.common.domain.dto.DataDictionaryResponseDTO;
import com.deepexi.dd.domain.common.domain.query.DataDictionaryRequestQuery;
import com.deepexi.dd.system.mall.remote.common.DataDictionaryClient;
import com.deepexi.dd.system.mall.service.common.DataDictionaryService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: mumu
 * @Datetime: 2020/8/29   16:30
 * @version: v1.0
 */
@Service
@Slf4j
public class DataDictionaryServiceImpl implements DataDictionaryService {

    private static final String SUCCESS = "0";

    @Autowired
    private DataDictionaryClient dictionaryClient;

    @Override
    public List<DataDictionaryResponseDTO> listDataDictionarys(DataDictionaryRequestQuery query) throws Exception {
        Payload<List<DataDictionaryResponseDTO>> payload = dictionaryClient.findAll(query);
//        if (SUCCESS.equals(payload.getCode())) {
//            try {
        List<DataDictionaryResponseDTO> result = GeneralConvertUtils.convert2List(payload.getPayload(),
                DataDictionaryResponseDTO.class);
        return result;
//            } catch (Exception e) {
//                log.error("获取公共域数据字段失败", e);
//                throw new ApplicationException("获取公共域数据字段失败");
//            }
//        } else {
//            throw new ApplicationException(payload.getMsg());
//        }
    }
}
