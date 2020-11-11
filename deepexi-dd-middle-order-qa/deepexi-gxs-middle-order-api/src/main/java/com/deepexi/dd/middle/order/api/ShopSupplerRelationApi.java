package com.deepexi.dd.middle.order.api;
import com.deepexi.dd.middle.order.domain.dto.ShopOrderItemRequestDTO;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.ShopSupplerRelationRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.ShopSupplerRelationResponseDTO;
import com.deepexi.dd.middle.order.domain.query.ShopSupplerRelationRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * ShopSupplerRelationApi
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
@Api(value = "已分发订单和店铺订单关系表管理")
@RequestMapping("/shopSupplerRelations")
public interface ShopSupplerRelationApi {
    /**
     * 查询已分发订单和店铺订单关系表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<ShopSupplerRelationResponseDTO> listShopSupplerRelations(ShopSupplerRelationRequestQuery query);

    /**
     * 分页查询已分发订单和店铺订单关系表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<ShopSupplerRelationResponseDTO> listShopSupplerRelationsPage(ShopSupplerRelationRequestQuery query);


    /**
     * 根据ID删除已分发订单和店铺订单关系表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增已分发订单和店铺订单关系表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    ShopSupplerRelationResponseDTO insert(@RequestBody ShopSupplerRelationRequestDTO record);

    /**
     * 查询已分发订单和店铺订单关系表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    ShopSupplerRelationResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改已分发订单和店铺订单关系表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody ShopSupplerRelationRequestDTO record);

    @PostMapping("/saveBatch")
    boolean saveBatch(@RequestBody List<ShopSupplerRelationRequestDTO> list);

}