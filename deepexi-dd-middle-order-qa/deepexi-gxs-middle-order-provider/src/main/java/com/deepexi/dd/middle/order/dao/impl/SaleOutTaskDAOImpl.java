package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.SaleOutTaskDAO;
import com.deepexi.dd.middle.order.domain.SaleOutTaskDO;
import com.deepexi.dd.middle.order.domain.query.SaleOutTaskMiddleRequestQuery;
import com.deepexi.dd.middle.order.mapper.SaleOutTaskMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SaleOutTaskDAOImpl
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
@Repository
public class SaleOutTaskDAOImpl extends ServiceImpl<SaleOutTaskMapper, SaleOutTaskDO> implements SaleOutTaskDAO {
/*    @Autowired
    private SaleOutTaskMapper saleOutTaskMapper;*/

    @Override
    public List<SaleOutTaskDO> listSaleOutTasks(SaleOutTaskMiddleRequestQuery query) {
        return baseMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return baseMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SaleOutTaskDO record) {
        return baseMapper.insert(record);
    }

    @Override
    public SaleOutTaskDO selectById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * @param day
     * @Description: 根据天数查出自动签收出库单的数据.
     * @Param: [day]
     * @return: java.util.List<java.lang.Long>
     * @Author: SongTao
     * @Date: 2020/7/21
     */
    @Override
    public List<Long> selectIdListForAutoSign(Integer day) {
        return baseMapper.selectIdListForAutoSign(day);
    }

    @Override
    public List<SaleOutTaskDO> findSaleOutTaskByIds(List<Long> saleOrderId) {
        return baseMapper.findSaleOutTaskByIds(saleOrderId);
    }

   /* @Override
    public int updateById(SaleOutTaskDO record) {
        return saleOutTaskMapper.updateById(record);
    }*/

}