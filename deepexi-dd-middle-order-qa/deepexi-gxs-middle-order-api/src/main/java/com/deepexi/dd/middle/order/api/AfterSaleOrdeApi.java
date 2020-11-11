package com.deepexi.dd.middle.order.api;
import com.deepexi.dd.middle.order.domain.dto.AfterSaleOrdeRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.AfterSaleOrdeResponseDTO;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrdeRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * AfterSaleOrdeApi
 *
 * @author admin
 * @date Fri Oct 16 14:17:12 CST 2020
 * @version 1.0
 */
@Api(value = "售后申请单管理")
@RequestMapping("/afterSaleOrdes")
public interface AfterSaleOrdeApi {
    /**
     * 查询售后申请单列表
     *
     * @return
     */
    @GetMapping("/list")
    List<AfterSaleOrdeResponseDTO> listAfterSaleOrdes(AfterSaleOrdeRequestQuery query);

    /**
     * 分页查询售后申请单列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<AfterSaleOrdeResponseDTO> listAfterSaleOrdesPage(AfterSaleOrdeRequestQuery query);


    /**
     * 根据ID删除售后申请单
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增售后申请单
     *
     * @param record
     * @return
     */
    @PostMapping("")
    AfterSaleOrdeResponseDTO insert(@RequestBody AfterSaleOrdeRequestDTO record);

    /**
     * 查询售后申请单详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    AfterSaleOrdeResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改售后申请单
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody AfterSaleOrdeRequestDTO record);

}