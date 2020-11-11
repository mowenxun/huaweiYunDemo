package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.OrderConsigneeInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderConsigneeInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderConsigneeInfoRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * OrderConsigneeInfoApi
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
@Api(value = "收货地址信息表管理")
@RequestMapping("/orderConsigneeInfos")
public interface OrderConsigneeInfoApi {
    /**
     * 查询收货地址信息表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<OrderConsigneeInfoResponseDTO> listOrderConsigneeInfos(OrderConsigneeInfoRequestQuery query);

    /**
     * 分页查询收货地址信息表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<OrderConsigneeInfoResponseDTO> listOrderConsigneeInfosPage(OrderConsigneeInfoRequestQuery query);


    /**
     * 根据ID删除收货地址信息表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增收货地址信息表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    OrderConsigneeInfoResponseDTO insert(@RequestBody OrderConsigneeInfoRequestDTO record);

    /**
     * 查询收货地址信息表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    OrderConsigneeInfoResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改收货地址信息表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody OrderConsigneeInfoRequestDTO record);

}