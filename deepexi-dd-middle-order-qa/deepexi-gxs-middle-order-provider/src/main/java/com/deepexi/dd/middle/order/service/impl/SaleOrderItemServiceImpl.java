package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.SaleOrderItemDAO;
import com.deepexi.dd.middle.order.domain.SaleOrderItemDO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderItemDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderItemMiddleResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskDetailInfoMiddleResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemMiddleRequestQuery;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemQuery;
import com.deepexi.dd.middle.order.service.SaleOrderItemService;
import com.deepexi.dd.middle.order.service.SaleOutTaskDetailInfoService;
import com.deepexi.dd.middle.order.util.DomainUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * SaleOrderItemServiceImpl
 *
 * @author admin
 * @date Tue Jun 23 19:44:58 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class SaleOrderItemServiceImpl implements SaleOrderItemService {

    @Autowired
    private SaleOrderItemDAO saleOrderItemDao;

    @Autowired
    private SaleOutTaskDetailInfoService saleOutTaskDetailInfoService;

    /**
     * @Description:  查询销售订单明细表列表.
     * @Param: [query]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SaleOrderItemDTO>
     * @Author: SongTao
     * @Date: 2020/7/7
     */
    @Override
    public List<SaleOrderItemDTO> listSaleOrderItems(SaleOrderItemQuery query) {
        List<SaleOrderItemDO> saleOrderItemDOS = saleOrderItemDao.listSaleOrderItems(query.clone(SaleOrderItemMiddleRequestQuery.class));
        return ObjectCloneUtils.convertList(saleOrderItemDOS,SaleOrderItemDTO.class);
    }

    /**
     * @Description:  查询销售订单明细表分页列表.
     * @Param: [query]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SaleOrderItemDTO>
     * @Author: SongTao
     * @Date: 2020/7/7
     */
    @Override
    public PageBean<SaleOrderItemMiddleResponseDTO> listSaleOrderItemsPage(SaleOrderItemMiddleRequestQuery query) throws Exception {
        PageHelper.startPage(query.getPage(), query.getSize());
        List<SaleOutTaskDetailInfoMiddleResponseDTO> responseDTOList = Lists.newArrayList();//如通过出库单查 这List用于记录商品明细 否则则无用
        if (Objects.nonNull(query.getSaleOutTaskId())){//如果根据出库单查 则得处理
            responseDTOList= saleOutTaskDetailInfoService.searchSaleOutTaskDetailInfo("sale_out_task_id", query.getSaleOutTaskId());
            List<Long> idList = responseDTOList.stream().map(SaleOutTaskDetailInfoMiddleResponseDTO::getSaleOrderItemId).collect(Collectors.toList());//根据出库单拿到批量的商品明细ID
            query.setIdList(idList);
        }
        PageBean<SaleOrderItemMiddleResponseDTO> pageBean = ObjectCloneUtils.convertPageBean(new PageBean<>(saleOrderItemDao.listSaleOrderItems(query)), SaleOrderItemMiddleResponseDTO.class);
        if (Objects.nonNull(query.getSaleOutTaskId())){//如果根据出库单查 则要带出每件商品对应的出库数量
            Map<Long, SaleOutTaskDetailInfoMiddleResponseDTO> skuMap = responseDTOList.stream().collect(Collectors.
                    toMap(SaleOutTaskDetailInfoMiddleResponseDTO::getSaleOrderItemId, Function.identity(),(k1,k2)->k1));
            pageBean.getContent().forEach(f->{
                SaleOutTaskDetailInfoMiddleResponseDTO taskDetail = skuMap.get(f.getId());
                f.setSkuShipmentQuantity(taskDetail.getSkuShipmentQuantity());//商品的本次出库数量
                f.setSkuPickQuantity(taskDetail.getSkuPickQuantity());
            });
        }
        return pageBean;
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除销售订单明细表,传入ID为空");
            return false;
        }
        return saleOrderItemDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public SaleOrderItemDTO insert(SaleOrderItemDTO record) {
        if (record == null) {
            log.error("新增销售订单明细表,传入参数为空");
            return null;
        }
        SaleOrderItemDO saleOrderItemDo = record.clone(SaleOrderItemDO.class);
        DomainUtils.initDomainCreatedInfo(saleOrderItemDo);
        if (saleOrderItemDao.insert(saleOrderItemDo) > 0) {
            record.setId(saleOrderItemDo.getId());
            return record;
        }
        log.error("新增销售订单明细表失败");
        return null;
    }

    @Override
    public SaleOrderItemDTO selectById(Long id) {
        if (id == null) {
            log.error("查询销售订单明细表,传入ID为空");
            return null;
        }
        SaleOrderItemDO saleOrderItemDo = saleOrderItemDao.selectById(id);
        if (saleOrderItemDo == null) {
            return null;
        }
        return saleOrderItemDo.clone(SaleOrderItemDTO.class);
    }

    @Override
    public Boolean updateById(Long id, SaleOrderItemDTO record) {
        if (id == null) {
            log.error("修改销售订单明细表,传入ID为空");
            return false;
        }
        record.setId(id);
        SaleOrderItemDO saleOrderItemDo = record.clone(SaleOrderItemDO.class);
        DomainUtils.initUpdateTimeInfo(saleOrderItemDo);
        return saleOrderItemDao.updateById(saleOrderItemDo);
    }

    @Override
    public Boolean updateBatchById(List<SaleOrderItemDTO> records) {
        if (records == null) {
            log.error("批量修改销售订单明细表,传入records为null");
            return false;
        }
        List<SaleOrderItemDO> itemDOs = records.stream().map(item -> {
            SaleOrderItemDO itemDO = item.clone(SaleOrderItemDO.class);
            DomainUtils.initUpdateTimeInfo(itemDO);
            return itemDO;
        }).collect(Collectors.toList());
        saleOrderItemDao.updateBatchById(itemDOs);
        return true;
    }

}