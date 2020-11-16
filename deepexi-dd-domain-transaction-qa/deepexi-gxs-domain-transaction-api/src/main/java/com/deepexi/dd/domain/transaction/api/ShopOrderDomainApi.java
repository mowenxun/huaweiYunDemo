package com.deepexi.dd.domain.transaction.api;

import com.deepexi.dd.domain.transaction.domain.dto.gxs.order.ShopOrderAddDTO;
import com.deepexi.dd.domain.transaction.domain.dto.gxs.order.ShopOrderDetailVO;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoRequestQuery;
import com.deepexi.dd.domain.transaction.domain.query.ShopOrderDomainRequestQuery;
import com.deepexi.dd.domain.transaction.domain.responseDto.ShopOrderDomainResponseDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformDistributionDomainRequestDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformDistributionViewDomainResponseDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformShopOrderDomainResponseDTO;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/13/17:49
 * @Description:
 */
@Api(value = "销售订单模块rpc接口")
@RequestMapping("/open-api/v1/domain/shop/order")
public interface ShopOrderDomainApi {

    @GetMapping("/page")
    PageBean<ShopOrderDomainResponseDTO> listShopOrdersPage(ShopOrderDomainRequestQuery query) throws Exception;

    @PostMapping("")
    List<ShopOrderAddDTO> addShopOrder(@RequestBody List<ShopOrderAddDTO> shopOrderAddDTOS) throws Exception;

    @GetMapping("/cancel/{id}")
    boolean cancelShopOrderById(@PathVariable Long id) throws Exception;

    /**
     * 根据id获得详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/{id}")
    ShopOrderDetailVO getShopOrderDetailById(@PathVariable Long id) throws Exception;


    /**
     * 平台端门店订单列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @GetMapping("/plat/page")
    PageBean<PlatformShopOrderDomainResponseDTO> listPlatformShopOrdersPage(ShopOrderDomainRequestQuery query) throws Exception;


    /**
     * 根据店铺id获得分发预览的视图
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @PostMapping("/distributeView")
    List<PlatformDistributionViewDomainResponseDTO> distributeView(@RequestBody List<Long> ids) throws Exception;

   /* @GetMapping("/plat/export")
    void exportShopOrderToExcel(ShopOrderDomainRequestQuery query) throws Exception;*/


    /**
     * 确认分发
     *
     * @param list
     * @return
     * @throws Exception
     */
    @PostMapping("/distributeOrder")
    Boolean distributeOrder(@RequestBody List<PlatformDistributionDomainRequestDTO> list) throws Exception;


    @PostMapping("/paySuccessUpdateStatus")
    Boolean paySuccessUpdateStatus(@RequestBody List<Long> ids) throws Exception;


    /**
     * 根据id获得详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/sign/{id}")
    Boolean signById(@PathVariable Long id) throws Exception;


    @GetMapping("/againDistributeView/{id}")
    List<PlatformDistributionViewDomainResponseDTO> againDistributeView(@PathVariable Long id) throws Exception;


    /**
     * 确认重新分发（修改原订单）
     *
     * @param platformDistributionDomainRequestDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/againDistributeOrder")
    Boolean againDistributeOrder(@RequestBody PlatformDistributionDomainRequestDTO platformDistributionDomainRequestDTO) throws Exception;


}
