package com.deepexi.dd.middle.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.dao.SaleOrderInfoDAO;
import com.deepexi.dd.middle.order.dao.SaleOrderSplitRecordDAO;
import com.deepexi.dd.middle.order.domain.SaleOrderSplitRecordDO;
import com.deepexi.dd.middle.order.domain.SaleOrderSplitRecordDTO;
import com.deepexi.dd.middle.order.domain.SaleOrderSplitRecordQuery;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderAmountStatuEditDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoAmountEditDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderSplitRecordResponseDTO;
import com.deepexi.dd.middle.order.service.SaleOrderSplitRecordService;
import com.deepexi.dd.middle.order.util.DomainUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.page.PageMethod;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * SaleOrderSplitRecordServiceImpl
 *
 * @author admin
 * @date Wed Aug 12 20:24:31 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class SaleOrderSplitRecordServiceImpl implements SaleOrderSplitRecordService {
    @Autowired
    private SaleOrderInfoDAO saleOrderInfoDAO;
    @Autowired
    private SaleOrderSplitRecordDAO saleOrderSplitRecordDao;

    @Override
    public List<SaleOrderSplitRecordDTO> listSaleOrderSplitRecords(SaleOrderSplitRecordQuery query) {
        return ObjectCloneUtils.convertList(saleOrderSplitRecordDao.listSaleOrderSplitRecords(query), SaleOrderSplitRecordDTO.class);
    }


    @Override
    public PageBean<SaleOrderSplitRecordDTO> listSaleOrderSplitRecordsPage(SaleOrderSplitRecordQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(saleOrderSplitRecordDao.listSaleOrderSplitRecords(query)), SaleOrderSplitRecordDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除销售订单拆单记录表,传入ID为空");
            return false;
        }
        return saleOrderSplitRecordDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public SaleOrderSplitRecordDTO insert(SaleOrderSplitRecordDTO record) {
        if (record == null) {
            log.error("新增销售订单拆单记录表,传入参数为空");
            return null;
        }
        SaleOrderSplitRecordDO saleOrderSplitRecordDo = record.clone(SaleOrderSplitRecordDO.class);
        saleOrderSplitRecordDo.setPaymentType(0);//默认无支付方式 后面支付后来会更新该字段
        DomainUtils.initDomainCreatedInfo(saleOrderSplitRecordDo);
        if (saleOrderSplitRecordDao.insert(saleOrderSplitRecordDo) > 0) {
            record.setId(saleOrderSplitRecordDo.getId());
            return record;
        }
        log.error("新增销售订单拆单记录表失败");
        return null;
    }

    @Override
    public SaleOrderSplitRecordDTO selectById(Long id) {
        if (id == null) {
            log.error("查询销售订单拆单记录表,传入ID为空");
            return null;
        }
        SaleOrderSplitRecordDO saleOrderSplitRecordDo = saleOrderSplitRecordDao.selectById(id);
        if (saleOrderSplitRecordDo == null) {
            return null;
        }
        return saleOrderSplitRecordDo.clone(SaleOrderSplitRecordDTO.class);
    }

    @Override
    public Boolean updateById(Long id, SaleOrderSplitRecordDTO record) {
        if (id == null) {
            log.error("修改销售订单拆单记录表,传入ID为空");
            return false;
        }
        record.setId(id);
        SaleOrderSplitRecordDO saleOrderSplitRecordDo = record.clone(SaleOrderSplitRecordDO.class);
        DomainUtils.initUpdateTimeInfo(saleOrderSplitRecordDo);
        return saleOrderSplitRecordDao.updateById(saleOrderSplitRecordDo);
    }

    @Override
    @Transactional(rollbackFor={Exception.class})
    public Boolean updateOrderAmountAndStatus(SaleOrderAmountStatuEditDTO editDto) {
        SaleOrderSplitRecordDO saleOrderSplitRecordDO=editDto.clone(SaleOrderSplitRecordDO.class);
        saleOrderSplitRecordDO.setId(editDto.getId());
        saleOrderSplitRecordDao.updateById(saleOrderSplitRecordDO);
        //更新子订单金额
        if(CollectionUtil.isNotEmpty(editDto.getSubList())) {
            for(SaleOrderInfoAmountEditDTO dto:editDto.getSubList()) {
                saleOrderInfoDAO.updateOrderAmount(dto);
            }
        }
        return true;
    }

    @Override
    public SaleOrderSplitRecordResponseDTO selectByCode(String code) {
        QueryWrapper<SaleOrderSplitRecordDO> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(SaleOrderSplitRecordDO::getCode,code).eq(SaleOrderSplitRecordDO::getDeleted,0);
        SaleOrderSplitRecordDO saleOrderSplitRecordDO=   saleOrderSplitRecordDao.getOne(queryWrapper);
        if(saleOrderSplitRecordDO!=null){
            return saleOrderSplitRecordDO.clone(SaleOrderSplitRecordResponseDTO.class);
        }
        return null;
    }
}