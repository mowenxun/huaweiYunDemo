package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.OrderDeliveryInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderDeliveryInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderDeliveryInfoRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * OrderDeliveryInfoApi
 *
 * @author admin
 * @date Wed Jul 01 19:40:51 CST 2020
 * @version 1.0
 */
@Api(value = "销售出库单物流信息管理")
@RequestMapping("/orderDeliveryInfos")
public interface OrderDeliveryInfoApi {
    /**
     * 查询销售出库单物流信息列表
     *
     * @return
     */
    @GetMapping("/list")
    List<OrderDeliveryInfoResponseDTO> listOrderDeliveryInfos(OrderDeliveryInfoRequestQuery query);

    /**
     * 分页查询销售出库单物流信息列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<OrderDeliveryInfoResponseDTO> listOrderDeliveryInfosPage(OrderDeliveryInfoRequestQuery query);


    /**
     * 根据ID删除销售出库单物流信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增销售出库单物流信息
     *
     * @param record
     * @return
     */
    @PostMapping("")
    OrderDeliveryInfoResponseDTO insert(@RequestBody OrderDeliveryInfoRequestDTO record);

    /**
     * 查询销售出库单物流信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    OrderDeliveryInfoResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改销售出库单物流信息
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody OrderDeliveryInfoRequestDTO record);

}