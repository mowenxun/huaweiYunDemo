package com.deepexi.dd.middle.order.api;
import com.deepexi.dd.middle.order.domain.dto.SalePickReceiveOrderYunLogRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickReceiveOrderYunLogResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickReceiveOrderYunLogRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * SalePickReceiveOrderYunLogApi
 *
 * @author admin
 * @date Wed Sep 23 13:47:55 CST 2020
 * @version 1.0
 */
@Api(value = "管理")
@RequestMapping("/salePickReceiveOrderYunLogs")
public interface SalePickReceiveOrderYunLogApi {
    /**
     * 查询列表
     *
     * @return
     */
    @GetMapping("/list")
    List<SalePickReceiveOrderYunLogResponseDTO> listSalePickReceiveOrderYunLogs(SalePickReceiveOrderYunLogRequestQuery query);

    /**
     * 分页查询列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<SalePickReceiveOrderYunLogResponseDTO> listSalePickReceiveOrderYunLogsPage(SalePickReceiveOrderYunLogRequestQuery query);


    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    @PostMapping("")
    SalePickReceiveOrderYunLogResponseDTO insert(@RequestBody SalePickReceiveOrderYunLogRequestDTO record);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    SalePickReceiveOrderYunLogResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody SalePickReceiveOrderYunLogRequestDTO record);

}