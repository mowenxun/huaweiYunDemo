package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskAddRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskDetailResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskSignRequestDTO;
import com.deepexi.dd.domain.transaction.domain.query.SaleOutTaskRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName SaleOutTaskService
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-03
 * @Version 1.0
 **/
public interface SaleOutTaskService {
    /**
     * @Description:  新增销售出库单.
     * @Param: [saleOutTaskAddRequestDTO]
     * @return: SaleOutTaskDetailResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/10
     */
    SaleOutTaskInfoResponseDTO insert(SaleOutTaskAddRequestDTO saleOutTaskAddRequestDTO) throws Exception;

    /**
     * @Description:  分页查询销售出库单（出库任务单）列表.
     * @Param: [query]
     * @return: com.deepexi.util.pageHelper.PageBean<com.deepexi.dd.middle.order.domain.vo.SaleOrderInfoResponseVO>
     * @Author: SongTao
     * @Date: 2020/7/6
     */
    PageBean<SaleOutTaskInfoResponseDTO> searchSaleOutTaskPageList(SaleOutTaskRequestQuery query) throws Exception;
    /**
     * @Description:  根据出库单id查销售出库单（出库任务单）详情.
     * @Param: [id]
     * @return: SaleOutTaskDetailResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/11
     */
    SaleOutTaskDetailResponseDTO searchSaleOutTaskInfoBySaleOutTaskId(Long id) throws Exception;

    /**
     * @Description:  根据订单ID及签收状态查询销售出库单（出库任务单）详情.
     * @Param: [id,signStatus]
     * @return: java.util.List<SaleOutTaskDetailResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/11
     */
    List<SaleOutTaskDetailResponseDTO> searchSaleOutTaskInfoBySaleOrderId(Long id,Integer signStatus) throws Exception;

    /**
     * @Description:  根据提货单号查询销售出库单详情.
     * @Param: [id, signStatus]
     * @return: java.util.List<com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskDetailResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/19
     */
    List<SaleOutTaskDetailResponseDTO> searchSaleOutTaskInfoByPickGoodsId(Long id,Integer signStatus) throws Exception;
    /**
     * @Description:  红冲出库单.
     * @Param: [id]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/9
     */
    Boolean hedgeOrder(Long id) throws Exception;
    /**
     * @Description:  出库单签收.
     * @Param: [dto]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/13
     */
    Boolean sign(SaleOutTaskSignRequestDTO dto) throws Exception;
    /**
     * @Description:  根据批量的订单id 查出对应的出库单信息 供app调用.
     * @Param: [query]
     * @return: java.util.List<com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskDetailResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/10
     */
    List<SaleOutTaskDetailResponseDTO> listSaleOutTasksForApp(SaleOutTaskRequestQuery query) throws Exception;

    List<String> importStorageDomainStock(MultipartFile file, Long appId) throws Exception;
}
