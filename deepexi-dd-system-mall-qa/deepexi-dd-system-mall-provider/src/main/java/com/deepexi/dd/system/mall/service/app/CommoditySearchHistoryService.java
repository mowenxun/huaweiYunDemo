package com.deepexi.dd.system.mall.service.app;

import com.deepexi.dd.domain.business.domain.dto.CommoditySearchHistoryRequestDTO;
import com.deepexi.dd.domain.business.domain.dto.CommoditySearchHistoryResponseDTO;
import com.deepexi.dd.domain.business.domain.query.CommoditySearchHistoryRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author asus
 * @version 1.0
 * @date 2020-07-20 9:37
 */
public interface CommoditySearchHistoryService {

    /**
     * 查询列表
     *
     * @return
     */
    List<CommoditySearchHistoryResponseDTO> listCommoditySearchHistorys(CommoditySearchHistoryRequestQuery query);

    /**
     * 分页查询列表
     *
     * @return
     */
    PageBean<CommoditySearchHistoryResponseDTO> listCommoditySearchHistorysPage(CommoditySearchHistoryRequestQuery query);


    /**
     * 根据IDS删除
     *
     * @param ids
     * @return
     */
    Boolean deleteByIdIn(@RequestBody List<Long> ids);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    CommoditySearchHistoryResponseDTO insert(@RequestBody CommoditySearchHistoryRequestDTO record);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    CommoditySearchHistoryResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(@PathVariable Long id, @RequestBody CommoditySearchHistoryRequestDTO record);
}
