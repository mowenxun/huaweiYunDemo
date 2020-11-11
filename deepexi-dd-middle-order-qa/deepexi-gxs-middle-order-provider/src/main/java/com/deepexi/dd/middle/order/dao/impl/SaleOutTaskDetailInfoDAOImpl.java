package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.SaleOutTaskDetailInfoDAO;
import com.deepexi.dd.middle.order.domain.SaleOutTaskDetailInfoDO;
import com.deepexi.dd.middle.order.domain.query.SaleOutTaskDetailInfoQuery;
import com.deepexi.dd.middle.order.mapper.SaleOutTaskDetailInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName SaleOutTaskDetailInfoDAOImpl
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-07
 * @Version 1.0
 **/
@Repository
public class SaleOutTaskDetailInfoDAOImpl extends ServiceImpl<SaleOutTaskDetailInfoMapper, SaleOutTaskDetailInfoDO> implements SaleOutTaskDetailInfoDAO {
    @Autowired
    private SaleOutTaskDetailInfoMapper saleOutTaskDetailInfoMapper;

    @Override
    public List<SaleOutTaskDetailInfoDO> listSaleOutTaskDetailInfos(SaleOutTaskDetailInfoQuery query) {
        return saleOutTaskDetailInfoMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return saleOutTaskDetailInfoMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SaleOutTaskDetailInfoDO record) {
        return saleOutTaskDetailInfoMapper.insert(record);
    }

    @Override
    public SaleOutTaskDetailInfoDO selectById(Long id) {
        return saleOutTaskDetailInfoMapper.selectById(id);
    }

    @Override
    public boolean updateById(SaleOutTaskDetailInfoDO record) {
        saleOutTaskDetailInfoMapper.updateById(record);
        return true;
    }

}
