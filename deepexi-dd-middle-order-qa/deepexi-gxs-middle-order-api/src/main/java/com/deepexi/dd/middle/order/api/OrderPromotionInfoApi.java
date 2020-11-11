package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.OrderPromotionInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderPromotionInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderPromotionInfoRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * OrderPromotionInfoApi
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
@Api(value = "销售订单的促销信息表管理")
@RequestMapping("/orderPromotionInfos")
public interface OrderPromotionInfoApi {
    /**
     * 查询销售订单的促销信息表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<OrderPromotionInfoResponseDTO> listOrderPromotionInfos(OrderPromotionInfoRequestQuery query);

    /**
     * 分页查询销售订单的促销信息表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<OrderPromotionInfoResponseDTO> listOrderPromotionInfosPage(OrderPromotionInfoRequestQuery query);


    /**
     * 根据ID删除销售订单的促销信息表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增销售订单的促销信息表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    OrderPromotionInfoResponseDTO insert(@RequestBody OrderPromotionInfoRequestDTO record);

    /**
     * 查询销售订单的促销信息表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    OrderPromotionInfoResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改销售订单的促销信息表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody OrderPromotionInfoRequestDTO record);

}