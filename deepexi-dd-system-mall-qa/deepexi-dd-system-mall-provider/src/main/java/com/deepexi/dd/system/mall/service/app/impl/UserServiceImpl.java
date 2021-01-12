package com.deepexi.dd.system.mall.service.app.impl;

import com.deepexi.clientiam.api.AccountControllerApi;
import com.deepexi.clientiam.api.UserControllerApi;
import com.deepexi.clientiam.model.GetUserResultVO;
import com.deepexi.clientiam.model.GysUserVO;
import com.deepexi.clientiam.model.PageBeanGysUserVO;
import com.deepexi.clientiam.model.PayloadGetUserResultVO;
import com.deepexi.clientiam.model.PayloadPageBeanGysUserVO;
import com.deepexi.clientiam.model.PayloadUserRegisterResultVO;
import com.deepexi.clientiam.model.Payloadboolean;
import com.deepexi.clientiam.model.UpdateAccountBasicByIdParamVO;
import com.deepexi.clientiam.model.UpdateAccountPasswordParamVO;
import com.deepexi.clientiam.model.UserRegisterParamVO;
import com.deepexi.clientiam.model.UserRegisterResultVO;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.finance.domain.query.FinanceAmountAdminRequestQuery;
import com.deepexi.dd.middle.finance.domain.dto.FinanceAmountResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.User.CurrentUserInfoIncludeOrgResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.User.GysUserDTO;
import com.deepexi.dd.system.mall.domain.dto.User.UserInfoUpdateDTO;
import com.deepexi.dd.system.mall.domain.dto.User.UserPasswordUpdateDTO;
import com.deepexi.dd.system.mall.domain.dto.User.UserRegisterDTO;
import com.deepexi.dd.system.mall.domain.dto.User.UserRegisterResultDTO;
import com.deepexi.dd.system.mall.domain.query.user.UserQuery;
import com.deepexi.dd.system.mall.remote.customer.BusinessPartnerClient;
import com.deepexi.dd.system.mall.remote.customer.CompanyInfoClient;
import com.deepexi.dd.system.mall.remote.finance.FinanceAmountRemote;
import com.deepexi.dd.system.mall.service.app.UserService;
import com.deepexi.dd.system.mall.service.customer.BaseService;
import com.deepexi.dd.system.mall.service.customer.BusinessPartnerService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.dd.system.mall.util.PayloadUtils;
import com.deepexi.dd.system.mall.util.RSAUtil;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.StringUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import domain.dto.BusinessPartnerResponseDTO;
import domain.dto.CompanyInfoResponseApiDTO;
import domain.query.BusinessPartnerRequestQuery;
import domain.query.CompanyInfoRequestApiQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserControllerApi userControllerApi;

    @Autowired
    private AccountControllerApi accountControllerApi;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private BusinessPartnerService businessPartnerService;

    @Autowired
    private FinanceAmountRemote financeAmountRemote;

    @Autowired
    private BusinessPartnerClient partnerClient;

    @Value("${deepexi.login.rsa.privateKey}")
    private String loginPrivateKey;

    @Autowired
    private CompanyInfoClient companyInfoClient;

    @Override
    public GetUserResultVO currentUserInfo(Long userId) {
        PayloadGetUserResultVO user = userControllerApi.getUserById(userId, appRuntimeEnv.getTenantId(), userId, appRuntimeEnv.getUsername());
        PayloadUtils.assertSuccess(user);
        log.info("iam返回结果，user = {}", JsonUtil.bean2JsonString(user.getPayload()));
        return user.getPayload();
    }

    @Override
    public Boolean update(UserInfoUpdateDTO dto) {
        PayloadGetUserResultVO user = userControllerApi.getUserById(dto.getId(), appRuntimeEnv.getTenantId(), appRuntimeEnv.getUserId(), appRuntimeEnv.getUsername());
        PayloadUtils.assertSuccess(user);
        Long accountId = user.getPayload().getAccountId();
        Payloadboolean payloadboolean = accountControllerApi.updateAccountBasicById(accountId, dto.clone(UpdateAccountBasicByIdParamVO.class), appRuntimeEnv.getTenantId(),
                appRuntimeEnv.getUserId(), appRuntimeEnv.getUsername());
        PayloadUtils.assertSuccess(payloadboolean);
        return payloadboolean.getPayload();
    }

    @Override
    public CurrentUserInfoIncludeOrgResponseDTO currentUserInfoIncludeOrg(Long userId) throws Exception {
        GetUserResultVO user = currentUserInfo(userId);
        if (user == null) {
            return null;
        }
        BusinessPartnerRequestQuery query = new BusinessPartnerRequestQuery();
        query.setTenantId(appRuntimeEnv.getTenantId());
        query.setOrgId(appRuntimeEnv.getTopOrganization().getId());
        Payload<BusinessPartnerResponseDTO> payload = partnerClient.getPartner(query);
        BusinessPartnerResponseDTO partner = GeneralConvertUtils.conv(payload.getPayload(), BusinessPartnerResponseDTO.class);

        CurrentUserInfoIncludeOrgResponseDTO result = user.clone(CurrentUserInfoIncludeOrgResponseDTO.class);
        if (partner != null) {
            result.setPartnerName(partner.getName());
            result.setPartnerCode(partner.getCode());
            result.setPartnerId(partner.getId());
            result.setIsolationId(partner.getIsolationId());

            FinanceAmountAdminRequestQuery financeAmountAdminRequestQuery = new FinanceAmountAdminRequestQuery();
            financeAmountAdminRequestQuery.setTenantId(appRuntimeEnv.getTenantId());
            financeAmountAdminRequestQuery.setRelationId(partner.getId());
            Payload<FinanceAmountResponseDTO> financeAmount = financeAmountRemote.getFinanceAmount(financeAmountAdminRequestQuery);
            FinanceAmountResponseDTO financeAmountResponseDTO = GeneralConvertUtils.conv(financeAmount.getPayload(), FinanceAmountResponseDTO.class);
            if (Objects.nonNull(financeAmountResponseDTO)) {
                result.setAmount(financeAmountResponseDTO.getAmount());
                result.setCreditLimit(financeAmountResponseDTO.getCreditLimit());
            }
        }
        return result;
    }

    @Override
    public Boolean updateUserPassword(UserPasswordUpdateDTO dto) {
        String tenantId = appRuntimeEnv.getTenantId();
        Long userId = appRuntimeEnv.getUserId();
        String username = appRuntimeEnv.getUsername();
        PayloadGetUserResultVO user = userControllerApi.getUserById(appRuntimeEnv.getUserId(), appRuntimeEnv.getTenantId(), appRuntimeEnv.getUserId(), appRuntimeEnv.getUsername());
        if (Objects.isNull(user)) {
            log.info("updateUserPassword：未查询到用户数据");
            return false;
        }
        Long accountId = user.getPayload().getAccountId();
        UpdateAccountPasswordParamVO updateAccountPasswordParamVO = new UpdateAccountPasswordParamVO();
        updateAccountPasswordParamVO.setAccountId(accountId);
        updateAccountPasswordParamVO.setOldPassword(dto.getOldPassword());
        updateAccountPasswordParamVO.setNewPassword(dto.getNewPassword());
        Payloadboolean payloadboolean = accountControllerApi.updateAccountPassword(updateAccountPasswordParamVO,tenantId, userId, username);
        PayloadUtils.assertSuccess(payloadboolean);
        return payloadboolean.getPayload();
    }

    /**
     * @Description: 修改密码-加密.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/23
     */
    @Override
    public Boolean updatePwd(UserPasswordUpdateDTO updateDTO) throws Exception {
        // 对密码解密.

//        updateDTO.setNewPassword(Base64.decodeString(updateDTO.getNewPassword()));
//        updateDTO.setOldPassword(Base64.decodeString(updateDTO.getOldPassword()));

        updateDTO.setNewPassword(getDecryptPassword(updateDTO.getNewPassword()));
        updateDTO.setOldPassword(getDecryptPassword(updateDTO.getOldPassword()));

        return this.updateUserPassword(updateDTO);
    }

    @Override
    public UserRegisterResultDTO register(UserRegisterDTO dto) throws Exception {
        // 校验唯一性
        checkCompany(dto);

        // 保存账号到iam
        UserRegisterParamVO clone = dto.clone(UserRegisterParamVO.class);
        PayloadUserRegisterResultVO payloadUserRegisterResultVO = accountControllerApi.userRegister(clone);
        log.info("注册返回结果:", payloadUserRegisterResultVO);
        if (payloadUserRegisterResultVO != null && "0".equals(payloadUserRegisterResultVO.getCode()) && payloadUserRegisterResultVO.getPayload() != null) {
            UserRegisterResultVO result = payloadUserRegisterResultVO.getPayload();
            // 保存企业信息
            CompanyInfoResponseApiDTO company = new CompanyInfoResponseApiDTO();
            company.setCompanyName(dto.getEnterpriseName());
            company.setCreditCode(dto.getCreditCode());
            company.setCompanyId(result.getGroupId());
            company.setTenantId(dto.getTenantId());
            // 企业信息设置为3，表示设置为草稿状态
            company.setStatus(3);
            companyInfoClient.insert(company);

            return result.clone(UserRegisterResultDTO.class);
        }
        if (payloadUserRegisterResultVO != null && !"0".equals(payloadUserRegisterResultVO.getCode())) {

            if (StringUtil.isNotEmpty(payloadUserRegisterResultVO.getMsg())) {
                throw new ApplicationException(payloadUserRegisterResultVO.getMsg());
            }
        }
        throw new ApplicationException("注册失败");
    }

    @Override
    public PageBean<GysUserDTO> getGysUserPage(UserQuery query) throws Exception {
        // 设置只查询供应商管理员
        query.setAdmin(1);
        // 先分页查询账号
        PayloadPageBeanGysUserVO payloadResult = userControllerApi.getGysUserList(query.getTenantId(), appRuntimeEnv.getUserId(), appRuntimeEnv.getUsername(),
                query.getAdmin(), query.getAppId(), query.getCheckStatus(), null, null, null, query.getExtend3(),
                null, null, null, null, null, null, null, null, null,
                query.getPage(), null, query.getSize(), query.getStatus(), null, null);
        if (payloadResult == null || payloadResult.getPayload() == null) {
            return null;
        }

        PageBeanGysUserVO payload = payloadResult.getPayload();
        PageBean<GysUserDTO> pageBean = new PageBean<>();
        pageBean.setTotalPages(payload.getTotalPages());
        pageBean.setNumberOfElements(payload.getNumberOfElements());
        pageBean.setNumber(payload.getNumber());
        pageBean.setTotalElements(payload.getTotalElements());
        pageBean.setSize(payload.getSize());

        List<GysUserVO> userList = payload.getContent();
        if (CollectionUtil.isEmpty(userList)) {
            pageBean.setContent(new ArrayList<>());
        }
        List<GysUserDTO> gysUsers = ObjectCloneUtils.convertList(userList, GysUserDTO.class);
        pageBean.setContent(gysUsers);
        Map<Long, GysUserDTO> userMap = gysUsers.stream().collect(Collectors.toMap(GysUserDTO::getGroupId, Function.identity(), (v1, v2) -> v1));
        List<Long> groupIdList = gysUsers.stream().map(GysUserDTO::getGroupId).filter(groupId -> groupId != null).collect(Collectors.toList());

        if (CollectionUtil.isNotEmpty(groupIdList)) {
            // 开始查询供应商对应的企业信息,并封装到供应商属性中
            Payload<List<CompanyInfoResponseApiDTO>> payloadCompany = companyInfoClient.listCompanyInfosByCompanyIds(groupIdList);
            PayloadUtils.assertSuccess(payloadCompany);
            if (CollectionUtil.isNotEmpty(payloadCompany.getPayload())) {
                List<CompanyInfoResponseApiDTO> companyInfoResponseApiDTOS = GeneralConvertUtils.convert2List(payloadCompany.getPayload(), CompanyInfoResponseApiDTO.class);
                companyInfoResponseApiDTOS.forEach(company -> {
                    GysUserDTO gysUserDTO = userMap.get(company.getCompanyId());
                    if (gysUserDTO != null) {
                        gysUserDTO.setEnterpriseName(company.getCompanyName());
                        gysUserDTO.setNickname(company.getContactPerson());
                        gysUserDTO.setPhone(company.getContactPhone());
                    }
                });
            }
        }

        return pageBean;
    }

    private void checkCompany(UserRegisterDTO dto) throws Exception {
        if (StringUtil.isEmpty(dto.getEnterpriseName())) {
            throw new ApplicationException("企业名称不能为空");
        }
        if (StringUtil.isEmpty(dto.getCreditCode())) {
            throw new ApplicationException("统一社会信用代码不能为空");
        }
        CompanyInfoRequestApiQuery query = new CompanyInfoRequestApiQuery();
        query.setCompanyName(dto.getEnterpriseName());
        // 按名称查询
        Payload<List<CompanyInfoResponseApiDTO>> payloadResult = companyInfoClient.listCompanyInfos(query);
        PayloadUtils.assertSuccess(payloadResult);
        if (payloadResult != null && CollectionUtil.isNotEmpty(payloadResult.getPayload())) {
            throw new ApplicationException("企业名称已经存在");
        }

        // 按名称查询
        query.setCompanyName(null);
        query.setCreditCode(dto.getCreditCode());
        Payload<List<CompanyInfoResponseApiDTO>> payloadCompany = companyInfoClient.listCompanyInfos(query);
        PayloadUtils.assertSuccess(payloadCompany);
        if (payloadCompany != null && CollectionUtil.isNotEmpty(payloadCompany.getPayload())) {
            throw new ApplicationException("统一社会信用代码已经存在");
        }
    }

    private String getDecryptPassword(String password) throws Exception {
        try {
            byte[] decryptData = RSAUtil.decryptByPrivateKey(password, loginPrivateKey);
            String newPassword = new String(decryptData);

            return newPassword;
        }
        catch (Exception e){
            throw new Exception("不是有效的密码");
        }
    }
}
