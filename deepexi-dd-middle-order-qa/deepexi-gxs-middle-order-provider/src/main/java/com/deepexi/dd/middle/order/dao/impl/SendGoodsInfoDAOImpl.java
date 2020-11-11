package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.SendGoodsInfoDAO;
import com.deepexi.dd.middle.order.domain.OrderInvoiceDO;
import com.deepexi.dd.middle.order.domain.SendGoodsInfoDO;
import com.deepexi.dd.middle.order.domain.SendGoodsInfoQuery;
import com.deepexi.dd.middle.order.mapper.OrderInvoiceMapper;
import com.deepexi.dd.middle.order.mapper.SendGoodsInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.security.cert.TrustAnchor;
import java.util.List;

/**
 * SendGoodsInfoDAOImpl
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
@Repository
public class SendGoodsInfoDAOImpl extends ServiceImpl<SendGoodsInfoMapper, SendGoodsInfoDO> implements SendGoodsInfoDAO {
    /*@Autowired
    private SendGoodsInfoMapper sendGoodsInfoMapper;*/

    @Override
    public List<SendGoodsInfoDO> listSendGoodsInfos(SendGoodsInfoQuery query) {
        return baseMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return baseMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SendGoodsInfoDO record) {
        return baseMapper.insert(record);
    }

    @Override
    public SendGoodsInfoDO selectById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public boolean updateById(SendGoodsInfoDO record) {
        if(baseMapper.updateById(record)>0){
            return true;
        }
        return false;
    }

}