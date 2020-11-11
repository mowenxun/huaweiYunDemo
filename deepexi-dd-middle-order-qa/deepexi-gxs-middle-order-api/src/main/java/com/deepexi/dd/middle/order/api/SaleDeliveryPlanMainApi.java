package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanMainRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanMainResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanMainRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * SaleDeliveryPlanMainApi
 *
 * @author admin
 * @date Thu Aug 13 15:26:43 CST 2020
 * @version 1.0
 */
@Api(value = "发货编排主表管理")
@RequestMapping("/saleDeliveryPlanMains")
public interface SaleDeliveryPlanMainApi {
    /**
     * 查询发货编排主表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<SaleDeliveryPlanMainResponseDTO> listSaleDeliveryPlanMains(SaleDeliveryPlanMainRequestQuery query);

    /**
     * 分页查询发货编排主表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<SaleDeliveryPlanMainResponseDTO> listSaleDeliveryPlanMainsPage(SaleDeliveryPlanMainRequestQuery query);


    /**
     * 根据ID删除发货编排主表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增发货编排主表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    SaleDeliveryPlanMainResponseDTO insert(@RequestBody SaleDeliveryPlanMainRequestDTO record);

    /**
     * 查询发货编排主表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    SaleDeliveryPlanMainResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改发货编排主表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody SaleDeliveryPlanMainRequestDTO record);

}