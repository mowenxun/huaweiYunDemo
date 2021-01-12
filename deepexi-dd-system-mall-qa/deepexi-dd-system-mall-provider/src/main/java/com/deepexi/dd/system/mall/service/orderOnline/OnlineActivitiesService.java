package com.deepexi.dd.system.mall.service.orderOnline;

import com.deepexi.dd.domain.common.domain.query.OnlineActivitiesGoodQuery;
import com.deepexi.dd.domain.common.domain.query.OnlineActivitiesQuery;
import com.deepexi.dd.system.mall.domain.query.orderOnline.OnlineActivitiesGoodRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.app.CommodityTypeResponseVO;
import com.deepexi.dd.system.mall.domain.vo.app.ShelvesItemSkuDetailResponseVO;
import com.deepexi.dd.system.mall.domain.vo.orderOnline.OnlineActivitiesImageUrlResponseVO;
import com.deepexi.dd.system.mall.domain.vo.orderOnline.OnlineActivitiesResponseVO;

import java.util.List;

/**
 * @ClassName OnlineActivitiesService
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-19
 * @Version 1.0
 **/
public interface OnlineActivitiesService {

    List<OnlineActivitiesResponseVO> findActivities(OnlineActivitiesQuery activitiesQuery) throws Exception;

    List<OnlineActivitiesImageUrlResponseVO> getActivitiesImageUrl(OnlineActivitiesQuery activitiesQuery) throws Exception;

    OnlineActivitiesResponseVO getCommodityByActivities(OnlineActivitiesGoodQuery goodQuery) throws Exception;

    ShelvesItemSkuDetailResponseVO getActivitiesDetail(OnlineActivitiesGoodRequestQuery requestQuery) throws Exception;
}
