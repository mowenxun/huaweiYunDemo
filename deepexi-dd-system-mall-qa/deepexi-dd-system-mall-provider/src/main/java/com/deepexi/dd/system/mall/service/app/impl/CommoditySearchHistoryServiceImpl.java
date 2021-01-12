package com.deepexi.dd.system.mall.service.app.impl;

import com.deepexi.dd.domain.business.domain.dto.CommoditySearchHistoryRequestDTO;
import com.deepexi.dd.domain.business.domain.dto.CommoditySearchHistoryResponseDTO;
import com.deepexi.dd.domain.business.domain.query.CommoditySearchHistoryRequestQuery;
import com.deepexi.dd.system.mall.remote.commodity.CommoditySearchHistoryRemote;
import com.deepexi.dd.system.mall.service.app.CommoditySearchHistoryService;
import com.deepexi.util.pageHelper.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author asus
 * @version 1.0
 * @date 2020-07-20 9:48
 */
@Service
@Slf4j
public class CommoditySearchHistoryServiceImpl implements CommoditySearchHistoryService {

    @Autowired
    private CommoditySearchHistoryRemote commoditySearchHistoryRemote;

    @Override
    public List<CommoditySearchHistoryResponseDTO> listCommoditySearchHistorys(CommoditySearchHistoryRequestQuery query) {
        return commoditySearchHistoryRemote.listCommoditySearchHistorys(query);
    }

    @Override
    public PageBean<CommoditySearchHistoryResponseDTO> listCommoditySearchHistorysPage(CommoditySearchHistoryRequestQuery query) {
        return commoditySearchHistoryRemote.listCommoditySearchHistorysPage(query);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> ids) {
        return commoditySearchHistoryRemote.deleteByIdIn(ids);
    }

    @Override
    public CommoditySearchHistoryResponseDTO insert(CommoditySearchHistoryRequestDTO record) {
        return commoditySearchHistoryRemote.insert(record);
    }

    @Override
    public CommoditySearchHistoryResponseDTO selectById(Long id) {
        return commoditySearchHistoryRemote.selectById(id);
    }

    @Override
    public Boolean updateById(Long id, CommoditySearchHistoryRequestDTO record) {
        return commoditySearchHistoryRemote.updateById(id, record);
    }
}
