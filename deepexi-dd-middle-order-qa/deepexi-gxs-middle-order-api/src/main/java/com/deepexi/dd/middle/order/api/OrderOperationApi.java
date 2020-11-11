package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderOperationRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * OrderOperationApi
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
@Api(value = "按钮表管理")
@RequestMapping("/orderOperations")
public interface OrderOperationApi {
    /**
     * 查询按钮表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<OrderOperationResponseDTO> listOrderOperations(OrderOperationRequestQuery query);

    /**
     * 分页查询按钮表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<OrderOperationResponseDTO> listOrderOperationsPage(OrderOperationRequestQuery query);


    /**
     * 根据ID删除按钮表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增按钮表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    OrderOperationResponseDTO insert(@RequestBody OrderOperationRequestDTO record);

    /**
     * 查询按钮表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    OrderOperationResponseDTO selectById(@PathVariable Long id);

}