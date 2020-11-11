package com.deepexi.dd.middle.order.api;
import com.deepexi.dd.middle.order.domain.dto.OrderReturnInfoAddRequestDTO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.OrderReturnInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderReturnInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderReturnInfoRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * OrderReturnInfoApi
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
@Api(value = "销售订单退货单信息表管理")
@RequestMapping("/orderReturnInfos")
public interface OrderReturnInfoApi {
    /**
     * 查询销售订单退货单信息表列表
     *
     * @return
     */
    @GetMapping("/list")
    Payload<List<OrderReturnInfoResponseDTO>> listOrderReturnInfos(OrderReturnInfoRequestQuery query);

    /**
     * 分页查询销售订单退货单信息表列表
     *
     * @return
     */
    @GetMapping("/page")
    Payload<PageBean<OrderReturnInfoResponseDTO>> listOrderReturnInfosPage(OrderReturnInfoRequestQuery query);


    /**
     * 根据ID删除销售订单退货单信息表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Payload<Boolean> deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增销售订单退货单信息表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    Payload<OrderReturnInfoResponseDTO> insert(@RequestBody OrderReturnInfoAddRequestDTO record);

    /**
     * 查询销售订单退货单信息表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Payload<OrderReturnInfoResponseDTO> selectById(@PathVariable Long id);


    /**
     * 根据ID修改销售订单退货单信息表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Payload<Boolean> updateById(@PathVariable Long id, @RequestBody OrderReturnInfoRequestDTO record);

}