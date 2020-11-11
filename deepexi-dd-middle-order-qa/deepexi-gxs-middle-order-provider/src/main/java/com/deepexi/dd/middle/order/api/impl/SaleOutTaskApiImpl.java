package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SaleOutTaskApi;
import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOutTaskMiddleRequestQuery;
import com.deepexi.dd.middle.order.service.SaleOutTaskService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * SaleOutTaskApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SaleOutTaskApiImpl implements SaleOutTaskApi {

    @Autowired
    private SaleOutTaskService saleOutTaskService;

    @Override
    @ApiOperation("查询销售出库单（出库任务单）列表")
    public List<SaleOutTaskMiddleResponseDTO> listSaleOutTasks(SaleOutTaskMiddleRequestQuery query) {
        return saleOutTaskService.listSaleOutTasks(query);
    }

    @Override
    public List<SaleOutTaskMiddleResponseDTO> listSaleOutTasksAggregation(SaleOutTaskMiddleRequestQuery query) throws Exception {
        return saleOutTaskService.listSaleOutTasksAggregation(query);
    }

    /**
     * @Description:  分页查询销售出库单（出库任务单）列表.
     * @Param: [query]
     * @return: com.deepexi.util.pageHelper.PageBean<com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/6
     */
    @Override
    @ApiOperation("分页查询销售出库单（出库任务单）列表")
    public Payload<PageBean<SaleOutTaskMiddleResponseDTO>> listSaleOutTasksPage(SaleOutTaskMiddleRequestQuery query)  throws Exception{
        PageBean<SaleOutTaskMiddleResponseDTO> pageBean = saleOutTaskService.listSaleOutTasksPage(query);
        return new Payload<>(pageBean);
    }

    @Override
    @ApiOperation("批量删除销售出库单（出库任务单）")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return saleOutTaskService.deleteByIdIn(id);
    }

    /**
     * @Description:  新增销售出库单（出库任务单）.
     * @Param: [record]
     * @return: com.deepexi.dd.middle.order.domain.dto.SaleOutTaskResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/3
     */
    @Override
    @ApiOperation("新增销售出库单（出库任务单）")
    public SaleOutTaskMiddleResponseDTO insert(@RequestBody SaleOutTaskMiddleRequestDTO saleOutTaskMiddleRequestDTO) throws Exception{
        return saleOutTaskService.insert(saleOutTaskMiddleRequestDTO);
    }

    /**
     * @Description:  根据出库单id查销售出库单（出库任务单）详情.
     * @Param: [id]
     * @return: com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/7
     */
    @Override
    @ApiOperation("根据ID查询销售出库单（出库任务单）")
    public SaleOutTaskMiddleResponseDTO searchSaleOutTaskInfoBySaleOutTaskId(@PathVariable Long id)  throws Exception{
        SaleOutTaskMiddleResponseDTO result = saleOutTaskService.searchSaleOutTaskInfoBySaleOutTaskId(id);
        return result != null ? result.clone(SaleOutTaskMiddleResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    /**
     * @param id
     * @Description: 通过订单ID查销售出库单（出库任务单）详情.
     * @Param: [id,signStatus]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/11
     */
    @Override
    public List<SaleOutTaskMiddleResponseDTO> searchSaleOutTaskInfoBySaleOrderId(@PathVariable Long id,@PathVariable Integer signStatus) throws Exception {
        List<SaleOutTaskMiddleResponseDTO> result = saleOutTaskService.searchSaleOutTaskInfoBySaleOrderId(id,signStatus);
        return result;
    }

    /**
     * @param id
     * @param signStatus
     * @Description: 根据提货单号查销售出库单详情.
     * @Param: [id, signStatus]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/19
     */
    @Override
    public List<SaleOutTaskMiddleResponseDTO> searchSaleOutTaskInfoByPickGoodsId(@PathVariable Long id,@PathVariable Integer signStatus) throws Exception {
        return saleOutTaskService.searchSaleOutTaskInfoByPickGoodsId(id,signStatus);
    }

    /**
     * @param id
     * @Description: 根据主键id查询出库单.
     * @Param: [id]
     * @return: com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/20
     */
    @Override
    public SaleOutTaskMiddleResponseDTO selectById(@PathVariable Long id) throws Exception {
        return saleOutTaskService.selectById(id);
    }

    /**
     * @Description:  根据ID修改销售出库单（出库任务单）.
     * @Param: [record]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/9
     */
    @Override
    @ApiOperation("根据ID更新销售出库单（出库任务单）")
    public Boolean updateById(@RequestBody SaleOutTaskMiddleRequestDTO record) throws Exception{
        return saleOutTaskService.updateById(record);
    }

    /**
     * @param id
     * @Description: 出库单红冲.
     * @Param: [id]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/8
     */
    @Override
    public Boolean hedgeOrder(Long id) throws Exception{
        return saleOutTaskService.hedgeOrder(id);
    }

    /**
     * @param saleOutTaskMiddleRequestDTO
     * @Description: 出库单签收.
     * @Param: [idList]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/13
     */
    @Override
    public Boolean sign(@RequestBody SaleOutTaskMiddleRequestDTO saleOutTaskMiddleRequestDTO) throws Exception{
        return saleOutTaskService.sign(saleOutTaskMiddleRequestDTO);
    }

    /**
     * @param day
     * @Description: 自动签收.
     * @Param: [day]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/21
     */
    @Override
    public Boolean autoSign(@PathVariable Integer day) throws Exception {
        return saleOutTaskService.autoSign(day);
    }

    @Override
    public List<SaleOutTaskMiddleResponseDTO> findSaleOutTaskByIds(SaleOutTaskMiddleRequestQuery query) {
        return saleOutTaskService.findSaleOutTaskByIds(query);
    }
}
