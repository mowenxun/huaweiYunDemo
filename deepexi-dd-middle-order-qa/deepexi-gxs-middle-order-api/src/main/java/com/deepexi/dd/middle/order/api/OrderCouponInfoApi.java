package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.OrderCouponInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderCouponInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderCouponInfoRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * OrderCouponInfoApi
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
@Api(value = "优惠信息表管理")
@RequestMapping("/orderCouponInfos")
public interface OrderCouponInfoApi {
    /**
     * 查询优惠信息表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<OrderCouponInfoResponseDTO> listOrderCouponInfos(OrderCouponInfoRequestQuery query);

    /**
     * 分页查询优惠信息表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<OrderCouponInfoResponseDTO> listOrderCouponInfosPage(OrderCouponInfoRequestQuery query);


    /**
     * 根据ID删除优惠信息表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增优惠信息表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    OrderCouponInfoResponseDTO insert(@RequestBody OrderCouponInfoRequestDTO record);

    /**
     * 查询优惠信息表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    OrderCouponInfoResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改优惠信息表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody OrderCouponInfoRequestDTO record);

}