package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.ActivitySkuOrderQuantityDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-21 18:49
 */
@Mapper
public interface ActivitySkuOrderQuantityMapper extends BaseMapper<ActivitySkuOrderQuantityDO> {

    /**
     * 逻辑删除sku
     * @param id
     */
    int updateActivitySkuOrder(@Param("id") Long id);
}
