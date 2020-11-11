package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.OrderStatusRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderStatusResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderStatusRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * OrderStatusApi
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
@Api(value = "状态表管理")
@RequestMapping("/orderStatuss")
public interface OrderStatusApi {
    /**
     * 查询状态表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<OrderStatusResponseDTO> listOrderStatuss(OrderStatusRequestQuery query);

    /**
     * 分页查询状态表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<OrderStatusResponseDTO> listOrderStatussPage(OrderStatusRequestQuery query);


    /**
     * 根据ID删除状态表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增状态表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    OrderStatusResponseDTO insert(@RequestBody OrderStatusRequestDTO record);

    /**
     * 查询状态表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    OrderStatusResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改状态表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody OrderStatusRequestDTO record);

}