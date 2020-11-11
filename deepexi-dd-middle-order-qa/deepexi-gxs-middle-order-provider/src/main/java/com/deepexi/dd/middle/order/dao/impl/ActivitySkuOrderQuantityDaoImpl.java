package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.ActivitySkuOrderQuantityDao;
import com.deepexi.dd.middle.order.domain.ActivitySkuOrderQuantityDO;
import com.deepexi.dd.middle.order.domain.dto.ActivitySkuOrderQuantityDTO;
import com.deepexi.dd.middle.order.mapper.ActivitySkuOrderQuantityMapper;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-21 19:13
 */
@Repository
public class ActivitySkuOrderQuantityDaoImpl extends ServiceImpl<ActivitySkuOrderQuantityMapper, ActivitySkuOrderQuantityDO> implements ActivitySkuOrderQuantityDao {

    /**
     * 批量插入
     * @param activitySkuOrderQuantityDTOList
     */
    @Override
    public void batchInsert(List<ActivitySkuOrderQuantityDTO> activitySkuOrderQuantityDTOList) {
        List<ActivitySkuOrderQuantityDO> activitySkuOrderQuantityDOS =
                ObjectCloneUtils.convertList(activitySkuOrderQuantityDTOList, ActivitySkuOrderQuantityDO.class);
        activitySkuOrderQuantityDOS.forEach(a ->  baseMapper.insert(a));

    }

    @Override
    public void updateActivitySkuOrder(Long id) {
        baseMapper.updateActivitySkuOrder(id);
    }
}
