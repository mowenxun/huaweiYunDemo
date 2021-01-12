package com.deepexi.dd.system.mall.controller.app;

import com.deepexi.dd.domain.business.domain.query.CommoditySearchHistoryRequestQuery;
import com.deepexi.dd.system.mall.domain.query.app.SearchHistoryRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.app.CommoditySearchHistoryResponseVO;
import com.deepexi.dd.system.mall.service.app.CommoditySearchHistoryService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author asus
 * @version 1.0
 * @date 2020-07-16 19:18
 */
@Api(value = "搜索历史管理", tags = "搜索历史管理")
@RequestMapping("/admin-api/v1/commoditySearchHistorys")
@RestController
public class CommoditySearchHistoryController {

    @Autowired
    private CommoditySearchHistoryService commoditySearchHistoryService;

    @ApiOperation("查询列表")
    @GetMapping("/list")
    public Payload<List<CommoditySearchHistoryResponseVO>> listCommoditySearchHistorys(@Valid SearchHistoryRequestQuery query) {
        return new Payload<>(ObjectCloneUtils.convertList(commoditySearchHistoryService.listCommoditySearchHistorys(
                query.clone(CommoditySearchHistoryRequestQuery.class, CloneDirection.OPPOSITE)),
                CommoditySearchHistoryResponseVO.class, CloneDirection.OPPOSITE));
    }

    @ApiOperation("根据IDS删除,如果为空即删除全部")
    @DeleteMapping
    public Payload<Boolean> deleteByIdIn(@RequestBody List<Long> ids) {
        return new Payload<>(commoditySearchHistoryService.deleteByIdIn(ids));
    }

}
