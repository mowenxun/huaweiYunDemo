package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.SendGoodsInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SendGoodsInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SendGoodsInfoRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * SendGoodsInfoApi
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
@Api(value = "发货表管理")
@RequestMapping("/sendGoodsInfos")
public interface SendGoodsInfoApi {
    /**
     * 查询发货表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<SendGoodsInfoResponseDTO> listSendGoodsInfos(SendGoodsInfoRequestQuery query);

    /**
     * 分页查询发货表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<SendGoodsInfoResponseDTO> listSendGoodsInfosPage(SendGoodsInfoRequestQuery query);


    /**
     * 根据ID删除发货表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增发货表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    SendGoodsInfoResponseDTO insert(@RequestBody SendGoodsInfoRequestDTO record);

    /**
     * 查询发货表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    SendGoodsInfoResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改发货表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody SendGoodsInfoRequestDTO record);

}