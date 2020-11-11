package com.deepexi.dd.middle.order.dao.impl;

import com.deepexi.dd.middle.order.dao.SaleCloudInterfaceRecordDAO;
import com.deepexi.dd.middle.order.domain.SaleCloudInterfaceRecordDO;
import com.deepexi.dd.middle.order.domain.SaleCloudInterfaceRecordQuery;
import com.deepexi.dd.middle.order.mapper.SaleCloudInterfaceRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SaleCloudInterfaceRecordDAOImpl
 *
 * @author admin
 * @date Wed Aug 26 19:14:46 CST 2020
 * @version 1.0
 */
@Repository
public class SaleCloudInterfaceRecordDAOImpl implements SaleCloudInterfaceRecordDAO {
    @Autowired
    private SaleCloudInterfaceRecordMapper saleCloudInterfaceRecordMapper;

    @Override
    public List<SaleCloudInterfaceRecordDO> listSaleCloudInterfaceRecords(SaleCloudInterfaceRecordQuery query) {
        return saleCloudInterfaceRecordMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return saleCloudInterfaceRecordMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SaleCloudInterfaceRecordDO record) {
        return saleCloudInterfaceRecordMapper.insert(record);
    }

    @Override
    public SaleCloudInterfaceRecordDO selectById(Long id) {
        return saleCloudInterfaceRecordMapper.selectById(id);
    }

    @Override
    public int updateById(SaleCloudInterfaceRecordDO record) {
        return saleCloudInterfaceRecordMapper.updateById(record);
    }

}