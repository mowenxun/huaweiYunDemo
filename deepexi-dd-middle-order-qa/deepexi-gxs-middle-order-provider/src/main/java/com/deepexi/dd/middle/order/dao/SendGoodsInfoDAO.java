package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.SendGoodsInfoDO;
import com.deepexi.dd.middle.order.domain.SendGoodsInfoQuery;

import java.util.List;


/**
 * SendGoodsInfoDAO
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
public interface SendGoodsInfoDAO extends IService<SendGoodsInfoDO> {

    /**
     * 查询发货表列表}
     *
     * @return
     */
    List<SendGoodsInfoDO> listSendGoodsInfos(SendGoodsInfoQuery query);

    /**
     * 根据ID删除发货表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增发货表
     *
     * @param record
     * @return
     */
    int insert(SendGoodsInfoDO record);

    /**
     * 查询发货表详情
     *
     * @param id
     * @return
     */
    SendGoodsInfoDO selectById(Long id);

    /**
     * 根据ID修改发货表
     *
     * @param record
     * @return
     */
    boolean updateById(SendGoodsInfoDO record);

}