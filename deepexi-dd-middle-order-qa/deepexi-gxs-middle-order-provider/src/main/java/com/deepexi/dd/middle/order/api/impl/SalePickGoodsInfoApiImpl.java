package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SalePickGoodsInfoApi;
import com.deepexi.dd.middle.order.domain.SalePickGoodsInfoDTO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsInfoQuery;
import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsInfoRequestQuery;
import com.deepexi.dd.middle.order.service.SalePickGoodsInfoService;
import com.deepexi.dd.middle.order.service.SalePickGoodsOrderService;
import com.deepexi.dd.middle.order.service.SalePickGoodsOrderSkuService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


/**
 * SalePickGoodsInfoApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SalePickGoodsInfoApiImpl implements SalePickGoodsInfoApi {

    @Autowired
    private SalePickGoodsInfoService salePickGoodsInfoService;

    @Autowired
    private SalePickGoodsOrderService salePickGoodsOrderService;

    @Autowired
    private SalePickGoodsOrderSkuService salePickGoodsOrderSkuService;

    @Override
    @ApiOperation("查询提货单据表列表")
    public List<SalePickGoodsInfoResponseDTO> listSalePickGoodsInfos(SalePickGoodsInfoRequestQuery query) {
        return ObjectCloneUtils
                .convertList(salePickGoodsInfoService
                                .listSalePickGoodsInfos(query.clone(SalePickGoodsInfoQuery.class,
                                        CloneDirection.OPPOSITE)),
                        SalePickGoodsInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询提货单据表列表")
    public PageBean<SalePickGoodsInfoResponseDTO> listSalePickGoodsInfosPage(@RequestBody SalePickGoodsInfoRequestQuery query) {
        PageBean<SalePickGoodsInfoDTO> salePickGoodsInfoDTOPageBean = salePickGoodsInfoService.listSalePickGoodsInfosPage(query.clone(SalePickGoodsInfoQuery.class, CloneDirection.OPPOSITE));
        PageBean<SalePickGoodsInfoResponseDTO> salePickGoodsInfoResponseDTOPageBean = ObjectCloneUtils.convertPageBean(salePickGoodsInfoDTOPageBean, SalePickGoodsInfoResponseDTO.class,CloneDirection.OPPOSITE);
        return salePickGoodsInfoResponseDTOPageBean;
    }

    @Override
    @ApiOperation("批量删除提货单据表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return salePickGoodsInfoService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增提货单据表")
    public SalePickGoodsInfoResponseDTO insert(@RequestBody SalePickGoodsInfoRequestDTO record) {
        return salePickGoodsInfoService.insert(record.clone(SalePickGoodsInfoDTO.class, CloneDirection.OPPOSITE))
                .clone(SalePickGoodsInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询提货单据表")
    public SalePickGoodsInfoResponseDTO selectById(@PathVariable Long id) {
        SalePickGoodsInfoDTO result = salePickGoodsInfoService.selectById(id);
        SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO=null;
        if (result != null) {
             salePickGoodsInfoResponseDTO = result.clone(SalePickGoodsInfoResponseDTO.class
                    , CloneDirection.OPPOSITE);
            salePickGoodsInfoResponseDTO.setSalePickGoodsConsigneeResponseDTOS(ObjectCloneUtils.convertList(result.getSalePickGoodsConsigneeDTOS(), SalePickGoodsConsigneeResponseDTO.class));
        }
        return salePickGoodsInfoResponseDTO;
    }

    @Override
    public Payload<SalePickGoodsInfoResponseDTO> getSalePickGoodsInfoByCode(@PathVariable String code) {
        return new Payload<>(salePickGoodsInfoService.getSalePickGoodsInfoByCode(code));
    }

    @Override
    @ApiOperation("根据ID更新提货单据表")
    public Boolean updateById(@PathVariable Long id, @RequestBody SalePickGoodsInfoRequestDTO record) {
        return salePickGoodsInfoService.updateById(id, record.clone(SalePickGoodsInfoDTO.class,
                CloneDirection.OPPOSITE));
    }


    @Override
    @ApiOperation("新增提货单据表、订单明细、sku明细、以及其他数据库操作")
    @Transactional
    public SalePickGoodsInfoResponseDTO savePickGoods(@RequestBody SalePickGoodsInfoRequestDTO record) {
        SalePickGoodsInfoTransferDTO dateDTO = new SalePickGoodsInfoTransferDTO();
        dateDTO.setRecordObj(record);
        dateDTO.setType("save");
        SalePickGoodsInfoResponseDTO responseDTO = salePickGoodsInfoService.saveOrEdit(dateDTO);
        return responseDTO;
    }


    /**
     * @Description: 审批通过.
     * @Param: [dto]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/8/14
     */
    @Override
    public Payload<Boolean> examinePass(@RequestBody SalePickGoodsInfoRequestDTO dto) throws Exception {
        return new Payload<>(salePickGoodsInfoService.examinePass(dto));
    }

    /**
     * @param dto
     * @Description: 审批驳回.
     * @Param: [dto]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/8/30
     */
    @Override
    public Payload<Boolean> examineReject(@RequestBody SalePickGoodsInfoRequestDTO dto) throws Exception {
        return new Payload<>(salePickGoodsInfoService.examineReject(dto));
    }

    /**
     * @Description: 提货计划的发货.
     * @Param: [dto]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/8/15
     */
    @Override
    public Payload<Boolean> pickPlanDeliveryGoods(@RequestBody @Valid SalePickDeliveryInfoMiddleRequestDTO dto) throws Exception {
        return new Payload<>(salePickGoodsInfoService.pickPlanDeliveryGoods(dto));
    }


    /**
     * 编辑提货计划
     *
     * @param dateDTO
     * @return
     */
    @Override
    public SalePickGoodsInfoResponseDTO editPickGoods(@RequestBody SalePickGoodsInfoTransferDTO dateDTO) {
        SalePickGoodsInfoResponseDTO responseDTO = salePickGoodsInfoService.saveOrEdit(dateDTO);
        return responseDTO;
    }


    /**
     * 取消提货计划
     *
     * @param dateDTO
     * @return
     */
    @Override
    public SalePickGoodsInfoResponseDTO doCancelPickGoods(@RequestBody SalePickGoodsInfoTransferDTO dateDTO) {
        SalePickGoodsInfoResponseDTO responseDTO = salePickGoodsInfoService.doCancelPickGoods(dateDTO);
        return responseDTO;
    }

    @Override
    public Payload<Boolean> updateOrderStatus(SaleOrderInfoStatusEditRequestDTO saleOrderInfoStatusEditRequestDTO) {
        return new Payload<>(salePickGoodsInfoService.updateOrderStatus(saleOrderInfoStatusEditRequestDTO));
    }

    @Override
    public Payload<Boolean> updateOrderAmount(@RequestBody SalePickGoodsInfoAmountEditDTO salePickGoodsInfoAmountEditDTO) {
        return new Payload<>(salePickGoodsInfoService.updateOrderAmount(salePickGoodsInfoAmountEditDTO));
    }

    @Override
    public Payload<PageBean<SalePickGoodsOrderSkuResponseDTO>> searchPickGoodsList(@PathVariable Long saleOrderId,
                                                                                   @RequestParam("page") Integer page
            , @RequestParam("size") Integer size) {
        return new Payload<>(salePickGoodsInfoService.searchPickGoodsList(saleOrderId, page, size));
    }

    /**
     * @Description: 提货单的出库单签收.
     * @Param: [saleOutTaskMiddleRequestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/18
     */
    @Override
    public Payload<Boolean> sign(@RequestBody SaleOutTaskMiddleRequestDTO saleOutTaskMiddleRequestDTO) throws Exception {
        return new Payload<>(salePickGoodsInfoService.sign(saleOutTaskMiddleRequestDTO));
    }

    /**
     * @Description: 根据提货单ID查询商品sku列表.
     * @Param: [id]
     * @return: com.deepexi.util.config.Payload<java.util.List < com.deepexi.dd.middle.order.domain.dto.SalePickGoodsDetailInfoMiddleResponseDTO>>
     * @Author: SongTao
     * @Date: 2020/8/21
     */
    @Override
    public Payload<List<SalePickGoodsDetailInfoMiddleResponseDTO>> searchSkuDetailList(@PathVariable Long id) throws Exception {
        return new Payload<>(salePickGoodsInfoService.searchSkuDetailList(id));
    }

    /**
     *
     * @param query
     * @return
     */
    @Override
    public SalePickGoodsInfoResponseDTO selectByPickCode(SalePickGoodsInfoRequestQuery query) {
        return salePickGoodsInfoService.selectByPickCode(query);
    }
}
