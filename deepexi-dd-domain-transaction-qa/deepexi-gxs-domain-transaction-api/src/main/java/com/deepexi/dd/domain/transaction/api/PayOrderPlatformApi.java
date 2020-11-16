package com.deepexi.dd.domain.transaction.api;

import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformResponseDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformRequestPageQuery;
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
@RequestMapping("/payOrderPlatformApi")
public interface PayOrderPlatformApi {
    /**
     * 查询付款单表列表
     * @return
     */
    @GetMapping("/list")
    List<PayOrderPlatformResponseDTO> list(PayOrderPlatformRequestDTO query);

    /**
     * 分页查询付款单表列表
     * @return
     */
    @GetMapping("/page")
    PageBean<PayOrderPlatformResponseDTO> listPage(PayOrderPlatformRequestPageQuery query);


    /**
     * 根据ID删除
     */
    @DeleteMapping("/delete")
    boolean deleteByIds(@RequestBody List<Long> ids);

    /**
     * 新增付款单表
     * @param record
     * @return
     */
    @PostMapping("/insert")
    PayOrderPlatformResponseDTO insert(@RequestBody PayOrderPlatformRequestDTO record);

    /**
     * 查询付款单表详情
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
    @PutMapping("updateById")
    boolean updateById(@RequestBody PayOrderPlatformRequestDTO record);

}