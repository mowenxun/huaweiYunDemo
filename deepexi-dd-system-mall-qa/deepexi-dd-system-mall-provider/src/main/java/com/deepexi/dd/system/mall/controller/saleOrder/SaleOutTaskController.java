package com.deepexi.dd.system.mall.controller.saleOrder;

import com.deepexi.dd.system.mall.domain.dto.SaleOutTaskDetailInfoResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.SaleOutTaskInfoResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.SaleOutTaskInfoSignRequestDTO;
import com.deepexi.dd.system.mall.domain.query.SaleOutTaskRequestQuery;
import com.deepexi.dd.system.mall.service.SaleOutTaskService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName SaleOutTaskController
 * @Description 销售出库单接口
 * @Author SongTao
 * @Date 2020-07-18
 * @Version 1.0
 **/
@RestController
@Api(tags = "销售出库单接口")
@RequestMapping("admin-api/v1/domain/sale/salesOutTask")
public class SaleOutTaskController {
    @Autowired
    SaleOutTaskService saleOutTaskService;
    /**
     * @Description:  签收.
     * @Param: [dto]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/7/21
     */
    @ApiOperation("签收")
    @PostMapping("/sign")
    public Payload<Boolean> sign(@RequestBody @Valid SaleOutTaskInfoSignRequestDTO dto) throws Exception {
        return new Payload<>(saleOutTaskService.sign(dto));
    }

    /**
     * @Description:  分页查询销售出库单（出库任务单）列表.
     * @Param: [query]
     * @return: com.deepexi.util.config.Payload<com.deepexi.util.pageHelper.PageBean<com.deepexi.dd.domain.transaction.domain.dto.saleouttask.SaleOutTaskInfoResponseDTO>>
     * @Author: SongTao
     * @Date: 2020/7/21
     */
    @ApiOperation("分页查询销售出库单（出库任务单）列表")
    @GetMapping("/page")
    public Payload<PageBean<SaleOutTaskInfoResponseDTO>> searchSaleOutTaskPageList(SaleOutTaskRequestQuery query) throws Exception{
        return new Payload<>(GeneralConvertUtils.convert2PageBean(saleOutTaskService.searchSaleOutTaskPageList(query), SaleOutTaskInfoResponseDTO.class));
    }

    /**
     * @Description:  根据出库单ID查询销售出库单（出库任务单）.
     * @Param: [id,signStatus]
     * @return: com.deepexi.util.config.Payload<com.deepexi.dd.system.mall.domain.dto.SaleOutTaskDetailInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/21
     */
    @ApiOperation("根据出库单ID查询销售出库单（出库任务单）")
    @GetMapping("/searchSaleOutTaskInfoBySaleOutTaskId/{id}")
    public Payload<SaleOutTaskDetailInfoResponseDTO> searchSaleOutTaskInfoBySaleOutTaskId(@PathVariable Long id) throws Exception {
        SaleOutTaskDetailInfoResponseDTO saleOutTaskInfoResponseDTO = saleOutTaskService.searchSaleOutTaskInfoBySaleOutTaskId(id);
        return new Payload<>(saleOutTaskInfoResponseDTO);
    }

    /**
     * @Description:  根据订单ID及签收状态查询销售出库单（出库任务单）.
     * @Param: [id]
     * @return: com.deepexi.util.config.Payload<java.util.List<com.deepexi.dd.system.mall.domain.dto.SaleOutTaskDetailInfoResponseDTO>>
     * @Author: SongTao
     * @Date: 2020/7/21
     */
    @ApiOperation("根据订单ID查询销售出库单（出库任务单）")
    @RequestMapping(value = "/searchSaleOutTaskInfoBySaleOrderId/{id}/{signStatus}",method = RequestMethod.GET)
    public Payload<List<SaleOutTaskDetailInfoResponseDTO>> searchSaleOutTaskInfoBySaleOrderId(@PathVariable Long id,@PathVariable Integer signStatus) throws Exception {
        List<SaleOutTaskDetailInfoResponseDTO> list = saleOutTaskService.searchSaleOutTaskInfoBySaleOrderId(id,signStatus);
        return new Payload<>(list);
    }

    @ApiOperation("根据提货单号查询销售出库单（出库任务单）")
    @GetMapping(value = "/searchSaleOutTaskInfoByPickGoodsId/{id}/{signStatus}")
    public Payload<List<SaleOutTaskDetailInfoResponseDTO>> searchSaleOutTaskInfoByPickGoodsId(@PathVariable Long id,@PathVariable Integer signStatus) throws Exception {
        return new Payload<>(saleOutTaskService.searchSaleOutTaskInfoByPickGoodsId(id,signStatus));
    }

}
