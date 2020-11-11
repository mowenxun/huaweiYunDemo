package com.deepexi.dd.middle.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.dao.SaleOmsLogisticsTrajectoryDAO;
import com.deepexi.dd.middle.order.dao.SaleOrderItemDAO;
import com.deepexi.dd.middle.order.domain.BaseDO;
import com.deepexi.dd.middle.order.domain.SaleOmsLogisticsTrajectoryDO;
import com.deepexi.dd.middle.order.domain.SaleOmsLogisticsTrajectoryDTO;
import com.deepexi.dd.middle.order.domain.SaleOrderItemDO;
import com.deepexi.dd.middle.order.domain.dto.SaleOmsLogisticsTrajectoryRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderItemMiddleResponseDTO;
import com.deepexi.dd.middle.order.service.SaleOmsLogisticsTrajectoryService;
import com.deepexi.dd.middle.order.util.DomainUtils;
import com.deepexi.dd.middle.order.util.Utils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * SaleOmsLogisticsTrajectoryServiceImpl
 *
 * @author admin
 * @date Tue Aug 25 16:23:34 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class SaleOmsLogisticsTrajectoryServiceImpl implements SaleOmsLogisticsTrajectoryService {

    @Autowired
    private SaleOmsLogisticsTrajectoryDAO saleOmsLogisticsTrajectoryDao;

    @Autowired
    private SaleOrderItemDAO saleOrderItemDao;

    @Override
    public List<SaleOmsLogisticsTrajectoryDTO> listSaleOmsLogisticsTrajectorys(com.deepexi.dd.middle.order.domain.SaleOmsLogisticsTrajectoryQuery query) {
        return ObjectCloneUtils.convertList(saleOmsLogisticsTrajectoryDao.listSaleOmsLogisticsTrajectorys(query), SaleOmsLogisticsTrajectoryDTO.class);
    }


    @Override
    public PageBean<SaleOmsLogisticsTrajectoryDTO> listSaleOmsLogisticsTrajectorysPage(com.deepexi.dd.middle.order.domain.SaleOmsLogisticsTrajectoryQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(saleOmsLogisticsTrajectoryDao.listSaleOmsLogisticsTrajectorys(query)), SaleOmsLogisticsTrajectoryDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除OMS物流轨迹,传入ID为空");
            return false;
        }
        return saleOmsLogisticsTrajectoryDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public Boolean insert(SaleOmsLogisticsTrajectoryRequestDTO record) throws Exception {
        Optional.ofNullable(record).orElseThrow(()->new ApplicationException("新增OMS物流轨迹,传入参数为空"));
        //通过行号拿到对应的sku及订单
        QueryWrapper<SaleOrderItemDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SaleOrderItemDO::getRowCode, record.getSubExternalOrderCode()).eq(BaseDO::getDeleted , 0);
        List<SaleOrderItemDO> orderItemDOList = saleOrderItemDao.list(queryWrapper);
        List<SaleOrderItemMiddleResponseDTO> saleOrderItemMiddleResponseDTOList = ObjectCloneUtils.convertList(orderItemDOList, SaleOrderItemMiddleResponseDTO.class);
        SaleOrderItemMiddleResponseDTO saleOrderItemMiddleResponseDTO = Utils.selectOne(saleOrderItemMiddleResponseDTOList);
        //生成物流轨迹信息
        List<SaleOmsLogisticsTrajectoryDTO> list = record.getTrackMessage().stream().map(map -> {
            SaleOmsLogisticsTrajectoryDTO requestDTO = new SaleOmsLogisticsTrajectoryDTO();
            requestDTO.setSubExternalOrderCode(record.getSubExternalOrderCode());//行号
            requestDTO.setSaleOrderId(saleOrderItemMiddleResponseDTO.getSaleOrderId());//订单ID
            requestDTO.setSkuItemId(saleOrderItemMiddleResponseDTO.getId());//商品主键ID
            requestDTO.setTenantId(saleOrderItemMiddleResponseDTO.getTenantId());//企业ID
            requestDTO.setAppId(saleOrderItemMiddleResponseDTO.getAppId());//appId
            requestDTO.setTime(map.getTime());//物流时间
            requestDTO.setMessage(map.getMessage());//轨迹
            requestDTO.setWhetherIncrement(record.getWhetherIncrement());//是否增量 true:增量推送;false:全量推送
            requestDTO.setCreatedBy("OMS");
            initDomainCreatedInfo(requestDTO);
            return requestDTO;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        if (!record.getWhetherIncrement()){//全量推送 则要根据行号批量覆盖
            QueryWrapper<SaleOmsLogisticsTrajectoryDO> doQueryWrapper = new QueryWrapper<>();
            doQueryWrapper.lambda().eq(SaleOmsLogisticsTrajectoryDO::getWhetherIncrement,record.getSubExternalOrderCode()).eq(BaseDO::getDeleted,0);
            List<SaleOmsLogisticsTrajectoryDO> omsList = saleOmsLogisticsTrajectoryDao.list(doQueryWrapper);
            if (Utils.isNotEmpty(omsList)){//如果存在数据 则删除
                List<SaleOmsLogisticsTrajectoryDTO> deleteOmsList = omsList.stream().map(oms -> {
                    SaleOmsLogisticsTrajectoryDTO trajectoryDO = new SaleOmsLogisticsTrajectoryDTO();
                    trajectoryDO.setId(oms.getId());
                    trajectoryDO.setDeleted(true);
                    return trajectoryDO;
                }).collect(Collectors.toList());
                List<SaleOmsLogisticsTrajectoryDO> trajectoryDOList = ObjectCloneUtils.convertList(deleteOmsList, SaleOmsLogisticsTrajectoryDO.class);
                saleOmsLogisticsTrajectoryDao.updateBatch(trajectoryDOList);
            }
        }
        log.info("生成的物流轨迹信息:{}", JSON.toJSONString(list));
        List<SaleOmsLogisticsTrajectoryDO> saleOmsLogisticsTrajectoryDOS = ObjectCloneUtils.convertList(list, SaleOmsLogisticsTrajectoryDO.class);
        saleOmsLogisticsTrajectoryDao.saveBatch(saleOmsLogisticsTrajectoryDOS);
        return Boolean.TRUE;
    }
    /**
     * @Description:  初始化新建数据.
     * @Param: [dto]
     * @return: void
     * @Author: SongTao
     * @Date: 2020/8/25
     */
    private void initDomainCreatedInfo(SaleOmsLogisticsTrajectoryDTO dto){
        Date now = new Date();
        dto.setDeleted(false);
        dto.setVersion(1);
        dto.setCreatedTime(now);
        dto.setUpdatedTime(now);
    }

    @Override
    public SaleOmsLogisticsTrajectoryDTO selectById(Long id) {
        if (id == null) {
            log.error("查询OMS物流轨迹,传入ID为空");
            return null;
        }
        SaleOmsLogisticsTrajectoryDO saleOmsLogisticsTrajectoryDo = saleOmsLogisticsTrajectoryDao.selectById(id);
        if (saleOmsLogisticsTrajectoryDo == null) {
            return null;
        }
        return saleOmsLogisticsTrajectoryDo.clone(SaleOmsLogisticsTrajectoryDTO.class);
    }

    @Override
    public Boolean updateById(Long id, SaleOmsLogisticsTrajectoryDTO record) {
        if (id == null) {
            log.error("修改OMS物流轨迹,传入ID为空");
            return false;
        }
        record.setId(id);
        SaleOmsLogisticsTrajectoryDO saleOmsLogisticsTrajectoryDo = record.clone(SaleOmsLogisticsTrajectoryDO.class);
        DomainUtils.initUpdateTimeInfo(saleOmsLogisticsTrajectoryDo);
        return saleOmsLogisticsTrajectoryDao.updateById(saleOmsLogisticsTrajectoryDo) ;
    }

}