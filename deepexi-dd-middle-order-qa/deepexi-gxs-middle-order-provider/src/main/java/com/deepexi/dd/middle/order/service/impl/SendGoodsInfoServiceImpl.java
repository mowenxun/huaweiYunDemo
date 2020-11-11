package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.SendGoodsInfoDAO;
import com.deepexi.dd.middle.order.domain.SendGoodsInfoDO;
import com.deepexi.dd.middle.order.domain.SendGoodsInfoDTO;
import com.deepexi.dd.middle.order.domain.SendGoodsInfoQuery;
import com.deepexi.dd.middle.order.service.SendGoodsInfoService;
import com.deepexi.dd.middle.order.util.DomainUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.page.PageMethod;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * SendGoodsInfoServiceImpl
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class SendGoodsInfoServiceImpl implements SendGoodsInfoService {

    @Autowired
    private SendGoodsInfoDAO sendGoodsInfoDao;

    @Override
    public List<SendGoodsInfoDTO> listSendGoodsInfos(SendGoodsInfoQuery query) {
        return ObjectCloneUtils.convertList(sendGoodsInfoDao.listSendGoodsInfos(query), SendGoodsInfoDTO.class);
    }


    @Override
    public PageBean<SendGoodsInfoDTO> listSendGoodsInfosPage(SendGoodsInfoQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(sendGoodsInfoDao.listSendGoodsInfos(query)), SendGoodsInfoDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除发货表,传入ID为空");
            return false;
        }
        return sendGoodsInfoDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public SendGoodsInfoDTO insert(SendGoodsInfoDTO record) {
        if (record == null) {
            log.error("新增发货表,传入参数为空");
            return null;
        }
        SendGoodsInfoDO sendGoodsInfoDo = record.clone(SendGoodsInfoDO.class);
        DomainUtils.initDomainCreatedInfo(sendGoodsInfoDo);
        if (sendGoodsInfoDao.insert(sendGoodsInfoDo) > 0) {
            record.setId(sendGoodsInfoDo.getId());
            return record;
        }
        log.error("新增发货表失败");
        return null;
    }

    @Override
    public SendGoodsInfoDTO selectById(Long id) {
        if (id == null) {
            log.error("查询发货表,传入ID为空");
            return null;
        }
        SendGoodsInfoDO sendGoodsInfoDo = sendGoodsInfoDao.selectById(id);
        if (sendGoodsInfoDo == null) {
            return null;
        }
        return sendGoodsInfoDo.clone(SendGoodsInfoDTO.class);
    }

    @Override
    public Boolean updateById(Long id, SendGoodsInfoDTO record) {
        if (id == null) {
            log.error("修改发货表,传入ID为空");
            return false;
        }
        record.setId(id);
        SendGoodsInfoDO sendGoodsInfoDo = record.clone(SendGoodsInfoDO.class);
        DomainUtils.initUpdateTimeInfo(sendGoodsInfoDo);
        return sendGoodsInfoDao.updateById(sendGoodsInfoDo);
    }

}