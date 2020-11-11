package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.PayOrderPlatformItemDO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformItemPageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author huanghuai
 * @version 1.0
 * @date 2020-08-21 18:49
 */
@Mapper
public interface PayOrderPlatformItemMapper extends BaseMapper<PayOrderPlatformItemDO> {

    List<PayOrderPlatformItemDO> findByQuery(PayOrderPlatformItemPageDTO query);
}
