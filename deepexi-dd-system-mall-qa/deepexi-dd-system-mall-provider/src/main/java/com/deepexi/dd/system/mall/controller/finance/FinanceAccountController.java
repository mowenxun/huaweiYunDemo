package com.deepexi.dd.system.mall.controller.finance;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deepexi.dd.middle.finance.domain.dto.FinanceAmountDetailResponseDTO;
import com.deepexi.dd.middle.finance.domain.dto.FinanceAmountResponseDTO;
import com.deepexi.dd.middle.finance.domain.dto.FinanceCreditDetailResponseDTO;
import com.deepexi.dd.middle.finance.domain.query.FinanceAmountDetailRequestQuery;
import com.deepexi.dd.middle.finance.domain.query.FinanceAmountRequestQuery;
import com.deepexi.dd.middle.finance.domain.query.FinanceCreditDetailRequestQuery;
import com.deepexi.dd.system.mall.domain.dto.financeaccount.OrgInfoConvertDTO;
import com.deepexi.dd.system.mall.domain.dto.financeaccount.OrgInfoRequestQuery;
import com.deepexi.dd.system.mall.service.finance.FinanceAccountService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author easy
 * @date 2020/8/28 16:19
 **/
@Api(value = "我的资金", tags = "我的资金")
@RequestMapping("/admin-api/v1/domain/finance/financeAccount")
@RestController
public class FinanceAccountController {

    @Autowired
    private FinanceAccountService financeAccountService;

    /**
     * 查询我的资金账户
     *
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation("查询我的资金账户")
    @GetMapping("/account")
    Payload<FinanceAmountResponseDTO> selectMyFinanceAccount(FinanceAmountRequestQuery query) throws Exception {
        return financeAccountService.selectMyFinanceAccount(query);
    }

    /**
     * 获取当前登录用户的所有供应商
     *
     * @return
     * @throws Exception
     */
    @ApiOperation("获取当前登录用户的所有供应商")
    @GetMapping("/getOrgInfo")
    Payload<List<OrgInfoConvertDTO>> getOrgInfo(OrgInfoRequestQuery orgInfoRequestQuery) throws Exception {
        List<Map<String, String>> list = financeAccountService.listBusinessPartner(orgInfoRequestQuery.getPartnerId()).getPayload();
        List<OrgInfoConvertDTO> orgInfoList = new ArrayList<>();
        OrgInfoConvertDTO orgInfoConvertDTO = null;
        FinanceAmountResponseDTO financeAmountResponseDTO = null;
        for(Map<String, String> map:list){
            orgInfoConvertDTO = new OrgInfoConvertDTO();
            orgInfoConvertDTO.setOrgId(map.get("orgId"));
            orgInfoConvertDTO.setName(map.get("name"));
            orgInfoList.add(orgInfoConvertDTO);
        }

        FinanceAmountRequestQuery query = new FinanceAmountRequestQuery();
        query.setTenantId(orgInfoRequestQuery.getTenantId());
        query.setAppId(orgInfoRequestQuery.getAppId());
        query.setRelationId(orgInfoRequestQuery.getPartnerId());
        for(OrgInfoConvertDTO dto:orgInfoList){
            query.setIsolationId(dto.getOrgId());
            financeAmountResponseDTO = GeneralConvertUtils.conv(financeAccountService.selectMyFinanceAccount(query).getPayload(),FinanceAmountResponseDTO.class);
            dto.setFinanceAccountInfo(financeAmountResponseDTO);
        }
        return new Payload<>(orgInfoList);
    }

    /**
     * 获取当前登录用户的所有供应商
     *
     * @param partnerId
     * @return
     * @throws Exception
     */
    @ApiOperation("获取当前登录用户的所有供应商")
    @GetMapping("/listBusinessPartner/{partnerId}")
    Payload<List<Map<String, String>>> listBusinessPartner(@PathVariable Long partnerId) throws Exception {
        return financeAccountService.listBusinessPartner(partnerId);
    }

    /**
     * 分页余额明细查询
     *
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation("分页余额明细查询")
    @GetMapping("/financeAmountDetail")
    Payload<PageBean<FinanceAmountDetailResponseDTO>> listFinanceAmountDetailPage(FinanceAmountDetailRequestQuery query) throws Exception {
        return financeAccountService.listFinanceAmountDetailPage(query);
    }

    /**
     * 分页信用明细查询
     *
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation("分页信用明细查询")
    @GetMapping("/financeCreditDetail")
    Payload<PageBean<FinanceCreditDetailResponseDTO>> listFinanceCreditDetailPage(FinanceCreditDetailRequestQuery query) throws Exception {
        return financeAccountService.listFinanceCreditDetailPage(query);
    }
}
