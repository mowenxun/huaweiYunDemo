package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.OrderExpenseInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderExpenseInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderExpenseInfoRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * OrderExpenseInfoApi
 *
 * @author admin
 * @date Wed Jun 24 09:42:04 CST 2020
 * @version 1.0
 */
@Api(value = "销售订单的费用信息表管理")
@RequestMapping("/orderExpenseInfos")
public interface OrderExpenseInfoApi {
    /**
     * 查询销售订单的费用信息表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<OrderExpenseInfoResponseDTO> listOrderExpenseInfos(OrderExpenseInfoRequestQuery query);

    /**
     * 分页查询销售订单的费用信息表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<OrderExpenseInfoResponseDTO> listOrderExpenseInfosPage(OrderExpenseInfoRequestQuery query);


    /**
     * 根据ID删除销售订单的费用信息表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增销售订单的费用信息表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    OrderExpenseInfoResponseDTO insert(@RequestBody OrderExpenseInfoRequestDTO record);

    /**
     * 查询销售订单的费用信息表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    OrderExpenseInfoResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改销售订单的费用信息表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody OrderExpenseInfoRequestDTO record);

}