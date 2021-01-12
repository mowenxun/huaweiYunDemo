package com.deepexi.dd.system.mall.controller.orderOnline;

import com.deepexi.dd.domain.common.domain.query.OnlineActivitiesGoodQuery;
import com.deepexi.dd.domain.common.domain.query.OnlineActivitiesQuery;
import com.deepexi.dd.system.mall.domain.query.orderOnline.OnlineActivitiesGoodRequestQuery;
import com.deepexi.dd.system.mall.domain.query.orderOnline.OnlineActivitiesRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.app.CommodityTypeResponseVO;
import com.deepexi.dd.system.mall.domain.vo.app.ShelvesItemSkuDetailResponseVO;
import com.deepexi.dd.system.mall.domain.vo.orderOnline.OnlineActivitiesImageUrlResponseVO;
import com.deepexi.dd.system.mall.domain.vo.orderOnline.OnlineActivitiesResponseVO;
import com.deepexi.dd.system.mall.service.orderOnline.OnlineActivitiesService;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName OnlineActivitiesController
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-19
 * @Version 1.0
 **/
@Api(value = "在线订货活动展示管理", tags = "在线订货活动展示管理")
@RequestMapping("/admin-api/v1/domain/online/activities")
@RestController
public class OnlineActivitiesController {

    @Autowired
    private OnlineActivitiesService activitiesService;

    @GetMapping("/getActivitiesImageUrl")
    @ApiOperation(value = "查询活动宣传图", nickname = "getActivitiesImageUrl")
    public Payload<List<OnlineActivitiesImageUrlResponseVO>> getActivitiesImageUrl(@Valid OnlineActivitiesRequestQuery activitiesQuery) throws Exception {
        List<OnlineActivitiesImageUrlResponseVO> result = activitiesService.getActivitiesImageUrl(activitiesQuery.clone(OnlineActivitiesQuery.class));
        return new Payload<>(result);
    }

    @GetMapping("/findActivities")
    @ApiOperation(value = "查询活动列表", nickname = "findActivities")
    public Payload<List<OnlineActivitiesResponseVO>> findActivities(@Valid OnlineActivitiesRequestQuery activitiesQuery) throws Exception {
        List<OnlineActivitiesResponseVO> result = activitiesService.findActivities(activitiesQuery.clone(OnlineActivitiesQuery.class));
        return new Payload<>(result);
    }

    @GetMapping("/getCommodityByActivities")
    @ApiOperation(value = "通过活动查询商品", nickname = "getCommodityByActivities")
    public Payload<OnlineActivitiesResponseVO> getCommodityByActivities(@Valid OnlineActivitiesGoodRequestQuery requestQuery) throws Exception {
        OnlineActivitiesResponseVO result = activitiesService.getCommodityByActivities(requestQuery.clone(OnlineActivitiesGoodQuery.class));
        return new Payload<>(result);
    }

    @GetMapping("/getActivitiesDetail")
    @ApiOperation(value = "查询商品详情", nickname = "getActivitiesDetail")
    public Payload<ShelvesItemSkuDetailResponseVO> getActivitiesDetail(@Valid OnlineActivitiesGoodRequestQuery requestQuery) throws Exception {
        ShelvesItemSkuDetailResponseVO result = activitiesService.getActivitiesDetail(requestQuery);
        return new Payload<>(result);
    }
}
