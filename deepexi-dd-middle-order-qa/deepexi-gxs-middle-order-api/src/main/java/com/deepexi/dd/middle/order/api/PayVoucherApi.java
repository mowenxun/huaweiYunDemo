package com.deepexi.dd.middle.order.api;

import com.deepexi.dd.middle.order.domain.dto.PayVoucherRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayVoucherResponseDTO;
import com.deepexi.dd.middle.order.domain.query.PayVoucherRequestPageQuery;
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
@Api(value = "付款凭证api")
@RequestMapping("/payVoucherApi")
public interface PayVoucherApi {
    /**
     * 查询付款凭证表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<PayVoucherResponseDTO> list(PayVoucherRequestDTO query);

    /**
     * 分页查询付款凭证表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<PayVoucherResponseDTO> listPage(PayVoucherRequestPageQuery query);


    /**
     * 根据ID删除
     */
    @DeleteMapping("")
    boolean deleteByIds(@RequestBody List<Long> ids);

    /**
     * 新增付款凭证表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    PayVoucherResponseDTO insert(@RequestBody PayVoucherRequestDTO record);

    /**
     * 查询付款凭证表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    PayVoucherResponseDTO selectById(@PathVariable("id") Long id);


    /**
     * 根据ID修改付款凭证表·
     * @param record
     * @return
     */
    @PutMapping()
    boolean updateById(@RequestBody PayVoucherRequestDTO record);

}