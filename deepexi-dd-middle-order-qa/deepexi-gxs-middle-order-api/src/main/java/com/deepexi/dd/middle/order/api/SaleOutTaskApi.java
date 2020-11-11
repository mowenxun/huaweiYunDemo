package com.deepexi.dd.middle.order.api;

import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOutTaskMiddleRequestQuery;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SaleOutTaskApi
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
@Api(value = "销售出库单（出库任务单）管理")
@RequestMapping("/saleOutTasks")
public interface SaleOutTaskApi {
    /**
     * 查询销售出库单（出库任务单）列表
     *
     * @return
     */
    @GetMapping("/list")
    List<SaleOutTaskMiddleResponseDTO> listSaleOutTasks(SaleOutTaskMiddleRequestQuery query);

    /**
     * 询销售出库单（出库任务单）聚合信息列表.
     *
     * @param query
     * @return
     */
    @GetMapping("/list/aggregation")
    List<SaleOutTaskMiddleResponseDTO> listSaleOutTasksAggregation(SaleOutTaskMiddleRequestQuery query) throws Exception;

    /**
     * @Description:  分页查询销售出库单（出库任务单）列表.
     * @Param: [query]
     * @return: com.deepexi.util.pageHelper.PageBean<com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/6
     */
    @GetMapping("/page")
    Payload<PageBean<SaleOutTaskMiddleResponseDTO>> listSaleOutTasksPage(SaleOutTaskMiddleRequestQuery query) throws Exception;


    /**
     * 根据ID删除销售出库单（出库任务单）
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);
    /**
     * @Description:  新增销售出库单（出库任务单）.
     * @Param: [record]
     * @return: com.deepexi.dd.middle.order.domain.dto.SaleOutTaskResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/3
     */
    @PostMapping("/insert")
    SaleOutTaskMiddleResponseDTO insert(@RequestBody SaleOutTaskMiddleRequestDTO saleOutTaskMiddleRequestDTO) throws Exception;

    /**
     * @Description:  根据出库单id查销售出库单（出库任务单）详情.
     * @Param: [id]
     * @return: com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/7
     */
    @RequestMapping(value = "/searchSaleOutTaskInfoBySaleOutTaskId/{id}",method = RequestMethod.GET)
    SaleOutTaskMiddleResponseDTO searchSaleOutTaskInfoBySaleOutTaskId(@PathVariable Long id) throws Exception;
    /**
     * @Description:  通过订单ID查销售出库单（出库任务单）详情.
     * @Param: [id,signStatus]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/11
     */
    @RequestMapping(value = "/searchSaleOutTaskInfoBySaleOrderId/{id}/{signStatus}",method = RequestMethod.GET)
    List<SaleOutTaskMiddleResponseDTO> searchSaleOutTaskInfoBySaleOrderId(@PathVariable Long id,
                                                                          @PathVariable Integer signStatus) throws Exception;
    /**
     * @Description:  根据提货单号查销售出库单详情.
     * @Param: [id, signStatus]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/19
     */
    @GetMapping(value = "/searchSaleOutTaskInfoByPickGoodsId/{id}/{signStatus}")
    List<SaleOutTaskMiddleResponseDTO> searchSaleOutTaskInfoByPickGoodsId(@PathVariable Long id,
                                                                          @PathVariable Integer signStatus) throws Exception;

    /**
     * @Description:  根据主键id查询出库单.
     * @Param: [id]
     * @return: com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/20
     */
    @GetMapping("/{id}")
    SaleOutTaskMiddleResponseDTO selectById(@PathVariable Long id) throws Exception;

    /**
     * @Description:  根据ID修改销售出库单（出库任务单）.
     * @Param: [record]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/9
     */
    @PutMapping("/saleOutTask/{id}")
    Boolean updateById(@RequestBody SaleOutTaskMiddleRequestDTO record) throws Exception;
    /**
     * @Description:  出库单红冲.
     * @Param: [id]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/8
     */
    @RequestMapping(value = "hedgeOrder",method = RequestMethod.POST)
    Boolean hedgeOrder(@RequestParam(value = "id") Long id) throws Exception;

    /**
     * @Description:  出库单签收.
     * @Param: [saleOutTaskMiddleRequestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/13
     */
    @RequestMapping(value = "sign",method = RequestMethod.POST)
    Boolean sign(@RequestBody SaleOutTaskMiddleRequestDTO saleOutTaskMiddleRequestDTO) throws Exception;
    /**
     * @Description:  自动签收.
     * @Param: [day]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/21
     */
    @PutMapping(value = "/autoSign/{day}")
    Boolean autoSign(@PathVariable Integer day) throws Exception;

    /**
     * 根据订单ID查询列表
     * @param query
     * @return
     */
    @GetMapping("/findSaleOutTaskByIds")
    List<SaleOutTaskMiddleResponseDTO> findSaleOutTaskByIds(@RequestBody SaleOutTaskMiddleRequestQuery query);
}