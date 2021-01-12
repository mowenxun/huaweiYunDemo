package com.deepexi.dd.system.mall.controller.app;

import com.deepexi.dd.domain.common.domain.dto.DataDictionaryResponseDTO;
import com.deepexi.dd.domain.common.domain.query.DataDictionaryRequestQuery;
import com.deepexi.dd.system.mall.service.common.DataDictionaryService;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: mumu
 * @Datetime: 2020/8/29   14:46
 * @version: v1.0
 */
@RestController
@RequestMapping("/admin-api/v1/dd/common/dictionary")
@Api(tags="数据字段远程服务")
public class DataDictionaryController {

    @Autowired
    private DataDictionaryService dataDictionaryService;

    /**
     * 数据字典查询列表 post请求
     *
     * @return
     */
    @PostMapping("/list")
    @ApiOperation(value = "数据字典查询列表")
    public Payload<List<DataDictionaryResponseDTO>> listDataDictionarys(@RequestBody DataDictionaryRequestQuery query) throws Exception{
        return new Payload<>(dataDictionaryService.listDataDictionarys(query));
    }

    /**
     * 数据字典查询列表 get请求
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "数据字典查询列表")
    public Payload<List<DataDictionaryResponseDTO>> appGetListDataDictionarys(DataDictionaryRequestQuery query) throws Exception{
        return new Payload<>(dataDictionaryService.listDataDictionarys(query));
    }

}
