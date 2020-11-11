package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.SaleCloudInterfaceRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleCloudInterfaceRecordResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleCloudInterfaceRecordRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * SaleCloudInterfaceRecordApi
 *
 * @author admin
 * @date Wed Aug 26 19:53:05 CST 2020
 * @version 1.0
 */
@Api(value = "调用云仓接口记录表管理")
@RequestMapping("/saleCloudInterfaceRecords")
public interface SaleCloudInterfaceRecordApi {
    /**
     * 查询调用云仓接口记录表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<SaleCloudInterfaceRecordResponseDTO> listSaleCloudInterfaceRecords(SaleCloudInterfaceRecordRequestQuery query);

    /**
     * 分页查询调用云仓接口记录表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<SaleCloudInterfaceRecordResponseDTO> listSaleCloudInterfaceRecordsPage(SaleCloudInterfaceRecordRequestQuery query);


    /**
     * 根据ID删除调用云仓接口记录表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增调用云仓接口记录表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    SaleCloudInterfaceRecordResponseDTO insert(@RequestBody SaleCloudInterfaceRecordRequestDTO record);

    /**
     * 查询调用云仓接口记录表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    SaleCloudInterfaceRecordResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改调用云仓接口记录表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody SaleCloudInterfaceRecordRequestDTO record);

}