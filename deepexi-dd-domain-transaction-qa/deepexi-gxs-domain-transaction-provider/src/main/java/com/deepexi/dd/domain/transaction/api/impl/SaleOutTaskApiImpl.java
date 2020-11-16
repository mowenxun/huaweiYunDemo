package com.deepexi.dd.domain.transaction.api.impl;

import com.deepexi.dd.domain.transaction.api.SaleOutTaskApi;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskDetailResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskSignRequestDTO;
import com.deepexi.dd.domain.transaction.domain.query.SaleOutTaskRequestQuery;
import com.deepexi.dd.domain.transaction.service.SaleOutTaskService;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName SaleOutTaskApiImpl
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-18
 * @Version 1.0
 **/
@RestController
public class SaleOutTaskApiImpl implements SaleOutTaskApi {

    @Autowired
    SaleOutTaskService saleOutTaskService;

    /**
     * @param dto
     * @Description: 出库单签收.
     * @Param: [dto]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/7/18
     */
    @Override
    public Boolean sign(@RequestBody @Valid SaleOutTaskSignRequestDTO dto) throws Exception {
        return saleOutTaskService.sign(dto);
    }

    /**
     * @param query
     * @Description: 分页查询销售出库单（出库任务单）列表.
     * @Param: [query]
     * @return: com.deepexi.util.pageHelper.PageBean<com.deepexi.dd.middle.order.domain.vo.SaleOrderInfoResponseVO>
     * @Author: SongTao
     * @Date: 2020/7/6
     */
    @Override
    public PageBean<SaleOutTaskInfoResponseDTO> searchSaleOutTaskPageList(SaleOutTaskRequestQuery query) throws Exception {
        return saleOutTaskService.searchSaleOutTaskPageList(query);
    }

    /**
     * @param id
     * @Description: 根据出库单id查销售出库单（出库任务单）详情.
     * @Param: [id]
     * @return: com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskDetailResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/11
     */
    @Override
    public SaleOutTaskDetailResponseDTO searchSaleOutTaskInfoBySaleOutTaskId(@PathVariable Long id) throws Exception {
        return saleOutTaskService.searchSaleOutTaskInfoBySaleOutTaskId(id);
    }

    /**
     * @param id
     * @Description: 根据订单ID及签收状态查询销售出库单（出库任务单）详情.
     * @Param: [id,signStatus]
     * @return: java.util.List<com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskDetailResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/11
     */
    @Override
    public List<SaleOutTaskDetailResponseDTO> searchSaleOutTaskInfoBySaleOrderId(@PathVariable Long id,@PathVariable Integer signStatus) throws Exception {
        return saleOutTaskService.searchSaleOutTaskInfoBySaleOrderId(id,signStatus);
    }

    /**
     * @param id
     * @param signStatus
     * @Description: 根据提货单号查询销售出库单详情.
     * @Param: [id, signStatus]
     * @return: java.util.List<com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskDetailResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/19
     */
    @Override
    public List<SaleOutTaskDetailResponseDTO> searchSaleOutTaskInfoByPickGoodsId(@PathVariable Long id,@PathVariable Integer signStatus) throws Exception {
        return saleOutTaskService.searchSaleOutTaskInfoByPickGoodsId(id,signStatus);
    }

    /**
     * @param query
     * @Description: 根据批量的订单id 查出对应的出库单信息 供app调用.
     * @Param: [query]
     * @return: java.util.List<com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskDetailResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/10
     */
    @Override
    public List<SaleOutTaskDetailResponseDTO> listSaleOutTasksForApp(SaleOutTaskRequestQuery query) throws Exception {
        return saleOutTaskService.listSaleOutTasksForApp(query);
    }
}
