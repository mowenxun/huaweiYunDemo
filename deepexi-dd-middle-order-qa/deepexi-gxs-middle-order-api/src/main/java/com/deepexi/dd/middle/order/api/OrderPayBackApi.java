package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.OrderPayBackRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderPayBackResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderPayBackRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * OrderPayBackApi
 *
 * @author admin
 * @date Wed Jul 22 16:27:51 CST 2020
 * @version 1.0
 */
@Api(value = "支付回调参数表管理")
@RequestMapping("/orderPayBacks")
public interface OrderPayBackApi {
    /**
     * 查询支付回调参数表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<OrderPayBackResponseDTO> listOrderPayBacks(OrderPayBackRequestQuery query);

    /**
     * 分页查询支付回调参数表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<OrderPayBackResponseDTO> listOrderPayBacksPage(OrderPayBackRequestQuery query);


    /**
     * 根据ID删除支付回调参数表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增支付回调参数表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    OrderPayBackResponseDTO insert(@RequestBody OrderPayBackRequestDTO record);

    /**
     * 查询支付回调参数表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    OrderPayBackResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改支付回调参数表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody OrderPayBackRequestDTO record);

}