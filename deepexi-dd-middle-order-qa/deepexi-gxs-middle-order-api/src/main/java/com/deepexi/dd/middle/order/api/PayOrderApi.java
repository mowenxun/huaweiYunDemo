package com.deepexi.dd.middle.order.api;

import com.deepexi.dd.middle.order.domain.dto.PayOrderRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderResponseDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderRequestPageQuery;
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
@Api(value = "付款单api")
@RequestMapping("/payOrderApi")
public interface PayOrderApi {
    /**
     * 查询付款单表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<PayOrderResponseDTO> list(PayOrderRequestDTO query);

    /**
     * 分页查询付款单表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<PayOrderResponseDTO> listPage(PayOrderRequestPageQuery query);


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
    PayOrderResponseDTO insert(@RequestBody PayOrderRequestDTO record);

    /**
     * 查询付款单表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    PayOrderResponseDTO selectById(@PathVariable("id") Long id);


    /**
     * 根据ID修改付款单表·
     * @param record
     * @return
     */
    @PutMapping()
    boolean updateById(@RequestBody PayOrderRequestDTO record);

}