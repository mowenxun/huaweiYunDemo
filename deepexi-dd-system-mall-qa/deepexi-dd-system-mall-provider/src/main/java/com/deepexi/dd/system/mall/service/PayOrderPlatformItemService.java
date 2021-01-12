package com.deepexi.dd.system.mall.service;

import com.deepexi.dd.middle.order.domain.dto.PayOrderItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderItemResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformItemResponseDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderItemRequestPageQuery;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformItemRequestPageQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JeremyLian
 * @date 2020/10/31 15:09
 */
public interface PayOrderPlatformItemService {

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
