package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanInfoRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * SaleDeliveryPlanInfoApi
 *
 * @author admin
 * @date Thu Aug 13 15:26:43 CST 2020
 * @version 1.0
 */
@Api(value = "发货计划表管理")
@RequestMapping("/saleDeliveryPlanInfos")
public interface SaleDeliveryPlanInfoApi {
    /**
     * 查询发货计划表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<SaleDeliveryPlanInfoResponseDTO> listSaleDeliveryPlanInfos(SaleDeliveryPlanInfoRequestQuery query);

    /**
     * 分页查询发货计划表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<SaleDeliveryPlanInfoResponseDTO> listSaleDeliveryPlanInfosPage(SaleDeliveryPlanInfoRequestQuery query);


    /**
     * 根据ID删除发货计划表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增发货计划表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    SaleDeliveryPlanInfoResponseDTO insert(@RequestBody SaleDeliveryPlanInfoRequestDTO record);

    /**
     * 查询发货计划表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    SaleDeliveryPlanInfoResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改发货计划表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody SaleDeliveryPlanInfoRequestDTO record);

}