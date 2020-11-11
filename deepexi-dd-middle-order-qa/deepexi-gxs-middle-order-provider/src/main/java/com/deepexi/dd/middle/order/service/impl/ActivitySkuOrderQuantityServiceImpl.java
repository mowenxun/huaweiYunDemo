package com.deepexi.dd.middle.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.dao.ActivitySkuOrderQuantityDao;
import com.deepexi.dd.middle.order.domain.ActivitySkuOrderQuantityDO;
import com.deepexi.dd.middle.order.domain.dto.ActivitySkuOrderQuantityDTO;
import com.deepexi.dd.middle.order.domain.query.ActivitySkuOrderQuantityQuery;
import com.deepexi.dd.middle.order.service.ActivitySkuOrderQuantityService;
import com.deepexi.util.pojo.ObjectCloneUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-21 19:11
 */
@Service
@Slf4j
public class ActivitySkuOrderQuantityServiceImpl implements ActivitySkuOrderQuantityService {

    @Autowired
    private ActivitySkuOrderQuantityDao activitySkuOrderQuantityDao;

    @Override
    public void batchInsert(List<ActivitySkuOrderQuantityDTO> activitySkuOrderQuantityDTOList) {
        activitySkuOrderQuantityDao.batchInsert(activitySkuOrderQuantityDTOList);
    }

    @Override
    public void updateActivitySkuOrder(Long id) {
        activitySkuOrderQuantityDao.updateActivitySkuOrder(id);
    }

    @Override
    public List<ActivitySkuOrderQuantityDTO> queryActivitySku(ActivitySkuOrderQuantityQuery query) {
        QueryWrapper<ActivitySkuOrderQuantityDO> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(ActivitySkuOrderQuantityDO::getActivitiesId,query.getActivitiesId()).eq(ActivitySkuOrderQuantityDO::getDeleted,0).in(ActivitySkuOrderQuantityDO::getSkuId,query.getSkuIds());
        if(query.getPartnerId()!=null){
            queryWrapper.lambda().eq(ActivitySkuOrderQuantityDO::getPartnerId,query.getPartnerId());
        }
        List<ActivitySkuOrderQuantityDO> list= activitySkuOrderQuantityDao.list(queryWrapper);
        return ObjectCloneUtils.convertList(list,ActivitySkuOrderQuantityDTO.class);
    }
}
