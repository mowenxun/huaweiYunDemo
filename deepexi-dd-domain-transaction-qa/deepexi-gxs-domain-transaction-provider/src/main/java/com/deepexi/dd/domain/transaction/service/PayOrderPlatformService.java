package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.domain.transaction.domain.dto.payOrder.PayOrderInfoDTO;
import com.deepexi.dd.domain.transaction.domain.dto.payOrder.PayOrderInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.PayOrderRequestQuery;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderRequestDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformRequestPageQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JeremyLian
 * @date 2020/10/22 10:20
 */
public interface PayOrderPlatformService {


    /**
     * 查询付款单表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<PayOrderPlatformResponseDTO> list(PayOrderPlatformRequestDTO query);

    /**
     * 分页查询付款单表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<PayOrderPlatformResponseDTO> listPage(PayOrderPlatformRequestPageQuery query);


    /**
     * 根据ID删除
     */
    @DeleteMapping("")
    boolean deleteByIds(@RequestBody List<Long> ids);

    /**
     * 新增付款单表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    PayOrderPlatformResponseDTO insert(@RequestBody PayOrderPlatformRequestDTO record);

    /**
     * 查询付款单表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    PayOrderPlatformResponseDTO selectById(@PathVariable("id") Long id);


    /**
     * 根据ID修改付款单表·
     * @param record
     * @return
     */
    @PutMapping()
    boolean updateById(@RequestBody PayOrderPlatformRequestDTO record);
}
