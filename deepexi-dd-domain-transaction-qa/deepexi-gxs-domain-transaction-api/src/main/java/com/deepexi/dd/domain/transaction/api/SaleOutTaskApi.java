package com.deepexi.dd.domain.transaction.api;

import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskDetailResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskSignRequestDTO;
import com.deepexi.dd.domain.transaction.domain.query.SaleOutTaskRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName SaleOutTaskApi
 * @Description 销售出库单
 * @Author SongTao
 * @Date 2020-07-18
 * @Version 1.0
 **/
@Api(value = "销售出库单模块rpc接口")
@RequestMapping("/open-api/v1/domain/saleOutTask")
public interface SaleOutTaskApi {
    /**
     * @Description:  出库单签收.
     * @Param: [dto]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/18
     */
    @PostMapping("/sign")
    Boolean sign(@RequestBody @Valid SaleOutTaskSignRequestDTO dto) throws Exception;
    /**
     * @Description:  分页查询销售出库单（出库任务单）列表.
     * @Param: [query]
     * @return: com.deepexi.util.pageHelper.PageBean<com.deepexi.dd.middle.order.domain.vo.SaleOrderInfoResponseVO>
     * @Author: SongTao
     * @Date: 2020/7/6
     */
    @GetMapping("/page")
    PageBean<SaleOutTaskInfoResponseDTO> searchSaleOutTaskPageList(SaleOutTaskRequestQuery query) throws Exception;
    /**
     * @Description:  根据出库单id查销售出库单（出库任务单）详情.
     * @Param: [id]
     * @return: com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskDetailResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/11
     */
    @GetMapping(value = "/searchSaleOutTaskInfoBySaleOutTaskId/{id}")
    SaleOutTaskDetailResponseDTO searchSaleOutTaskInfoBySaleOutTaskId(@PathVariable Long id) throws Exception;

    /**
     * @Description:  根据订单ID及签收状态查询销售出库单（出库任务单）详情.
     * @Param: [id,signStatus]
     * @return: java.util.List<com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskDetailResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/11
     */
    @GetMapping(value = "/searchSaleOutTaskInfoBySaleOrderId/{id}/{signStatus}")
    List<SaleOutTaskDetailResponseDTO> searchSaleOutTaskInfoBySaleOrderId(@PathVariable Long id,@PathVariable Integer signStatus) throws Exception;

    /**
     * @Description:  根据提货单号查询销售出库单详情.
     * @Param: [id, signStatus]
     * @return: java.util.List<com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskDetailResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/19
     */
    @GetMapping(value = "/searchSaleOutTaskInfoByPickGoodsId/{id}/{signStatus}")
    List<SaleOutTaskDetailResponseDTO> searchSaleOutTaskInfoByPickGoodsId(@PathVariable Long id,@PathVariable Integer signStatus) throws Exception;

    /**
     * @Description:  根据批量的订单id 查出对应的出库单信息 供app调用.
     * @Param: [query]
     * @return: java.util.List<com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskDetailResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/10
     */
    @GetMapping(value = "/listSaleOutTasksForApp")
    List<SaleOutTaskDetailResponseDTO> listSaleOutTasksForApp(SaleOutTaskRequestQuery query) throws Exception;
}
