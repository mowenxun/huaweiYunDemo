package com.deepexi.dd.middle.order.api;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsConsigneeRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsConsigneeResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsConsigneeRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SalePickGoodsConsigneeApi
 *
 * @author admin
 * @date Mon Aug 24 16:35:15 CST 2020
 * @version 1.0
 */
@Api(value = "提货计划收货地址信息表管理")
@RequestMapping("/salePickGoodsConsignees")
public interface SalePickGoodsConsigneeApi {
    /**
     * 查询提货计划收货地址信息表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<SalePickGoodsConsigneeResponseDTO> listSalePickGoodsConsignees(SalePickGoodsConsigneeRequestQuery query);

    /**
     * 分页查询提货计划收货地址信息表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<SalePickGoodsConsigneeResponseDTO> listSalePickGoodsConsigneesPage(SalePickGoodsConsigneeRequestQuery query);


    /**
     * 根据ID删除提货计划收货地址信息表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增提货计划收货地址信息表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    SalePickGoodsConsigneeResponseDTO insert(@RequestBody SalePickGoodsConsigneeRequestDTO record);

    /**
     * 查询提货计划收货地址信息表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    SalePickGoodsConsigneeResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改提货计划收货地址信息表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody SalePickGoodsConsigneeRequestDTO record);

    @PostMapping("/selectByPickCode")
    SalePickGoodsConsigneeResponseDTO selectByPickCode(@RequestBody SalePickGoodsConsigneeRequestQuery salePickGoodsConsigneeRequestQuery);
}