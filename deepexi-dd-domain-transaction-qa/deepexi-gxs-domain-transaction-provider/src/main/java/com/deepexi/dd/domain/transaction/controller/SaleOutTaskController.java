package com.deepexi.dd.domain.transaction.controller;

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskAddRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskDetailResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskSignRequestDTO;
import com.deepexi.dd.domain.transaction.domain.query.SaleOutTaskRequestQuery;
import com.deepexi.dd.domain.transaction.remote.order.SaleOutTaskClient;
import com.deepexi.dd.domain.transaction.service.SaleOutTaskService;
import com.deepexi.dd.domain.transaction.service.SalePickGoodsInfoService;
import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : SalesOutTaskController
 * @Description : 销售出库单
 * @Author : yuanzaishun
 * @Date: 2020-06-23 11:47
 */
@RestController
@Api(tags = "销售出库单接口")
@RequestMapping("admin-api/v1/domain/transaction/salesOutTask")
public class SaleOutTaskController {

    @Autowired
    private SaleOutTaskService saleOutTaskService;

    @Autowired
    private SaleOutTaskClient saleOutTaskClient;

    @Autowired
    private SalePickGoodsInfoService salePickGoodsInfoService;

    /**
     * @Description: 新增销售出库单.
     * @Param: [saleOutTaskAddRequestDTO]
     * @return: com.deepexi.util.config.Payload<SaleOutTaskInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/10
     */
    @ApiOperation("新增销售出库单（出库任务单）")
    @PostMapping("/insert")
    public Payload<SaleOutTaskInfoResponseDTO> insert(@RequestBody SaleOutTaskAddRequestDTO saleOutTaskAddRequestDTO) throws Exception {
        SaleOutTaskInfoResponseDTO saleOrderInfoDTOResult = saleOutTaskService.insert(saleOutTaskAddRequestDTO);
        return new Payload<>(saleOrderInfoDTOResult);
    }

/*
     @ApiOperation("查询销售出库单（出库任务单）列表")
    @GetMapping("/list")
    public Payload<List<SaleOutTaskInfoResponseDTO>> listSaleOutTasks(SaleOutTaskRequestQuery query) {
        return new Payload<>();
    }

    @ApiOperation("根据ID更新销售出库单（出库任务单）")
    @PutMapping("/{id}")
    public Payload<Boolean> updateById(@PathVariable Long id,@RequestBody SaleOutTaskRequestDTO record) {
        return new Payload<>();
    }

    @ApiOperation("批量删除销售出库单（出库任务单）")
    @DeleteMapping("")
    public Payload<Boolean> deleteByIdIn(@RequestBody List<Long> id){
        return new Payload<>();
    }
 */

    /**
     * @Description: 分页查询销售出库单（出库任务单）列表.
     * @Param: [query]
     * @return: com.deepexi.util.config.Payload<com.deepexi.util.pageHelper.PageBean < com.deepexi.dd.domain.transaction.domain.dto.SaleOutTaskResponseDTO>>
     * @Author: SongTao
     * @Date: 2020/7/6
     */
    @ApiOperation("分页查询销售出库单（出库任务单）列表")
    @GetMapping("/page")
    public Payload<PageBean<SaleOutTaskInfoResponseDTO>> searchSaleOutTaskPageList(SaleOutTaskRequestQuery query) throws Exception {
        PageBean<SaleOutTaskInfoResponseDTO> result = saleOutTaskService.searchSaleOutTaskPageList(query);
        return new Payload<>(GeneralConvertUtils.convert2PageBean(result, SaleOutTaskInfoResponseDTO.class));
    }

    @ApiOperation("根据出库单ID查询销售出库单（出库任务单）")
    @RequestMapping(value = "/searchSaleOutTaskInfoBySaleOutTaskId/{id}", method = RequestMethod.GET)
    public Payload<SaleOutTaskDetailResponseDTO> searchSaleOutTaskInfoBySaleOutTaskId(@PathVariable Long id) throws Exception {
        SaleOutTaskDetailResponseDTO saleOutTaskInfoResponseDTO =
                saleOutTaskService.searchSaleOutTaskInfoBySaleOutTaskId(id);
        return new Payload<>(saleOutTaskInfoResponseDTO);
    }

    @ApiOperation("根据订单ID及签收状态查询销售出库单（出库任务单）")
    @RequestMapping(value = "/searchSaleOutTaskInfoBySaleOrderId/{id}/{signStatus}", method = RequestMethod.GET)
    public Payload<List<SaleOutTaskDetailResponseDTO>> searchSaleOutTaskInfoBySaleOrderId(@PathVariable Long id,
                                                                                          @PathVariable Integer signStatus) throws Exception {
        List<SaleOutTaskDetailResponseDTO> list = saleOutTaskService.searchSaleOutTaskInfoBySaleOrderId(id, signStatus);
        return new Payload<>(list);
    }

    @ApiOperation("根据提货单ID及签收状态查询销售出库单（出库任务单）")
    @GetMapping(value = "/searchSaleOutTaskInfoByPickGoodsId/{id}/{signStatus}")
    public Payload<List<SaleOutTaskDetailResponseDTO>> searchSaleOutTaskInfoByPickGoodsId(@PathVariable Long id,
                                                                                          @PathVariable Integer signStatus) throws Exception {
        List<SaleOutTaskDetailResponseDTO> list = saleOutTaskService.searchSaleOutTaskInfoByPickGoodsId(id, signStatus);
        return new Payload<>(list);
    }


    @ApiOperation("红冲出库单")
    @RequestMapping(value = "/hedgeOrder/{id}", method = RequestMethod.POST)
    public Payload<Boolean> hedgeOrder(@PathVariable Long id) throws Exception {
        return new Payload<>(saleOutTaskService.hedgeOrder(id));
    }

    @ApiOperation("签收")
    @PostMapping("/sign")
    public Payload<Boolean> sign(@RequestBody @Valid SaleOutTaskSignRequestDTO dto) throws Exception {
        SaleOutTaskSignRequestDTO saleOrderDto = new SaleOutTaskSignRequestDTO();
        List<Long> saleOrderDtoIdList = new ArrayList<>();
        SaleOutTaskSignRequestDTO salePickOrderDto = new SaleOutTaskSignRequestDTO();
        List<Long> salePickOrderDtoIdList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(dto.getIdList())) {
            dto.getIdList().forEach(item -> {
                try {
                    SaleOutTaskMiddleResponseDTO middleResponseDTO = saleOutTaskClient.selectById(item);//拿到相关出库单信息
                    if (middleResponseDTO != null) {
                        if (middleResponseDTO.getSalePickGoodsId() != null) {//提货单
                            salePickOrderDtoIdList.add(middleResponseDTO.getId());
                        } else {//销售单
                            saleOrderDtoIdList.add(middleResponseDTO.getId());
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            boolean f = true;
            boolean b = true;
            if (CollectionUtils.isNotEmpty(saleOrderDtoIdList)) {
                saleOrderDto.setIdList(saleOrderDtoIdList);
                f = saleOutTaskService.sign(saleOrderDto);
                //return new Payload<>(saleOutTaskService.sign(saleOrderDto));
            }
            if (CollectionUtils.isNotEmpty(salePickOrderDtoIdList)) {
                salePickOrderDto.setIdList(salePickOrderDtoIdList);
                b = salePickGoodsInfoService.sign(salePickOrderDto);
            }
            if (f && b) {
                return new Payload<>(true);
            }
        }
        return new Payload<>(true);
    }

    @ApiOperation("导入库存")
    @ResponseBody
    @PostMapping("/importStorageDomainStock")
    public Payload<List<String>> importStorageDomainStock(@RequestParam("file") MultipartFile file, Long appId) throws Exception {

        return new Payload<>(saleOutTaskService.importStorageDomainStock(file, appId));
    }


}
