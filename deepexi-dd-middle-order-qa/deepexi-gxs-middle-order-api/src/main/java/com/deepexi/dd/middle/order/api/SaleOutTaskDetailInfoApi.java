package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskDetailInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskDetailInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOutTaskDetailInfoRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * SaleOutTaskDetailInfoApi
 *
 * @author admin
 * @date Thu Sep 17 17:01:09 CST 2020
 * @version 1.0
 */
@Api(value = "销售出库商品明细表管理")
@RequestMapping("/saleOutTaskDetailInfos")
public interface SaleOutTaskDetailInfoApi {
    /**
     * 查询销售出库商品明细表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<SaleOutTaskDetailInfoResponseDTO> listSaleOutTaskDetailInfos(SaleOutTaskDetailInfoRequestQuery query);

    /**
     * 分页查询销售出库商品明细表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<SaleOutTaskDetailInfoResponseDTO> listSaleOutTaskDetailInfosPage(SaleOutTaskDetailInfoRequestQuery query);


    /**
     * 根据ID删除销售出库商品明细表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增销售出库商品明细表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    SaleOutTaskDetailInfoResponseDTO insert(@RequestBody SaleOutTaskDetailInfoRequestDTO record);

    /**
     * 查询销售出库商品明细表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    SaleOutTaskDetailInfoResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改销售出库商品明细表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody SaleOutTaskDetailInfoRequestDTO record);

}