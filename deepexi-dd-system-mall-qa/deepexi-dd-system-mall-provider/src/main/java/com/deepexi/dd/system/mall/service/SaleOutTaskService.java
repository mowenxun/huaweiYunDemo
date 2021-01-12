package com.deepexi.dd.system.mall.service;

import com.deepexi.dd.system.mall.domain.dto.SaleOutTaskDetailInfoResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.SaleOutTaskInfoResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.SaleOutTaskInfoSignRequestDTO;
import com.deepexi.dd.system.mall.domain.query.SaleOutTaskRequestQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * @ClassName SaleOutTaskService
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-18
 * @Version 1.0
 **/
public interface SaleOutTaskService {

    /**
     * @Description:  出库单签收.
     * @Param: [dto]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/18
     */
    Boolean sign(SaleOutTaskInfoSignRequestDTO dto) throws Exception;
    /**
     * @Description:  分页查询销售出库单（出库任务单）列表.
     * @Param: [query]
     * @return: com.deepexi.util.pageHelper.PageBean<com.deepexi.dd.system.mall.domain.dto.SaleOutTaskInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/21
     */
    PageBean<SaleOutTaskInfoResponseDTO> searchSaleOutTaskPageList(SaleOutTaskRequestQuery query) throws Exception;
    /**
     * @Description:  根据出库单id查销售出库单（出库任务单）详情.
     * @Param: [id]
     * @return: com.deepexi.dd.system.mall.domain.dto.SaleOutTaskDetailInfoResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/21
     */
    SaleOutTaskDetailInfoResponseDTO searchSaleOutTaskInfoBySaleOutTaskId(Long id) throws Exception;

    /**
     * @Description:  根据订单ID查销售出库单（出库任务单）详情.
     * @Param: [id,signStatus]
     * @return: java.util.List<com.deepexi.dd.system.mall.domain.dto.SaleOutTaskDetailInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/21
     */
    List<SaleOutTaskDetailInfoResponseDTO> searchSaleOutTaskInfoBySaleOrderId(Long id,Integer signStatus) throws Exception;
    /**
     * @Description:  根据提货单号查销售出库单详情.
     * @Param: [id, signStatus]
     * @return: java.util.List<com.deepexi.dd.system.mall.domain.dto.SaleOutTaskDetailInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/19
     */
    List<SaleOutTaskDetailInfoResponseDTO> searchSaleOutTaskInfoByPickGoodsId(Long id, Integer signStatus) throws Exception;
}
