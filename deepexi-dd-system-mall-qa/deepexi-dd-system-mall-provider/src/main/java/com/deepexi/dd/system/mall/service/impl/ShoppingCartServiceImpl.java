package com.deepexi.dd.system.mall.service.impl;

import com.deepexi.clientiam.model.GroupResultVO;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.CommonUtils;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.tool.domain.dto.ToolSkuAuthorizeOrgResponseDTO;
import com.deepexi.dd.domain.tool.domain.dto.ToolSkuRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.shoppingcart.ShoppingCartCommodityDTO;
import com.deepexi.dd.domain.transaction.domain.dto.shoppingcart.ShoppingCartCommodityResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.shoppingcart.ShoppingCartDTO;
import com.deepexi.dd.domain.transaction.domain.dto.shoppingcart.ShoppingCartResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.shoppingcart.ShoppingCartCommodityQuery;
import com.deepexi.dd.middle.tool.domain.dto.ToolBilltypeResponseDTO;
import com.deepexi.dd.middle.tool.domain.query.ToolBilltypeRequestQuery;
import com.deepexi.dd.system.mall.domain.dto.shoppingcart.ShoppingCartRequestDTO;
import com.deepexi.dd.system.mall.domain.query.shoppingcart.ShoppingCartQuery;
import com.deepexi.dd.system.mall.domain.vo.shoppingcart.ShoppingCartResponseVO;
import com.deepexi.dd.system.mall.remote.commodity.ToolCommoditytypeAuthorizeApiRemote;
import com.deepexi.dd.system.mall.remote.order.ShoppingCartRemote;
import com.deepexi.dd.system.mall.remote.tool.ToolBilltypeRemote;
import com.deepexi.dd.system.mall.service.ShoppingCartService;
import com.deepexi.util.config.Payload;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 购物车接口实现
 *
 * @author yangwu
 * @date 2020/7/6
 */
@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Resource
    private ShoppingCartRemote shoppingCartRemote;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private ToolBilltypeRemote toolBilltypeRemote;

    @Autowired
    private ToolCommoditytypeAuthorizeApiRemote apiRemote;

    @Override
    public Boolean addCommodity(ShoppingCartRequestDTO cartDTO) throws Exception {
        ShoppingCartDTO shoppingCartDTO = cartDTO.clone(ShoppingCartDTO.class);
        shoppingCartDTO.setShoppingCartCommodityDTOList(GeneralConvertUtils.convert2List(cartDTO.getShoppingCartCommodityDTOList(), ShoppingCartCommodityDTO.class));
        shoppingCartDTO.getShoppingCartCommodityDTOList().stream().forEach(data -> {
            data.setOperationTime(System.currentTimeMillis());
            data.setOrderTypeId(cartDTO.getOrderTypeId());
        });
        return shoppingCartRemote.addCommodity(shoppingCartDTO);
    }

    @Override
    public Boolean checkCommodity(ShoppingCartRequestDTO cartDTO) throws Exception {
        ShoppingCartDTO shoppingCartDTO = cartDTO.clone(ShoppingCartDTO.class);
        shoppingCartDTO.setShoppingCartCommodityDTOList(GeneralConvertUtils.convert2List(cartDTO.getShoppingCartCommodityDTOList(), ShoppingCartCommodityDTO.class));
        shoppingCartDTO.getShoppingCartCommodityDTOList().stream().forEach(data -> {
            data.setOperationTime(System.currentTimeMillis());
            data.setOrderTypeId(cartDTO.getOrderTypeId());
        });
        return shoppingCartRemote.checkCommodity(shoppingCartDTO);
    }


    private ToolSkuRequestDTO converterToolSkuBody(List<Long> skuIds, Long appId, Long orgId) {
        ToolSkuRequestDTO toolSkuRequestDTO = new ToolSkuRequestDTO();
        toolSkuRequestDTO.setAppId(appId);
        toolSkuRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
        toolSkuRequestDTO.setOrgId(orgId);
        toolSkuRequestDTO.setSkuIds(StringUtils.join(skuIds, ","));
        return toolSkuRequestDTO;
    }

    @Override
    public Boolean delCommodity(ShoppingCartRequestDTO shoppingCartRequestDTO) {
        ShoppingCartDTO clone = shoppingCartRequestDTO.clone(ShoppingCartDTO.class);
        clone.setShoppingCartCommodityDTOList(GeneralConvertUtils.convert2List(shoppingCartRequestDTO.getShoppingCartCommodityDTOList(), ShoppingCartCommodityDTO.class));
        return shoppingCartRemote.delCommodity(clone);
    }

    @Override
    public Boolean updateCommodity(ShoppingCartRequestDTO updateDTO) throws Exception {
        ShoppingCartDTO shoppingCartDTO = updateDTO.clone(ShoppingCartDTO.class);
        shoppingCartDTO.setShoppingCartCommodityDTOList(GeneralConvertUtils.convert2List(updateDTO.getShoppingCartCommodityDTOList(), ShoppingCartCommodityDTO.class));
        shoppingCartDTO.getShoppingCartCommodityDTOList().stream().forEach(data -> {
            data.setOperationTime(System.currentTimeMillis());
            data.setOrderTypeId(updateDTO.getOrderTypeId());
        });
        return shoppingCartRemote.updateCommodity(shoppingCartDTO);
    }

    @Override
    public List<ShoppingCartResponseVO> searchCommodity(ShoppingCartQuery shoppingCartQuery) throws Exception {
        com.deepexi.dd.domain.transaction.domain.query.shoppingcart.ShoppingCartQuery clone = shoppingCartQuery.clone(com.deepexi.dd.domain.transaction.domain.query.shoppingcart.ShoppingCartQuery.class);
        List<ShoppingCartResponseDTO> results = shoppingCartRemote.searchCommodity(clone);
        if (CommonUtils.isEmpty(results)) {
            return Lists.newArrayList();
        }
        List<ShoppingCartResponseVO> result = GeneralConvertUtils.convert2List(results, ShoppingCartResponseVO.class);
        return result;
    }

    /**
     * @Description: 获取购物车商品总数.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/7/28
     */
    @Override
    public Long getShoppingCount(ShoppingCartQuery shoppingCartQuery) {
        com.deepexi.dd.domain.transaction.domain.query.shoppingcart.ShoppingCartQuery clone = shoppingCartQuery.clone(com.deepexi.dd.domain.transaction.domain.query.shoppingcart.ShoppingCartQuery.class);
        return shoppingCartRemote.getShoppingCount(clone);
    }

    /**
     * @Description: 查询购物车.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/7/29
     */
    @Override
    public List<ShoppingCartCommodityResponseDTO> searchShopping(ShoppingCartQuery shoppingCartQuery) {
        com.deepexi.dd.domain.transaction.domain.query.shoppingcart.ShoppingCartQuery clone = shoppingCartQuery.clone(com.deepexi.dd.domain.transaction.domain.query.shoppingcart.ShoppingCartQuery.class);
        clone.setShoppingList(GeneralConvertUtils.convert2List(shoppingCartQuery.getShoppingCartCommodityDTOList(), ShoppingCartCommodityQuery.class));
        // 如果组织没有,默认去顶级组织.
        GroupResultVO group = appRuntimeEnv.getTopOrganization();
        clone.setAffiliatedOrganizationId(group.getId());
        return shoppingCartRemote.searchShopping(clone);
    }

    /**
     * @Description: 查询购物车提供给APP使用.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/7/29
     */
    @Override
    public List<ShoppingCartCommodityResponseDTO> searchShoppingToApp(ShoppingCartQuery shoppingCartQuery) {
        com.deepexi.dd.domain.transaction.domain.query.shoppingcart.ShoppingCartQuery clone = shoppingCartQuery.clone(com.deepexi.dd.domain.transaction.domain.query.shoppingcart.ShoppingCartQuery.class);
        List<ShoppingCartCommodityResponseDTO> result = shoppingCartRemote.searchShoppingToApp(clone);
        return GeneralConvertUtils.convert2List(result, ShoppingCartCommodityResponseDTO.class);
    }

    /**
     * @Description: 查询最新的记录.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/17
     */
    @Override
    public List<ShoppingCartCommodityResponseDTO> searchCommodityDesDate(Long appId, String tenantId) throws Exception {
        return shoppingCartRemote.searchCommodityDesDate(appId, tenantId);
    }
}
