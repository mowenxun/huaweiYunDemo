package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOutTaskMiddleRequestQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * SaleOutTaskService
 *
 * @author admin
 * @version 1.0
 * @date Wed Jun 24 09:42:06 CST 2020
 */
public interface SaleOutTaskService {


    /**
     * @Description: 查询销售出库单（出库任务单）列表.
     * @Param: [query]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/6
     */
    List<SaleOutTaskMiddleResponseDTO> listSaleOutTasks(SaleOutTaskMiddleRequestQuery query);

    /**
     * 询销售出库单（出库任务单）聚合信息列表.
     *
     * @param query
     * @return
     */
    List<SaleOutTaskMiddleResponseDTO> listSaleOutTasksAggregation(SaleOutTaskMiddleRequestQuery query) throws Exception;

    /**
     * @Description: 分页查询销售出库单（出库任务单）列表.
     * @Param: [query]
     * @return: com.deepexi.util.pageHelper.PageBean<com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/6
     */
    PageBean<SaleOutTaskMiddleResponseDTO> listSaleOutTasksPage(SaleOutTaskMiddleRequestQuery query) throws Exception;

    /**
     * 根据ID删除销售出库单（出库任务单）
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * @Description: 新增销售出库单（出库任务单）.
     * @Param: [record]
     * @return: com.deepexi.dd.middle.order.domain.dto.SaleOutTaskResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/3
     */
    SaleOutTaskMiddleResponseDTO insert(SaleOutTaskMiddleRequestDTO saleOutTaskMiddleRequestDTO) throws Exception;

    /**
     * @Description: 根据出库单id查销售出库单（出库任务单）详情.
     * @Param: [id]
     * @return: com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/7
     */
    SaleOutTaskMiddleResponseDTO searchSaleOutTaskInfoBySaleOutTaskId(Long id) throws Exception;

    /**
     * @param id
     * @Description: 通过订单ID查销售出库单（出库任务单）详情.
     * @Param: [id, signStatus]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/11
     */
    List<SaleOutTaskMiddleResponseDTO> searchSaleOutTaskInfoBySaleOrderId(Long id, Integer signStatus) throws Exception;
    /**
     * @Description:  根据提货单号查销售出库单详情.
     * @Param: [id, signStatus]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/19
     */
    List<SaleOutTaskMiddleResponseDTO> searchSaleOutTaskInfoByPickGoodsId(Long id, Integer signStatus) throws Exception;

    /**
     * @param id
     * @Description: 根据主键id查询出库单.
     * @Param: [id]
     * @return: com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/20
     */
    SaleOutTaskMiddleResponseDTO selectById(Long id) throws Exception;

    /**
     * @Description: 根据ID修改销售出库单（出库任务单）.
     * @Param: [record]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/9
     */
    Boolean updateById(SaleOutTaskMiddleRequestDTO record) throws Exception;

    /**
     * @Description: 出库单红冲.
     * @Param: [id]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/8
     */
    Boolean hedgeOrder(Long id) throws Exception;

    /**
     * @Description: 出库单签收.
     * @Param: [saleOutTaskMiddleRequestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/13
     */
    Boolean sign(SaleOutTaskMiddleRequestDTO saleOutTaskMiddleRequestDTO) throws Exception;

    /**
     * @Description: 自动签收.
     * @Param: [day]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/21
     */
    Boolean autoSign(Integer day) throws Exception;

    /**
     * 根据订单ID查询列表
     *
     * @param query
     * @return
     */
    List<SaleOutTaskMiddleResponseDTO> findSaleOutTaskByIds(SaleOutTaskMiddleRequestQuery query);
}