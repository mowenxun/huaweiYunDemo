package com.deepexi.dd.domain.transaction.service.impl;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deepexi.dd.domain.common.domain.dto.CommonRuleSettingResponseDTO;
import com.deepexi.dd.domain.common.domain.query.CommonRuleSettingRequestQuery;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.tool.domain.dto.ToolStatusActionResponesDTO;
import com.deepexi.dd.domain.tool.domain.query.ToolStatusActionRequestQuery;
import com.deepexi.dd.domain.tool.enums.LinkBusinessCodeEnum;
import com.deepexi.dd.domain.tool.enums.ListTypeEnum;
import com.deepexi.dd.domain.tool.enums.StatusCodeEnum;
import com.deepexi.dd.domain.transaction.api.DepotApi;
import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.dto.ruleSetting.RuleSettingResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskAddRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskDetailResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskSignRequestDTO;
import com.deepexi.dd.domain.transaction.domain.query.SaleOutTaskRequestQuery;
import com.deepexi.dd.domain.transaction.manager.ToolActionExcutor;
import com.deepexi.dd.domain.transaction.remote.order.CommonRuleSettingClient;
import com.deepexi.dd.domain.transaction.remote.order.SaleOrderInfoClient;
import com.deepexi.dd.domain.transaction.remote.order.SaleOutTaskClient;
import com.deepexi.dd.domain.transaction.remote.order.ToolStatusClient;
import com.deepexi.dd.domain.transaction.service.SaleOutTaskService;
import com.deepexi.dd.domain.transaction.service.common.ToolLinkService;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOutTaskMiddleRequestQuery;
import com.deepexi.sdk.commodity.api.SkuOpenApi;
import com.deepexi.sdk.commodity.model.ListSkuAggregationRequestDTO;
import com.deepexi.sdk.commodity.model.ListSkuAggregationResponseDTO;
import com.deepexi.sdk.commodity.model.PayloadListListSkuAggregationResponseDTO;
/*import com.deepexi.sdk.storage.api.StockSdkApi;
import com.deepexi.sdk.storage.model.PayloadStockSaveOrUpdateBatchPostResponseDTO;
import com.deepexi.sdk.storage.model.StockSaveOrUpdateBatchPostRequestDTO;
import com.deepexi.sdk.storage.model.StockSaveOrUpdateBatchPostRequestDTOStock;*/
import com.deepexi.sdk.storage.api.StockSdkApi;
import com.deepexi.sdk.storage.model.PayloadStockSaveOrUpdateBatchPostResponseDTO;
import com.deepexi.sdk.storage.model.StockSaveOrUpdateBatchPostRequestDTO;
import com.deepexi.sdk.storage.model.StockSaveOrUpdateBatchPostRequestDTOStock;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.DateUtils;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.StringUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName SaleOutTaskServiceImpl
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-03
 * @Version 1.0
 **/
@Service
@Slf4j
public class SaleOutTaskServiceImpl implements SaleOutTaskService {

    @Autowired
    private SaleOutTaskClient saleOutTaskClient;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private SaleOrderInfoClient saleOrderInfoClient;

    @Autowired
    private CommonRuleSettingClient commonRuleSettingClient;

    @Autowired
    private ToolStatusClient toolStatusClient;

    @Autowired
    private ToolLinkService toolLinkService;

    @Autowired
    private StockSdkApi stockSdkApi;

    @Autowired
    private DepotApi depotApi;

    @Resource
    private SkuOpenApi skuOpenApi;

    @Autowired
    HttpServletRequest req;

    @Autowired
    HttpServletResponse res;

    @Autowired
    private ToolActionExcutor<SaleOrderResponseDTO> toolActionExcutor;

    /**
     * @param saleOutTaskAddRequestDTO
     * @Description: 新增销售出库单.
     * @Param: [saleOutTaskInfoDTO]
     * @return: com.deepexi.dd.domain.transaction.domain.dto.SaleOutTaskInfoDTO
     * @Author: SongTao
     * @Date: 2020/7/3
     */
    @Override
    public SaleOutTaskInfoResponseDTO insert(SaleOutTaskAddRequestDTO saleOutTaskAddRequestDTO) throws Exception {
        SaleOutTaskMiddleRequestDTO saleOutTaskMiddleRequestDTO = saleOutTaskAddRequestDTO.clone(SaleOutTaskMiddleRequestDTO.class);
        return saleOutTaskClient.insert(saleOutTaskMiddleRequestDTO).clone(SaleOutTaskInfoResponseDTO.class,CloneDirection.OPPOSITE);
    }

    /**
     * @param query
     * @Description: 分页查询销售出库单（出库任务单）列表.
     * @Param: [query]
     * @return: com.deepexi.util.pageHelper.PageBean<com.deepexi.dd.middle.order.domain.vo.SaleOrderInfoResponseVO>
     * @Author: SongTao
     * @Date: 2020/7/6
     */
    @Override
    public PageBean<SaleOutTaskInfoResponseDTO> searchSaleOutTaskPageList(SaleOutTaskRequestQuery query) throws Exception {
        SaleOutTaskMiddleRequestQuery saleOutTaskMiddleRequestQuery = query.clone(SaleOutTaskMiddleRequestQuery.class);
        saleOutTaskMiddleRequestQuery.setIsolationId(appRuntimeEnv.getTopOrganization().getId()+"");//获取最顶级的组织
        if(!appRuntimeEnv.getUserOrganization().getId().equals(appRuntimeEnv.getTopOrganization().getId())){
            saleOutTaskMiddleRequestQuery.setAscriptionOrgId(appRuntimeEnv.getUserOrganization().getId());//一级组织
        }
        Payload<PageBean<SaleOutTaskMiddleResponseDTO>> payload = saleOutTaskClient.listSaleOutTasksPage(saleOutTaskMiddleRequestQuery);
        PageBean<SaleOutTaskInfoResponseDTO> result = GeneralConvertUtils.convert2PageBean(payload.getPayload(), SaleOutTaskInfoResponseDTO.class);
        //获取业务链路按钮
        ToolStatusActionRequestQuery toolStatusActionRequestQuery = new ToolStatusActionRequestQuery();
        toolStatusActionRequestQuery.setAppId(query.getAppId());
        toolStatusActionRequestQuery.setTenantId(query.getTenantId());
        toolStatusActionRequestQuery.setListType("StockOut");
        List<ToolStatusActionRequestQuery.Items> itemsList = result.getContent().stream().map(map -> {
            ToolStatusActionRequestQuery.Items item = new ToolStatusActionRequestQuery.Items();
            Integer signStatus = Objects.isNull(map.getSignStatus()) ? 17 : map.getSignStatus();//签收状态
            Integer statusCode = 0;//出库单状态
            Integer action = 0;//未签收 数量则为1 否则为0
            if (signStatus.equals(17)){//未签收 展示代客下单按钮
                statusCode = StatusCodeEnum.ToReceive.getCode();
                action = 1;
            }else if (signStatus.equals(19)){
                statusCode = StatusCodeEnum.Received.getCode();
            }
            item.setStatusCode(statusCode);
            item.setStatusType("");
            JSONObject json = new JSONObject();
            json.put("unSignQuantity", action);
            item.setRuleData(json.toString());
            return item;
        }).collect(Collectors.toList());
        toolStatusActionRequestQuery.setItems(itemsList);
        Payload<List<ToolStatusActionResponesDTO>> toolStatusAction = toolStatusClient.listToolStatusAction(toolStatusActionRequestQuery);//拿到权限按钮
        List<ToolStatusActionResponesDTO> list = GeneralConvertUtils.convert2List(toolStatusAction.getPayload(),ToolStatusActionResponesDTO.class);
        for(int i=0;i<result.getContent().size();i++){
            result.getContent().get(i).setActions(GeneralConvertUtils.convert2List(list.get(i).getActions(), OrderActionResponseDTO.class) );
        }
        return result;
    }

    /**
     * @Description:  根据出库单id查销售出库单（出库任务单）详情.
     * @Param: [id]
     * @return: SaleOutTaskDetailResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/11
     */
    @Override
    public SaleOutTaskDetailResponseDTO searchSaleOutTaskInfoBySaleOutTaskId(Long id) throws Exception {
        SaleOutTaskMiddleResponseDTO dto = saleOutTaskClient.searchSaleOutTaskInfoBySaleOutTaskId(id);
        return dto.clone(SaleOutTaskDetailResponseDTO.class);
    }

    /**
     * @Description:  根据订单ID及签收状态查询销售出库单（出库任务单）详情.
     * @Param: [id,signStatus]
     * @return: java.util.List<SaleOutTaskDetailResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/11
     */
    @Override
    public List<SaleOutTaskDetailResponseDTO> searchSaleOutTaskInfoBySaleOrderId(Long id,Integer signStatus) throws Exception {
        List<SaleOutTaskMiddleResponseDTO> list = saleOutTaskClient.searchSaleOutTaskInfoBySaleOrderId(id,signStatus);
        return ObjectCloneUtils.convertList(list, SaleOutTaskDetailResponseDTO.class) ;
    }

    /**
     * @param id
     * @param signStatus
     * @Description: 根据提货单号查询销售出库单详情.
     * @Param: [id, signStatus]
     * @return: java.util.List<com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskDetailResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/19
     */
    @Override
    public List<SaleOutTaskDetailResponseDTO> searchSaleOutTaskInfoByPickGoodsId(Long id, Integer signStatus) throws Exception {
        List<SaleOutTaskMiddleResponseDTO> list = saleOutTaskClient.searchSaleOutTaskInfoByPickGoodsId(id, signStatus);
        return ObjectCloneUtils.convertList(list,SaleOutTaskDetailResponseDTO.class);
    }

    /**
     * @param id
     * @Description: 红冲出库单.
     * @Param: [id]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/9
     */
    @Override
    public Boolean hedgeOrder(Long id) throws Exception {
        return saleOutTaskClient.hedgeOrder(id);
    }

    /**
     * @param dto
     * @Description: 出库单签收.
     * @Param: [dto]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/13
     */
    @Override
    public Boolean sign(SaleOutTaskSignRequestDTO dto) throws Exception {
        log.info("签收--入参: {}", JSON.toJSONString(dto));
        SaleOutTaskMiddleRequestDTO saleOutTaskMiddleRequestDTO = dto.clone(SaleOutTaskMiddleRequestDTO.class);
        saleOutTaskMiddleRequestDTO.setUpdateBy(appRuntimeEnv.getUsername());
        Boolean sign = saleOutTaskClient.sign(saleOutTaskMiddleRequestDTO);
        if (sign){
            Long saleOutTaskId = dto.getIdList().get(0);
            SaleOutTaskMiddleResponseDTO middleResponseDTO = saleOutTaskClient.selectById(saleOutTaskId);//拿到相关出库单信息
            Long saleOrderId = middleResponseDTO.getSaleOrderId();
            SaleOrderInfoResponseDTO saleOrderInfoResponseDTO = GeneralConvertUtils.conv(saleOrderInfoClient.selectById(saleOrderId).getPayload(), SaleOrderInfoResponseDTO.class);
            Long unSignQuantity = saleOrderInfoResponseDTO.getQuantity().longValue() - saleOrderInfoResponseDTO.getTotalSignQuantity().longValue();//未签收数量
            log.info("签收--未签收数量: {}" , unSignQuantity.toString());
            if (ObjectUtils.equals(unSignQuantity,0L)){//待签收数 =0 则通知业务链路下一步
                toolActionExcutor.excute(LinkBusinessCodeEnum.BuyerReceive.name(), ListTypeEnum.SalesOrder.name(),
                        saleOrderInfoResponseDTO.getId(), saleOrderInfoResponseDTO.getTenantId(), saleOrderInfoResponseDTO.getAppId());
            }
        }
        return Boolean.TRUE;
    }

    /**
     * @param query
     * @Description: 根据批量的订单id 查出对应的出库单信息 供app调用.
     * @Param: [query]
     * @return: java.util.List<com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskDetailResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/10
     */
    @Override
    public List<SaleOutTaskDetailResponseDTO> listSaleOutTasksForApp(SaleOutTaskRequestQuery query) throws Exception {
        List<SaleOutTaskMiddleResponseDTO> list = saleOutTaskClient.listSaleOutTasksAggregation(query.clone(SaleOutTaskMiddleRequestQuery.class));
        List<SaleOutTaskDetailResponseDTO> saleOutTaskDetailResponseDTOS = ObjectCloneUtils.convertList(list, SaleOutTaskDetailResponseDTO.class);
        return saleOutTaskDetailResponseDTOS;
    }

    @Override
    public List<String> importStorageDomainStock(MultipartFile file, Long appId) throws Exception {
        String[] fileNameArray = file.getOriginalFilename().split("\\.");
        String suffix = fileNameArray[fileNameArray.length-1];
        Boolean checkSuffix = suffix.equals("xls")||suffix.equals("xlsx");
        if(!checkSuffix){
            throw new ApplicationException("文件类型错误");
        }
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ExcelReader excelReader = ExcelUtil.getReader(inputStream);
        Map<String, String> map = new HashMap<>();
        map.put("物料代码","skuCode");
        excelReader.setHeaderAlias(map);

        List<List<Object>> read = excelReader.read(0, 0);

        //正确的模板
        List<String> depotCodes = new ArrayList<>();
        depotCodes.add("skuCode");
        depotCodes.add("物料说明");
        depotCodes.add("N47");
        depotCodes.add("N48");
        depotCodes.add("N49");
        depotCodes.add("N50");
        depotCodes.add("N51");
        depotCodes.add("N52");
        depotCodes.add("N53");
        depotCodes.add("N54");
        depotCodes.add("N55");

        List<String> wrongDepotCode = new ArrayList<>();

        //List<String>  wrongDepotCode = read.get(0).stream().map(e->e.toString().trim()).filter(i->StringUtil.isNotEmpty(i)).filter(f->!depotCodes.contains(f)).collect(Collectors.toList());

        read.get(0).forEach(i->{
            String headName = i.toString().replaceAll("\\u00A0","").trim();
            if(!depotCodes.contains(headName)&&StringUtil.isNotEmpty(i.toString())){
                wrongDepotCode.add(i.toString());
            }
        });

        if(!read.get(0).get(0).equals("skuCode")){
            throw new ApplicationException("第一列的表头必须是物料代码");
        }

        //判断模板的正确性
        if(CollectionUtil.isNotEmpty(wrongDepotCode)){
            String wrongDepotCodeString = Joiner.on(",").join(wrongDepotCode);
            throw new ApplicationException("当前仓库"+wrongDepotCodeString+"不在预定的仓库里，不能导入，请检查修正之后再导入");
        }

//        List<StockImportDTO> list = excelReader.readAll(StockImportDTO.class);
        List<List<Object>> content = excelReader.read(1);
        List<StockImportDTO> list = new ArrayList<>();
        for(int i=0;i<content.size();i++){
            List rowData = content.get(i);
            StockImportDTO stockImportDTO = new StockImportDTO();
            stockImportDTO.setSkuCode(rowData.get(0).toString());
            stockImportDTO.setN47(StringUtil.isNotEmpty(rowData.get(2).toString())? Integer.valueOf(rowData.get(2).toString()):null);
            stockImportDTO.setN48(StringUtil.isNotEmpty(rowData.get(3).toString())? Integer.valueOf(rowData.get(3).toString()):null);
            stockImportDTO.setN49(StringUtil.isNotEmpty(rowData.get(4).toString())? Integer.valueOf(rowData.get(4).toString()):null);
            stockImportDTO.setN50(StringUtil.isNotEmpty(rowData.get(5).toString())? Integer.valueOf(rowData.get(5).toString()):null);
            stockImportDTO.setN51(StringUtil.isNotEmpty(rowData.get(6).toString())? Integer.valueOf(rowData.get(6).toString()):null);
            stockImportDTO.setN52(StringUtil.isNotEmpty(rowData.get(7).toString())? Integer.valueOf(rowData.get(7).toString()):null);
            stockImportDTO.setN53(StringUtil.isNotEmpty(rowData.get(8).toString())? Integer.valueOf(rowData.get(8).toString()):null);
            stockImportDTO.setN54(StringUtil.isNotEmpty(rowData.get(9).toString())? Integer.valueOf(rowData.get(9).toString()):null);
            stockImportDTO.setN55(StringUtil.isNotEmpty(rowData.get(10).toString())? Integer.valueOf(rowData.get(10).toString()):null);
            list.add(stockImportDTO);
        }




        if(CollectionUtil.isEmpty(list)){
            throw new ApplicationException("解析不到excel数据");
        }

        List<String> skuCodes =  list.stream().map(StockImportDTO::getSkuCode).collect(Collectors.toList());
        if(CollectionUtil.isEmpty(skuCodes)){
            throw new ApplicationException("解析不到excel数据");
        }

        Payload<List<DepotResponseDTO>> depotPayload = depotApi.getAllDepotList(appId,appRuntimeEnv.getTopOrganization().getId().toString());
        List<DepotResponseDTO> depotResponseDTOS = GeneralConvertUtils.convert2List(depotPayload.getPayload(), DepotResponseDTO.class);
        if(CollectionUtil.isEmpty(depotResponseDTOS)){
            throw new ApplicationException("获取不到仓库");
        }
        log.info("depotResponseDTOS:"+JsonUtil.bean2JsonString(depotResponseDTOS));
        try{
            Map<String,String> depotMap = depotResponseDTOS.stream().collect(Collectors.toMap(DepotResponseDTO::getDepotNo,DepotResponseDTO::getId));
        }
        catch (Exception e){
            throw new ApplicationException("原有仓库列表存在重复编码");
        }
        Map<String,String> depotMap = depotResponseDTOS.stream().collect(Collectors.toMap(DepotResponseDTO::getDepotNo,DepotResponseDTO::getId));
        log.info("depotMap:"+JsonUtil.bean2JsonString(depotMap));

        ListSkuAggregationRequestDTO skuAggregationRequestDTO = new ListSkuAggregationRequestDTO();
        skuAggregationRequestDTO.setCodes(skuCodes);
        skuAggregationRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
        skuAggregationRequestDTO.setAppId(appId);
        PayloadListListSkuAggregationResponseDTO skuPayload = skuOpenApi.listSkuAggregation(skuAggregationRequestDTO);
        List<ListSkuAggregationResponseDTO> skuDTOS = GeneralConvertUtils.convert2List(skuPayload.getPayload(),ListSkuAggregationResponseDTO.class);
        if(CollectionUtil.isEmpty(skuDTOS)){
            throw new ApplicationException("没有查到任何sku");
        }
        log.info("skuDTOS:"+JsonUtil.bean2JsonString(skuDTOS));
        try{
            Map<String,Long> skuMap = skuDTOS.stream().collect(Collectors.toMap(ListSkuAggregationResponseDTO::getCode,ListSkuAggregationResponseDTO::getId));
            Map<String,Long> codeMap = skuDTOS.stream().collect(Collectors.groupingBy(ListSkuAggregationResponseDTO::getCode,Collectors.counting()));
            List<String> codes =codeMap.keySet().stream().filter(f->codeMap.get(f)>1).collect(Collectors.toList());
            log.info("重复codes"+JsonUtil.bean2JsonString(codes));
        }
        catch (Exception e){
            throw new ApplicationException("sku存在重复编码");
        }
        Map<String,Long> skuMap = skuDTOS.stream().collect(Collectors.toMap(ListSkuAggregationResponseDTO::getCode,ListSkuAggregationResponseDTO::getId));
        log.info("skuMap:"+ JsonUtil.bean2JsonString(skuMap));

        //过滤出没有查到sku的那些skuCode 当做spuCode再去查spuId?
//        List<String> successSkuCode = skuDTOS.stream().map(ListSkuAggregationResponseDTO::getCode).collect(Collectors.toList());
//        List<String> spuCodes = skuCodes.stream().filter(f->!successSkuCode.contains(f)).collect(Collectors.toList());
//        ListSkuAggregationRequestDTO skuAggregationRequestDTO2 = new ListSkuAggregationRequestDTO();
//        skuAggregationRequestDTO2.(spuCodes);
//        skuAggregationRequestDTO2.setTenantId(appRuntimeEnv.getTenantId());
//        skuAggregationRequestDTO2.setAppId(appId);
//        PayloadListListSkuAggregationResponseDTO skuPayload = skuOpenApi.listSkuAggregation(skuAggregationRequestDTO2);
//        List<ListSkuAggregationResponseDTO> skuDTOS = GeneralConvertUtils.convert2List(skuPayload.getPayload(),ListSkuAggregationResponseDTO.class);

        List<StockSaveOrUpdateBatchPostRequestDTOStock> stockList = new ArrayList<>();

        //查不到sku的skuCodes
        List<String> disabledSkuCodes = new ArrayList<>();

            list.forEach(i->{
                StockSaveOrUpdateBatchPostRequestDTOStock stock = new StockSaveOrUpdateBatchPostRequestDTOStock();

                stock.setSkuNo(i.getSkuCode());

                if(skuMap.get(i.getSkuCode())==null){
                    log.info(i.getSkuCode() + "找不到对应的sku");
                    disabledSkuCodes.add(i.getSkuCode());
                    return;
                }
                stock.setSkuId(skuMap.get(i.getSkuCode()));
                stock.setTenantId(appRuntimeEnv.getTenantId());
                stock.setAppId(appId);
                stock.setLockStockQty(0);
                stock.setOutboundStockQty(0);
                stock.setUnavailableStockQty(0);
                if(i.getN47()!=null&& StringUtil.isNotEmpty(depotMap.get("N47"))){
                    stock.setDepotId(Long.valueOf(depotMap.get("N47")));
                    stock.setAvailableStockQty(i.getN47());
                    stockList.add(stock);
                }else{
                    log.info("N47仓库没有设置可用数量或者仓库找不到");
                }

                if(i.getN48()!=null&& StringUtil.isNotEmpty(depotMap.get("N48"))){
                    StockSaveOrUpdateBatchPostRequestDTOStock stock2 = stock.clone(StockSaveOrUpdateBatchPostRequestDTOStock.class);
                    stock2.setDepotId(Long.valueOf(depotMap.get("N48")));
                    stock2.setAvailableStockQty(i.getN48());
                    stockList.add(stock2);
                }else{
                    log.info("N48仓库没有设置可用数量或者仓库找不到");
                }

                if(i.getN49()!=null&& StringUtil.isNotEmpty(depotMap.get("N49"))) {
                    StockSaveOrUpdateBatchPostRequestDTOStock stock3 = stock.clone(StockSaveOrUpdateBatchPostRequestDTOStock.class);
                    stock3.setDepotId(Long.valueOf(depotMap.get("N49")));
                    stock3.setAvailableStockQty(i.getN49());
                    stockList.add(stock3);
                }else{
                    log.info("N49仓库没有设置可用数量或者仓库找不到");
                }

                if(i.getN50()!=null&& StringUtil.isNotEmpty(depotMap.get("N50"))) {
                    StockSaveOrUpdateBatchPostRequestDTOStock stock4 = stock.clone(StockSaveOrUpdateBatchPostRequestDTOStock.class);
                    stock4.setDepotId(Long.valueOf(depotMap.get("N50")));
                    stock4.setAvailableStockQty(i.getN50());
                    stockList.add(stock4);
                }else{
                    log.info("N50仓库没有设置可用数量或者仓库找不到");
                }

                if(i.getN51()!=null&& StringUtil.isNotEmpty(depotMap.get("N51"))) {
                    StockSaveOrUpdateBatchPostRequestDTOStock stock5 = stock.clone(StockSaveOrUpdateBatchPostRequestDTOStock.class);
                    stock5.setDepotId(Long.valueOf(depotMap.get("N51")));
                    stock5.setAvailableStockQty(i.getN51());
                    stockList.add(stock5);
                }else{
                    log.info("N51仓库没有设置可用数量或者仓库找不到");
                }

                if(i.getN52()!=null&& StringUtil.isNotEmpty(depotMap.get("N52"))) {
                    StockSaveOrUpdateBatchPostRequestDTOStock stock6 = stock.clone(StockSaveOrUpdateBatchPostRequestDTOStock.class);
                    stock6.setDepotId(Long.valueOf(depotMap.get("N52")));
                    stock6.setAvailableStockQty(i.getN52());
                    stockList.add(stock6);
                }else{
                    log.info("N52仓库没有设置可用数量或者仓库找不到");
                }

                if(i.getN53()!=null&& StringUtil.isNotEmpty(depotMap.get("N53"))) {
                    StockSaveOrUpdateBatchPostRequestDTOStock stock7 = stock.clone(StockSaveOrUpdateBatchPostRequestDTOStock.class);
                    stock7.setDepotId(Long.valueOf(depotMap.get("N53")));
                    stock7.setAvailableStockQty(i.getN53());
                    stockList.add(stock7);
                }else{
                    log.info("N53仓库没有设置可用数量或者仓库找不到");
                }

                if(i.getN54()!=null&& StringUtil.isNotEmpty(depotMap.get("N54"))){
                    StockSaveOrUpdateBatchPostRequestDTOStock stock8 = stock.clone(StockSaveOrUpdateBatchPostRequestDTOStock.class);
                    stock8.setDepotId(Long.valueOf(depotMap.get("N54")));
                    stock8.setAvailableStockQty(i.getN54());
                    stockList.add(stock8);
                }else{
                    log.info("N54仓库没有设置可用数量或者仓库找不到");
                }

                if(i.getN55()!=null&&StringUtil.isNotEmpty(depotMap.get("N55"))){
                    StockSaveOrUpdateBatchPostRequestDTOStock stock9 = stock.clone(StockSaveOrUpdateBatchPostRequestDTOStock.class);
                    stock9.setDepotId(Long.valueOf(depotMap.get("N55")));
                    stock9.setAvailableStockQty(i.getN55());
                    stockList.add(stock9);
                }else{
                    log.info("N55仓库没有设置可用数量或者仓库找不到");
                }

            });

            if(CollectionUtil.isEmpty(stockList)){
                throw new ApplicationException("找不到对应的仓库或者表格没有数据");
            }

//            List<StockSaveOrUpdateBatchPostRequestDTOStock> stockList = GeneralConvertUtils.convert2List(list,StockSaveOrUpdateBatchPostRequestDTOStock.class);
            StockSaveOrUpdateBatchPostRequestDTO stockSaveOrUpdateBatchPostRequestDTO = new StockSaveOrUpdateBatchPostRequestDTO();
            stockSaveOrUpdateBatchPostRequestDTO.setStockList(stockList);
            stockSaveOrUpdateBatchPostRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
            stockSaveOrUpdateBatchPostRequestDTO.setAppId(appId);
            log.info("stockSaveOrUpdateBatchPostRequestDTO:"+JsonUtil.bean2JsonString(stockSaveOrUpdateBatchPostRequestDTO));
            PayloadStockSaveOrUpdateBatchPostResponseDTO payloadStockSaveOrUpdateBatchPostResponseDTO = stockSdkApi.saveOrUpdateBatch(stockSaveOrUpdateBatchPostRequestDTO);
            if(payloadStockSaveOrUpdateBatchPostResponseDTO.getCode().equals("0")){
//                if(CollectionUtil.isNotEmpty(disabledSkuCodes)){
//                    exportWrongSku(disabledSkuCodes);
//                }
                return disabledSkuCodes;
            }
            else {
                throw new ApplicationException(payloadStockSaveOrUpdateBatchPostResponseDTO.getMsg());
            }
        }

    private void exportWrongSku(List<String> disabledSkuCodes){

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("错误物料编码");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("错误物料编码");
        cell.setCellStyle(style);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        List<String> list = disabledSkuCodes;

        for (int i = 0; i < list.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            row.createCell((short) 0).setCellValue(list.get(i));
        }
        // 第六步，将文件存到指定位置
        try
        {

            String fileName ="错误物料编码"+DateUtils.format(new Date(),"yyyyMMddHHmmss")+".xls";
            fileName = URLEncoder.encode(fileName, "UTF-8");
            res.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            OutputStream out = res.getOutputStream();
            wb.write(out);
            out.close();
            wb.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @Description:  定时任务 出库单自动签收.
     * @Param: []
     * @return: void
     * @Author: SongTao
     * @Date: 2020/7/20
     */
//    @Scheduled(cron = "0 0 0 * * ?")
    public void autoSign() throws Exception{
        CommonRuleSettingRequestQuery query = new CommonRuleSettingRequestQuery();
        Payload<List<CommonRuleSettingResponseDTO>> listPayload = commonRuleSettingClient.listCommonRuleSettings(query);
        if (CollectionUtils.isEmpty(listPayload.getPayload())){
            log.info("订货商城未设置规则设置");
        }else{
            RuleSettingResponseDTO ruleSettingResponseDTO = GeneralConvertUtils.conv(listPayload.getPayload().get(0), RuleSettingResponseDTO.class);
            String value = ruleSettingResponseDTO.getValue();//拿到商城设置的规则天数
            if (StringUtils.isNotBlank(value)){
                saleOutTaskClient.autoSign(Integer.valueOf(value));
            }
        }
    }
}
