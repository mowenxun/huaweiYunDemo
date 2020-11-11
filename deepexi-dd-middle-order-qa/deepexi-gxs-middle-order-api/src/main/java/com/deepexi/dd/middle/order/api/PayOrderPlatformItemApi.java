package com.deepexi.dd.middle.order.api;

import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformItemResponseDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformItemRequestPageQuery;
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
@RequestMapping("/payOrderPlatformItemMiddleApi")
public interface PayOrderPlatformItemApi {
    /**
     * 查询付款单明细列表
     *
     * @return
     */
    @GetMapping("/list")
    List<PayOrderPlatformItemResponseDTO> list(PayOrderPlatformItemRequestDTO query);

    /**
     * 分页查询付款单明细列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<PayOrderPlatformItemResponseDTO> listPage(PayOrderPlatformItemRequestPageQuery query);


    /**
     * 根据ID删除
     */
    @DeleteMapping("/delete")
    boolean deleteByIds(@RequestBody List<Long> ids);

    /**
     * 新增付款单明细
     *
     * @param record
     * @return
     */
    @PostMapping("/insert")
    PayOrderPlatformItemResponseDTO insert(@RequestBody PayOrderPlatformItemRequestDTO record);

    /**
     * 查询付款单明细详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    PayOrderPlatformItemResponseDTO selectById(@PathVariable("id") Long id);


    /**
     * 根据ID修改付款单明细·
     * @param record
     * @return
     */
    @PutMapping("/updateById")
    boolean updateById(@RequestBody PayOrderPlatformItemRequestDTO record);

}