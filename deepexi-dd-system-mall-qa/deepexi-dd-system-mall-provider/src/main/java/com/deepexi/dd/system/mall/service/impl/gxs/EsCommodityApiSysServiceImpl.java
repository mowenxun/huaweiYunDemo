package com.deepexi.dd.system.mall.service.impl.gxs;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/12/16/14:43
 * @Description:
 */

import com.alibaba.fastjson.JSONObject;
import com.deepexi.commodity.dto.es.EsGoodInformationApiRequestVO;
import com.deepexi.commodity.dto.es.EsGoodInformationApiResponseVO;
import com.deepexi.dd.domain.business.domain.dto.BusinessCommodityRelationshipResponseDTO;
import com.deepexi.dd.system.mall.domain.vo.commodity.EsGoodInformationApiSysResponseVO;
import com.deepexi.dd.system.mall.remote.business.BusinessCommodityRelationshipDomainRemote;
import com.deepexi.dd.system.mall.remote.commodity.EsCommodityApiSysRemote;
import com.deepexi.dd.system.mall.service.gxs.EsCommodityApiSysService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName EsCommodityApiSysServiceImpl
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/12/16
 * @Version V1.0
 **/
@Service
@Slf4j
public class EsCommodityApiSysServiceImpl implements EsCommodityApiSysService {

    @Autowired
    private EsCommodityApiSysRemote esCommodityApiSysRemote;

    @Autowired
    private BusinessCommodityRelationshipDomainRemote businessCommodityRelationshipDomainRemote;

    @Override
    public PageBean<EsGoodInformationApiSysResponseVO> getPage(EsGoodInformationApiRequestVO request) {
        PageBean<EsGoodInformationApiResponseVO> pageBean = esCommodityApiSysRemote.getPage(request);
        log.info("商品域pageBean=={}", JSONObject.toJSONString(pageBean));
        if (pageBean != null && CollectionUtils.isNotEmpty(pageBean.getContent())) {
            PageBean<EsGoodInformationApiSysResponseVO> voPageBean = ObjectCloneUtils.convertPageBean(pageBean,
                    EsGoodInformationApiSysResponseVO.class, CloneDirection.OPPOSITE);
            List<Long> allskuIds = new ArrayList<>();
            voPageBean.getContent().forEach(item -> {
                List<Long> skuIds =
                        item.getSkuList().stream().map(EsGoodInformationApiSysResponseVO.
                                EsGoodInformationGroupSkuResponseVO::getId).collect(Collectors.toList());
                allskuIds.addAll(skuIds);
            });
            //所有的上架的商品
            List<BusinessCommodityRelationshipResponseDTO> list =
                    businessCommodityRelationshipDomainRemote.findBusinessCommodityByGoodId(allskuIds);
            if (CollectionUtils.isEmpty(list)) {
                voPageBean.getContent().forEach(item -> {
                    //下线给编辑
                    item.getItemWhole().setIsUpdate(true);
                });
                return voPageBean;
            }
            List<Long> onLineIds =
                    list.stream().map(BusinessCommodityRelationshipResponseDTO::getSkuId).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(onLineIds)) {
                voPageBean.getContent().forEach(item -> {
                    //只要有一个sku是上线状态就不给编辑
                    List<EsGoodInformationApiSysResponseVO.EsGoodInformationGroupSkuResponseVO> skuList =
                            item.getSkuList();
                    int count = 0;
                    for (EsGoodInformationApiSysResponseVO.EsGoodInformationGroupSkuResponseVO skuResponseVO :
                            skuList) {
                        if (onLineIds.contains(skuResponseVO.getId())) {
                            count++;
                            //item.getItemWhole().setIsUpdate(true);
                            //break;
                        }

                    }
                    item.getItemWhole().setIsUpdate(true);
                    if (count != 0) {
                        item.getItemWhole().setIsUpdate(false);
                    }
                });
            }
            return voPageBean;
        } else {
            return new PageBean<EsGoodInformationApiSysResponseVO>();
        }
    }
}
