package com.deepexi.dd.middle.order.api;
import com.deepexi.dd.middle.order.domain.dto.OrderDeliverySelfRaisingInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderDeliverySelfRaisingInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderDeliverySelfRaisingInfoRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * OrderDeliverySelfRaisingInfoApi
 *
 * @author admin
 * @date Wed Aug 26 16:41:35 CST 2020
 * @version 1.0
 */
@Api(value = "出库单自提地址信息表管理")
@RequestMapping("/orderDeliverySelfRaisingInfos")
public interface OrderDeliverySelfRaisingInfoApi {
    /**
     * 查询出库单自提地址信息表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<OrderDeliverySelfRaisingInfoResponseDTO> listOrderDeliverySelfRaisingInfos(OrderDeliverySelfRaisingInfoRequestQuery query);

    /**
     * 分页查询出库单自提地址信息表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<OrderDeliverySelfRaisingInfoResponseDTO> listOrderDeliverySelfRaisingInfosPage(OrderDeliverySelfRaisingInfoRequestQuery query);


    /**
     * 根据ID删除出库单自提地址信息表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增出库单自提地址信息表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    OrderDeliverySelfRaisingInfoResponseDTO insert(@RequestBody OrderDeliverySelfRaisingInfoRequestDTO record);

    /**
     * 查询出库单自提地址信息表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    OrderDeliverySelfRaisingInfoResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改出库单自提地址信息表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody OrderDeliverySelfRaisingInfoRequestDTO record);

}