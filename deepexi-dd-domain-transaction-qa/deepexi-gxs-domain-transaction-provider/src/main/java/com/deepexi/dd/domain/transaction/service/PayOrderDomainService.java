package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.middle.order.domain.dto.PayOrderRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderResponseDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderRequestPageQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author huanghuai
 * @date 2020/10/13
 */
public interface PayOrderDomainService {

    /**
     * 查询付款单表列表
     *
     * @return
     */
    List<PayOrderResponseDTO> list(PayOrderRequestDTO query);

    /**
     * 分页查询付款单表列表
     *
     * @return
     */
    PageBean<PayOrderResponseDTO> listPage(PayOrderRequestPageQuery query);


    /**
     * 根据ID删除
     */
    boolean deleteByIds(@RequestBody List<Long> ids);

    /**
     * 新增付款单表
     *
     * @param record
     * @return
     */
    PayOrderResponseDTO insert(@RequestBody PayOrderRequestDTO record);

    /**
     * 查询付款单表详情
     *
     * @param id
     * @return
     */
    PayOrderResponseDTO selectById(@PathVariable("id") Long id);


    /**
     * 根据ID修改付款单表·
     * @param record
     * @return
     */
    boolean updateById(@RequestBody PayOrderRequestDTO record);
}
