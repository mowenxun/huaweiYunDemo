package com.deepexi.dd.domain.transaction.service.impl;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/21/14:35
 * @Description:
 */

import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.enums.gxs.GxsResultEnum;
import com.deepexi.dd.domain.transaction.remote.gxs.GxsManagementRemoteService;
import com.deepexi.middle.merchant.domain.dto.GxsManagementResponseDTO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName commonServiceImpl
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/10/21
 * @Version V1.0
 **/
@Service
@Slf4j
public class commonServiceImpl {

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private GxsManagementRemoteService gxsManagementRemoteService;

    /**
     * 当前登陆者对应的店铺信息
     *
     * @return
     * @throws Exception
     */
    public GxsManagementResponseDTO getShop() throws Exception {
        //通过组织id获得店铺信息
        List<Long> ids = new ArrayList<>();
        Long orgId = appRuntimeEnv.getUserOrganization().getId();
        ids.add(orgId);
        Payload<List<GxsManagementResponseDTO>> payload = gxsManagementRemoteService.listGxsManagementByGroupIds(ids);
        if (payload == null || CollectionUtils.isEmpty(payload.getPayload())) {
            throw new ApplicationException(GxsResultEnum.NOT_FOUND_SHOP);
        }
        List<GxsManagementResponseDTO> list = GeneralConvertUtils.convert2List(payload.getPayload(),
                GxsManagementResponseDTO.class);
        GxsManagementResponseDTO shop = list.get(0);
        return shop;
    }

    /**
     * @param shopIds 店铺id集合
     * @return 供销社集合
     * @throws Exception
     */
    public List<GxsManagementResponseDTO> getShops(List<Long> shopIds) throws Exception {
        log.info("根据店铺id集合查询供销社；shopIds={}", shopIds);
        if (CollectionUtils.isEmpty(shopIds)) {
            throw new ApplicationException(GxsResultEnum.REQUEST_PARA_IS_NULL);
        }
        Payload<List<GxsManagementResponseDTO>> payload =
                gxsManagementRemoteService.listGxsManagementByIds(shopIds);
        if (payload == null || CollectionUtils.isEmpty(payload.getPayload())) {
            //控制顶层供销社报错
            if (shopIds.contains(0l)) {
                return new ArrayList<GxsManagementResponseDTO>();
            } else {
                throw new ApplicationException(GxsResultEnum.NOT_FOUND_SHOP_BY_ID);
            }
        }
        List<GxsManagementResponseDTO> list = GeneralConvertUtils.convert2List(payload.getPayload(),
                GxsManagementResponseDTO.class);
        return list;
    }

    /**
     * 通过id获得供销社
     *
     * @return
     * @throws Exception
     */
    public GxsManagementResponseDTO getShopById(Long id) throws Exception {
        if (id == null) {
            throw new ApplicationException(GxsResultEnum.REQUEST_PARA_IS_NULL);
        }
        //通过组织id获得店铺信息
        Payload<GxsManagementResponseDTO> payload = gxsManagementRemoteService.selectById(id);
        if (payload == null) {
            throw new ApplicationException(GxsResultEnum.NOT_FOUND_SHOP_BY_ID);
        }
        GxsManagementResponseDTO gxsManagementResponseDTO = GeneralConvertUtils.conv(payload.getPayload(),
                GxsManagementResponseDTO.class);
        return gxsManagementResponseDTO;
    }
}
