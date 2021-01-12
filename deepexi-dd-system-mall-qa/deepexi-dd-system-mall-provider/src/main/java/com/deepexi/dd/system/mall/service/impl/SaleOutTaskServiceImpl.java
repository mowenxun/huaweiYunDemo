package com.deepexi.dd.system.mall.service.impl;

import com.deepexi.dd.domain.transaction.domain.dto.SaleOrderInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskDetailResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskSignRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.SaleOutTaskDetailInfoResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.SaleOutTaskInfoResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.SaleOutTaskInfoSignRequestDTO;
import com.deepexi.dd.system.mall.domain.query.SaleOutTaskRequestQuery;
import com.deepexi.dd.system.mall.remote.order.SaleOrderInfoRemote;
import com.deepexi.dd.system.mall.remote.order.SaleOutTaskRemote;
import com.deepexi.dd.system.mall.service.SaleOutTaskService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SaleOutTaskServiceImpl
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-18
 * @Version 1.0
 **/
@Service
@Slf4j
public class SaleOutTaskServiceImpl implements SaleOutTaskService {

    @Autowired
    SaleOutTaskRemote saleOutTaskRemote;
    @Autowired
    SaleOrderInfoRemote saleOrderInfoRemote;

    /**
     * @param dto
     * @Description: 出库单签收.
     * @Param: [dto]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/18
     */
    @Override
    public Boolean sign(SaleOutTaskInfoSignRequestDTO dto) throws Exception {
        return saleOutTaskRemote.sign(dto.clone(SaleOutTaskSignRequestDTO.class));
    }

    /**
     * @param query
     * @Description: 分页查询销售出库单（出库任务单）列表.
     * @Param: [query]
     * @return: com.deepexi.util.pageHelper.PageBean<com.deepexi.dd.system.mall.domain.dto.SaleOutTaskInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/21
     */
    @Override
    public PageBean<SaleOutTaskInfoResponseDTO> searchSaleOutTaskPageList(SaleOutTaskRequestQuery query) throws Exception {
        PageBean<com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskInfoResponseDTO> saleOutTaskInfoResponseDTOPageBean = saleOutTaskRemote.
                searchSaleOutTaskPageList(query.clone(com.deepexi.dd.domain.transaction.domain.query.SaleOutTaskRequestQuery.class));
        return GeneralConvertUtils.convert2PageBean(saleOutTaskInfoResponseDTOPageBean, SaleOutTaskInfoResponseDTO.class);
    }

    /**
     * @param id
     * @Description: 根据出库单id查销售出库单（出库任务单）详情.
     * @Param: [id]
     * @return: com.deepexi.dd.system.mall.domain.dto.SaleOutTaskDetailInfoResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/21
     */
    @Override
    public SaleOutTaskDetailInfoResponseDTO searchSaleOutTaskInfoBySaleOutTaskId(Long id) throws Exception {
        SaleOutTaskDetailResponseDTO saleOutTaskDetailResponseDTO = saleOutTaskRemote.searchSaleOutTaskInfoBySaleOutTaskId(id);
        SaleOutTaskDetailInfoResponseDTO result = GeneralConvertUtils.conv(saleOutTaskDetailResponseDTO, SaleOutTaskDetailInfoResponseDTO.class);
        if (saleOutTaskDetailResponseDTO != null){
            //查询订单状态、供应商、客户名称
            Payload<SaleOrderInfoResponseDTO> saleOrderInfoResponseDTOPayload = saleOrderInfoRemote.selectById(saleOutTaskDetailResponseDTO.getSaleOrderId());
            if (saleOrderInfoResponseDTOPayload.getPayload() != null){
                SaleOrderInfoResponseDTO dto = GeneralConvertUtils.conv(saleOrderInfoResponseDTOPayload.getPayload(), SaleOrderInfoResponseDTO.class);
                result.setTicketType(dto.getTicketType());
                result.setBuyerName(dto.getBuyerName());
                result.setSellerName(dto.getSellerName());
            }
        }
        return result;
    }

    /**
     * @param id
     * @Description: 根据订单ID查销售出库单（出库任务单）详情.
     * @Param: [id, signStatus]
     * @return: java.util.List<com.deepexi.dd.system.mall.domain.dto.SaleOutTaskDetailInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/21
     */
    @Override
    public List<SaleOutTaskDetailInfoResponseDTO> searchSaleOutTaskInfoBySaleOrderId(Long id, Integer signStatus) throws Exception {
        List<SaleOutTaskDetailResponseDTO> list = saleOutTaskRemote.searchSaleOutTaskInfoBySaleOrderId(id, signStatus);
        return GeneralConvertUtils.convert2List(list, SaleOutTaskDetailInfoResponseDTO.class);
    }

    /**
     * @param id
     * @param signStatus
     * @Description: 根据提货单号查销售出库单详情.
     * @Param: [id, signStatus]
     * @return: java.util.List<com.deepexi.dd.system.mall.domain.dto.SaleOutTaskDetailInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/19
     */
    @Override
    public List<SaleOutTaskDetailInfoResponseDTO> searchSaleOutTaskInfoByPickGoodsId(Long id, Integer signStatus) throws Exception {
        List<SaleOutTaskDetailResponseDTO> list = saleOutTaskRemote.searchSaleOutTaskInfoByPickGoodsId(id, signStatus);
        return GeneralConvertUtils.convert2List(list, SaleOutTaskDetailInfoResponseDTO.class);
    }
}
