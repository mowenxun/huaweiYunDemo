package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SaleOrderInfoApi;
import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.query.SaleOrderInfoQuery;
import com.deepexi.dd.middle.order.domain.query.SaleOrderInfoRequestQuery;
import com.deepexi.dd.middle.order.domain.query.SaleOrderInfoResQuery;
import com.deepexi.dd.middle.order.service.SaleOrderInfoService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * SaleOrderInfoApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SaleOrderInfoApiImpl implements SaleOrderInfoApi {

    @Autowired
    private SaleOrderInfoService saleOrderInfoService;

    @Override
    public Payload<List<SaleOrderInfoResponseDTO>> findList(@RequestBody SaleOrderInfoResQuery query) {
        return new Payload<>(saleOrderInfoService.findList(query));
    }

    @Override
    @ApiOperation("查询销售订单表列表")
    public Payload<List<SaleOrderInfoResponseDTO>> listSaleOrderInfos(SaleOrderInfoRequestQuery query) {
        List<SaleOrderInfoResponseDTO> list = ObjectCloneUtils
                .convertList(saleOrderInfoService
                                     .listSaleOrderInfos(query.clone(SaleOrderInfoQuery.class, CloneDirection.OPPOSITE)),
                             SaleOrderInfoResponseDTO.class, CloneDirection.OPPOSITE);
        return new Payload<>(list);
    }

    @Override
    @ApiOperation("查询销售订单表列表")
    public Payload<List<SaleOrderInfoResponseDTO>> listSaleOrderInfosByIds(@RequestBody List<Long> ids) {
        List<SaleOrderInfoResponseDTO> list = ObjectCloneUtils
                .convertList(saleOrderInfoService
                                     .listSaleOrderInfosByIds(ids),
                             SaleOrderInfoResponseDTO.class, CloneDirection.OPPOSITE);
        return new Payload<>(list);
    }

    @Override
    @ApiOperation("分页查询销售订单表列表")
    public Payload<PageBean<SaleOrderInfoResponseDTO>> listSaleOrderInfosPage(@Valid SaleOrderInfoRequestQuery query) {
        PageBean<SaleOrderInfoResponseDTO> page = saleOrderInfoService
                .listSaleOrderInfosPage(query.clone(SaleOrderInfoQuery.class));
        return new Payload<>(page);
    }

    @Override
    @ApiOperation("分页查询销售订单表列表 （只返回订单主表和明细）")
    public Payload<PageBean<SaleOrderInfoOLResponseDTO>> listSaleOrdersPage(@Valid SaleOrderInfoRequestQuery query) {
        PageBean<SaleOrderInfoOLResponseDTO> page = saleOrderInfoService
                .listSaleOrdersPage(query.clone(SaleOrderInfoQuery.class));
        return new Payload<>(page);
    }

    @Override
    @ApiOperation("批量删除销售订单表")
    public Payload<Boolean> deleteByIdIn(@RequestBody List<Long> id) {
        Boolean result = saleOrderInfoService.deleteByIdIn(id);
        return new Payload<>(result);
    }

    @Override
    @ApiOperation("新增销售订单表")
    public Payload<SaleOrderInfoResponseDTO> insert(@RequestBody SaleOrderInfoAddRequestDTO record) {
        SaleOrderInfoResponseDTO result = saleOrderInfoService.createOrder(record)
                                                              .clone(SaleOrderInfoResponseDTO.class, CloneDirection.OPPOSITE);
        return new Payload<SaleOrderInfoResponseDTO>(result);
    }

    @Override
    @ApiOperation("新增销售订单批量")
    public Payload<List<SaleOrderInfoResponseDTO>> inserts(List<SaleOrderInfoAddRequestDTO> record) {
        return null;
    }

    @Override
    @ApiOperation("根据ID查询销售订单表")
    public Payload<SaleOrderInfoResponseDTO> selectById(@PathVariable Long id) {
//        SaleOrderInfoResponseDTO resultObj=null;
        SaleOrderInfoResponseDTO result = saleOrderInfoService.selectById(id);
        if (result == null) {
            return new Payload<>();
        }/*else {
            resultObj= result.clone(SaleOrderInfoResponseDTO.class, CloneDirection.OPPOSITE);
        }*/
        return new Payload<>(result);
    }

    @Override
    @ApiOperation("根据ID查询销售订单表（只返回订单表信息）")
    public Payload<SaleOrderResponseDTO> selectSaleOrder(@PathVariable Long id) {
//        SaleOrderInfoResponseDTO resultObj=null;
        SaleOrderResponseDTO result = saleOrderInfoService.selectSaleOrder(id);
        if (result == null) {
            return new Payload<>();
        }
        return new Payload<>(result);
    }

    @Override
    @ApiOperation("根据ID更新销售订单表")
    public Payload<Boolean> updateById(@PathVariable Long id, @RequestBody SaleOrderInfoAddRequestDTO record) {
        Boolean result = saleOrderInfoService.updateById(id, record);
        return new Payload<>(result);
    }

    @Override
    public Payload<SaleOrderResponseDTO> selectSaleOrder(@PathVariable String code) {
        SaleOrderResponseDTO responseDTO = saleOrderInfoService.getSaleOrderByCode(code);
        return new Payload<>(responseDTO);
    }

    @Override
    public Payload<List<SaleOrderResponseDTO>> selectSaleOrderByParentCode(@PathVariable String code) {
        List<SaleOrderResponseDTO> responseDTO = saleOrderInfoService.getSaleOrderByParentCode(code);
        return new Payload<>(responseDTO);
    }

    @Override
    @ApiOperation("根据ID更新销售订单表的支付金额")
    public Payload<Boolean> updatePayAmountById(@RequestBody SaleOrderInfoAddRequestDTO record) {
        Boolean result = saleOrderInfoService.updatePayAmountById(record);
        return new Payload<>(result);
    }

    @Override
    @ApiOperation("更新订单状态")
    public Payload<Boolean> updateOrderStatus(@RequestBody SaleOrderInfoStatusEditRequestDTO saleOrderInfoStatusEditRequestDTO) {
        return new Payload<>(saleOrderInfoService.updateOrderStatus(saleOrderInfoStatusEditRequestDTO));
    }

    /**
     * @Description: 订单发货.
     * @Param: [requestDTO]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/7/15
     */
    @Override
    public Payload<Boolean> deliveryGoodsOrder(@RequestBody SaleOrderInfoDeliverGoodsMiddleRequestDTO requestDTO) throws Exception {
        return new Payload<>(saleOrderInfoService.deliveryGoodsOrder(requestDTO));
    }

    @Override
    public Payload<Boolean> updateOrderAmount(@RequestBody SaleOrderInfoAmountEditDTO saleOrderInfoAmountEditDTO) {
        return new Payload<>(saleOrderInfoService.updateOrderAmount(saleOrderInfoAmountEditDTO));
    }

    /**
     * 更新支付记录订单编号
     *
     * @param saleOrderInfoPayOrderCodeEditDTO
     * @return
     */
    @Override
    public Payload<Boolean> updatePayOrderCode(@RequestBody SaleOrderInfoPayOrderCodeEditDTO saleOrderInfoPayOrderCodeEditDTO) {
        return new Payload<>(saleOrderInfoService.updatePayOrderCode(saleOrderInfoPayOrderCodeEditDTO));
    }

    /**
     * @param saleOrderCodeList
     * @Description: 根据批量的订单编号查订单信息.
     * @Param: [saleOrderIdList]
     * @return: com.deepexi.util.config.Payload<java.util.List               <               com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO>>
     * @Author: SongTao
     * @Date: 2020/8/20
     */
    @Override
    public Payload<List<SaleOrderInfoResponseDTO>> batchSearchSaleOrderInfo(@RequestBody List<String> saleOrderCodeList) {
        return new Payload<>(saleOrderInfoService.batchSearchSaleOrderInfo(saleOrderCodeList));
    }

    /**
     * @Description: 根据批量订单id修改.
     * @Param: [list]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/8/20
     */
    @Override
    public Payload<Boolean> updateBatchById(@RequestBody List<SaleOrderInfoRequestDTO> list) throws Exception {
        return new Payload<>(saleOrderInfoService.updateBatchById(list));
    }

    @Override
    public Payload<Boolean> updateMainOrderById(@PathVariable Long id, @RequestBody SaleOrderInfoRequestDTO record) {
        return new Payload<>(saleOrderInfoService.updateById(id, record));
    }

    @Override
    public Payload<SaleOrderResponseDTO> selectSaleOrder(@RequestBody SaleOrderInfoRequestQuery query) {
        SaleOrderInfoDTO info = saleOrderInfoService.selectSaleOrder(query.getId(), query.getCode());
        if (info != null) {
            return new Payload<>(info.clone(SaleOrderResponseDTO.class));
        }
        return new Payload<>(new SaleOrderResponseDTO(), "60001", "订单不存在");
    }

    /**
     * @Description:  根据id和code查询订单信息.
     * @Param: [saleOrderInfoRequestQuery]
     * @return: com.deepexi.util.config.Payload<com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/29
     */
    @Override
    public Payload<SaleOrderInfoResponseDTO> searchSaleOrderInfoByQuery(@RequestBody SaleOrderInfoRequestQuery saleOrderInfoRequestQuery) throws Exception {
        SaleOrderInfoResponseDTO saleOrderInfoResponseDTO = saleOrderInfoService.searchSaleOrderInfoByQuery(saleOrderInfoRequestQuery.clone(SaleOrderInfoQuery.class));
        return new Payload<>(saleOrderInfoResponseDTO);
    }

    @Override
    public Payload<Boolean> closePreMonthPlanOrder() {
        return new Payload<>(saleOrderInfoService.closePreMonthPlanOrder());
    }
}
