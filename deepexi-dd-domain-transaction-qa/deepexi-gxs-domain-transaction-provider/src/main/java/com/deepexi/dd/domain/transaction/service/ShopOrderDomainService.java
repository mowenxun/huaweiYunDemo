package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.domain.transaction.domain.dto.gxs.order.ShopOrderAddDTO;
import com.deepexi.dd.domain.transaction.domain.dto.gxs.order.ShopOrderDetailVO;
import com.deepexi.dd.domain.transaction.domain.query.ShopOrderDomainRequestQuery;
import com.deepexi.dd.domain.transaction.domain.responseDto.ShopOrderDomainResponseDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformDistributionDomainRequestDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformDistributionViewDomainResponseDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformShopOrderDomainResponseDTO;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/13/17:12
 * @Description:
 */
public interface ShopOrderDomainService {

    PageBean<ShopOrderDomainResponseDTO> listShopOrdersPage(ShopOrderDomainRequestQuery query) throws Exception;

    List<ShopOrderAddDTO> addShopOrder(List<ShopOrderAddDTO> shopOrderAddDTOS) throws Exception;

    Boolean cancelShopOrder(Long id) throws Exception;

    /**
     * 根据id获得详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    ShopOrderDetailVO getShopOrderDetailById(Long id) throws Exception;


    /**
     * 平台端门店订单列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    PageBean<PlatformShopOrderDomainResponseDTO> listPlatformShopOrdersPage(ShopOrderDomainRequestQuery query) throws Exception;


    /**
     * @param ids 店铺订单id结婚
     * @return 店铺订单分发预览视图
     * @throws Exception
     */
    List<PlatformDistributionViewDomainResponseDTO> distributeView(List<Long> ids) throws Exception;


    /**
     * 确认分发
     *
     * @param list
     * @return
     * @throws Exception
     */
    Boolean distributeOrder(List<PlatformDistributionDomainRequestDTO> list) throws Exception;

    /**
     * @param supperId 已分发订单id
     * @return
     * @throws Exception
     */
    Boolean updateSign(Long supperId) throws Exception;

    /**
     * 支付成功后处理订单
     *
     * @param ids
     * @return
     * @throws Exception
     */
    Boolean paySuccessUpdateStatus(List<Long> ids) throws Exception;


    /**
     * 签收订单
     *
     * @param id
     * @return
     * @throws Exception
     */
    Boolean signById(Long id) throws Exception;


    /**
     * 已分发订单重新分发视图接口
     *
     * @param
     * @return 店铺订单分发预览视图
     * @throws Exception
     */
    List<PlatformDistributionViewDomainResponseDTO> againDistributeView(Long id) throws Exception;


    /**
     * 重新分发订单（修改原订单的数据）
     *
     * @param platformDistributionDomainRequestDTO
     * @return
     * @throws Exception
     */
    Boolean againDistributeOrder(PlatformDistributionDomainRequestDTO platformDistributionDomainRequestDTO) throws Exception;

    boolean rejectOrder(Long id) throws Exception;

    boolean receiveOrder(Long id)throws Exception;

    boolean sendGoods(Long id)throws Exception;
}
