package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.SalePickOrderYunLogRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickOrderYunLogResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickOrderYunLogRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * SalePickOrderYunLogApi
 *
 * @author admin
 * @date Thu Aug 27 21:37:43 CST 2020
 * @version 1.0
 */
@Api(value = "管理")
@RequestMapping("/salePickOrderYunLogs")
public interface SalePickOrderYunLogApi {
    /**
     * 查询列表
     *
     * @return
     */
    @GetMapping("/list")
    List<SalePickOrderYunLogResponseDTO> listSalePickOrderYunLogs(SalePickOrderYunLogRequestQuery query);

    /**
     * 分页查询列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<SalePickOrderYunLogResponseDTO> listSalePickOrderYunLogsPage(SalePickOrderYunLogRequestQuery query);


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
    SalePickOrderYunLogResponseDTO insert(@RequestBody SalePickOrderYunLogRequestDTO record);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    SalePickOrderYunLogResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody SalePickOrderYunLogRequestDTO record);

}