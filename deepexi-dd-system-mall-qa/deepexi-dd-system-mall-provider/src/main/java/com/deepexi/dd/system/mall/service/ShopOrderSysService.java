package com.deepexi.dd.system.mall.service;

import com.deepexi.dd.domain.transaction.domain.dto.gxs.order.ShopOrderAddDTO;
import com.deepexi.dd.domain.transaction.domain.dto.gxs.order.ShopOrderDetailVO;
import com.deepexi.dd.domain.transaction.domain.query.ShopOrderDomainRequestQuery;
import com.deepexi.dd.domain.transaction.domain.responseDto.ShopOrderDomainResponseDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformDistributionDomainRequestDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformDistributionViewDomainResponseDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformShopOrderDomainResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.gxs.order.PayBackVO;
import com.deepexi.dd.system.mall.domain.dto.gxs.order.ShopOrderSysAddDTO;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/13/19:13
 * @Description:
 */
public interface ShopOrderSysService {
    PageBean<ShopOrderDomainResponseDTO> listShopOrdersPage(ShopOrderDomainRequestQuery query) throws Exception;

    List<ShopOrderAddDTO> addShopOrder(List<ShopOrderSysAddDTO> shopOrderSysAddDTOS) throws Exception;

    boolean cancelShopOrderById(@PathVariable Long id) throws Exception;

    /**
     * 根据id获得详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    ShopOrderDetailVO getShopOrderDetailById(Long id) throws Exception;

    /**
     * 平台端店铺订单列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    PageBean<PlatformShopOrderDomainResponseDTO> listPlatformShopOrdersPage(ShopOrderDomainRequestQuery query) throws Exception;

    /**
     * 导出excel
     *
     * @param query
     * @throws Exception
     */
   /* void exportShopOrderToExcel(HttpServletResponse response, ShopOrderDomainRequestQuery query) throws Exception;*/

    /**
     *
     * @param ids 店铺订单id结婚
     * @return 店铺订单分发预览视图
     * @throws Exception
     */
    List<PlatformDistributionViewDomainResponseDTO> distributeView(List<Long> ids)throws  Exception;


    /**
     * 确认分发
     * @param list
     * @return
     * @throws Exception
     */
    Boolean distributeOrder(List<PlatformDistributionDomainRequestDTO> list) throws Exception;

    /**
     * 支付成功后修改订单状态以及已支付金额、支付方式
     * @return
     * @throws Exception
     */
    PayBackVO paySuccessUpdateStatus(List<Long> ids)throws Exception;

    /**
     * 确定本人签收
     * @param id
     * @return
     * @throws Exception
     */
    Boolean  signById(Long id)throws Exception;

    /**已分发订单重新分发视图接口
     * @param
     * @return 店铺订单分发预览视图
     * @throws Exception
     */
    List<PlatformDistributionViewDomainResponseDTO> againDistributeView(Long id) throws Exception;



    /**
     * 重新分发确认接口
     * @param platformDistributionDomainRequestDTO
     * @return
     * @throws Exception
     */
    Boolean againDistributeOrder(PlatformDistributionDomainRequestDTO platformDistributionDomainRequestDTO) throws Exception;
}
