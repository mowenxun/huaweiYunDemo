package com.deepexi.dd.system.mall.service.customer;

import com.deepexi.clientiam.model.GroupResultVO;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.util.extension.ApplicationException;
import domain.dto.BaseRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ClassName BaseService
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-16
 * @Version 1.0
 **/
@Service
public class BaseService {

    @Autowired
    protected AppRuntimeEnv appRuntimeEnv;

    /**
     * @Description: 数据隔离id.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/7/14
     */
    protected String getIsolationId() throws Exception {
        GroupResultVO resultVO = appRuntimeEnv.getTopOrganization();
        if (Objects.nonNull(resultVO)) {
            return String.valueOf(resultVO.getId());
        } else {
            throw new ApplicationException("获取数据隔离id失败.");
        }
    }

    /**
     * @Description: 获取当前用户的组织id.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/7/15
     */
    protected String getCurrenOrgId() throws Exception {
        GroupResultVO resultVO = appRuntimeEnv.getUserOrganization();
        if (Objects.isNull(resultVO)) {
            throw new ApplicationException("当前用户没有归属组织,请绑定组织之后再来操作.");
        }
        return String.valueOf(resultVO.getId());
    }

    /**
    * @Description: 初始化创建时创建人跟修改人.
    * @Param: 
    * @return: 
    * @Author: JiangHaoWei
    * @Date: 2020/7/16
    */
    protected void initCreate(BaseRequestDTO requestDTO) throws Exception {
        requestDTO.setCreatedBy(String.valueOf(appRuntimeEnv.getUserId()));
        requestDTO.setUpdatedBy(String.valueOf(appRuntimeEnv.getUserId()));
        requestDTO.setIsolationId(getIsolationId());
    }

    /**
    * @Description: 初始化修改时修改人.
    * @Param:
    * @return: 
    * @Author: JiangHaoWei
    * @Date: 2020/7/16
    */
    protected void initModify(BaseRequestDTO requestDTO) {
        requestDTO.setUpdatedBy(String.valueOf(appRuntimeEnv.getUserId()));
    }
}
