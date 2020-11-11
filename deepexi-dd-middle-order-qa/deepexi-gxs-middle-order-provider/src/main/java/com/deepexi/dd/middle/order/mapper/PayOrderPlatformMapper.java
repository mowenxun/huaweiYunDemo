package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.PayOrderPlatformDO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformPageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author huanghuai
 * @version 1.0
 * @date 2020-08-21 18:49
 */
@Mapper
public interface PayOrderPlatformMapper extends BaseMapper<PayOrderPlatformDO> {

    List<PayOrderPlatformDO> findByQuery(PayOrderPlatformPageDTO query);
}
