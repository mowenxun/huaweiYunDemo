package com.deepexi.dd.middle.order.api;

import com.deepexi.dd.middle.order.domain.dto.PayOrderItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderItemResponseDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderItemRequestPageQuery;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
@Api(value = "付款单明细api")
@RequestMapping("/payOrderItemApi")
public interface PayOrderItemApi {
    /**
     * 查询付款单明细列表
     *
     * @return
     */
    @GetMapping("/list")
    List<PayOrderItemResponseDTO> list(PayOrderItemRequestDTO query);

    /**
     * 分页查询付款单明细列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<PayOrderItemResponseDTO> listPage(PayOrderItemRequestPageQuery query);


    /**
     * 根据ID删除
     */
    @DeleteMapping("")
    boolean deleteByIds(@RequestBody List<Long> ids);

    /**
     * 新增付款单明细
     *
     * @param record
     * @return
     */
    @PostMapping("")
    PayOrderItemResponseDTO insert(@RequestBody PayOrderItemRequestDTO record);

    /**
     * 查询付款单明细详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    PayOrderItemResponseDTO selectById(@PathVariable("id") Long id);


    /**
     * 根据ID修改付款单明细·
     * @param record
     * @return
     */
    @PutMapping()
    boolean updateById(@RequestBody PayOrderItemRequestDTO record);

}