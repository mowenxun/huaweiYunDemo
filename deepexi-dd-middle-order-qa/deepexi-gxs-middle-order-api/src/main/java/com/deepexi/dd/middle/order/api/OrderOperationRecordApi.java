package com.deepexi.dd.middle.order.api;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderOperationRecordRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * OrderOperationRecordApi
 *
 * @author admin
 * @date Wed Jul 29 15:12:50 CST 2020
 * @version 1.0
 */
@Api(value = "操作记录表管理")
@RequestMapping("/orderOperationRecords")
public interface OrderOperationRecordApi {
    /**
     * 查询操作记录表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<OrderOperationRecordResponseDTO> listOrderOperationRecords(OrderOperationRecordRequestQuery query);

    /**
     * 分页查询操作记录表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<OrderOperationRecordResponseDTO> listOrderOperationRecordsPage(OrderOperationRecordRequestQuery query);


    /**
     * 根据ID删除操作记录表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增操作记录表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    OrderOperationRecordResponseDTO insert(@RequestBody OrderOperationRecordRequestDTO record);

    /**
     * 查询操作记录表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    OrderOperationRecordResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改操作记录表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody OrderOperationRecordRequestDTO record);

}