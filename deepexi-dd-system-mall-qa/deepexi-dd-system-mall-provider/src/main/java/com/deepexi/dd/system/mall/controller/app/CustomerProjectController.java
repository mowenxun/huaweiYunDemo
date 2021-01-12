package com.deepexi.dd.system.mall.controller.app;

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.system.mall.domain.vo.app.ListCustomerProjectsRequestVO;
import com.deepexi.dd.system.mall.domain.vo.app.ListCustomerProjectsResponseVO;
import com.deepexi.dd.system.mall.remote.customer.CustomerProjectClient;
import com.deepexi.dd.system.mall.service.app.CategoryBackService;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import domain.dto.*;
import domain.query.ProjectBrandContendQuery;
import domain.query.ProjectDesignerQuery;
import domain.query.ProjectProgressQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author dengWenShun
 * @version 1.0
 * @date 2020/8/29 20:02
 */
@Api(value = "项目", tags = "项目管理")
@RequestMapping("/admin-api/v1/customerProject")
@RestController
@Slf4j
public class CustomerProjectController {

    @Autowired
    private CustomerProjectClient customerProjectClient;
    @Autowired
    private CategoryBackService categoryBackService;

    @GetMapping("/list")
    @ApiOperation("查询项目列表")
    public Payload<List<ListCustomerProjectsResponseVO>> listCustomerProjects(ListCustomerProjectsRequestVO query) throws Exception {
        Optional.ofNullable(query).orElseThrow(() -> new ApplicationException("query:{}null"));
        log.info("pageQuery:{}", JsonUtil.bean2JsonString(query));
        Payload<List<ListCustomerProjectsResponseDTO>> result = customerProjectClient.listCustomerProjects(query.clone(ListCustomerProjectsRequestDTO.class));
        if (ObjectUtils.isEmpty(result.getPayload())) {
            log.info("resultList:{}项目列表查询结果空");
            return new Payload<>();
        } else {
            List<ListCustomerProjectsResponseDTO> resultList = GeneralConvertUtils.convert2List(result.getPayload(), ListCustomerProjectsResponseDTO.class);
            return new Payload<>(GeneralConvertUtils.convert2List(resultList, ListCustomerProjectsResponseVO.class));
        }
    }

    /**
     * 查询项目列表-分页
     *
     * @param query query
     * @return 项目列表-分页
     */
    @GetMapping("/page")
    @ApiOperation("分页查询项目列表")
    public Payload<PageBean<ListCustomerProjectsResponseVO>> listCustomerProjectsPage(@Valid ListCustomerProjectsRequestVO query) throws Exception {
        Optional.ofNullable(query).orElseThrow(() -> new ApplicationException("query:{}null"));
        log.info("pageQuery:{}", JsonUtil.bean2JsonString(query));
        Payload<PageBean<ListCustomerProjectsResponseDTO>> pageBeanPayload = customerProjectClient.listCustomerProjectsPage(query.clone(ListCustomerProjectsRequestDTO.class));
        if (ObjectUtils.isEmpty(pageBeanPayload.getPayload())) {
            log.info("pageBeanPayload:{}项目列表查询结果空");
            return new Payload<>();
        } else {
            PageBean<ListCustomerProjectsResponseDTO> pageBean = GeneralConvertUtils.convert2PageBean(pageBeanPayload.getPayload(), ListCustomerProjectsResponseDTO.class);
            return new Payload<>(GeneralConvertUtils.convert2PageBean(pageBean, ListCustomerProjectsResponseVO.class));
        }

    }

    /**
     * 查询项目详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "项目详情")
    public Payload<CustomerProjectApiResponse> selectById(@PathVariable(value = "id") Long id) throws Exception {
        Optional.ofNullable(id).orElseThrow(() -> new ApplicationException("id:{}null"));
        log.info("query:{}", JsonUtil.bean2JsonString(id));
        Payload<CustomerProjectApiResponse> result = customerProjectClient.selectById(id);
        try {
            if (ObjectUtils.isEmpty(result.getPayload())) {
                log.info("项目详情查询结果为空");
                return new Payload<>();
            }
            result = categoryBackService.listBackCategory(GeneralConvertUtils.conv(result.getPayload(), CustomerProjectApiResponse.class));
        } catch (Exception e) {
            log.error("组装类目信息失败", e);
            throw new ApplicationException("中台获取类目信息失败");
        }
        return new Payload<>(result.getPayload());
    }

    /**
     * 查询品牌竞争列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @GetMapping("/listProjectBrandContends")
    @ApiOperation(value = "查询品牌竞争列表")
    Payload<List<BrandContendResponseDTO>> listProjectBrandContends(@Valid ProjectBrandContendQuery query) throws Exception {
        Optional.ofNullable(query).orElseThrow(() -> new ApplicationException("query:{}null"));
        log.info("query:{}", JsonUtil.bean2JsonString(query));
        Payload<List<BrandContendResponseDTO>> result = customerProjectClient.listProjectBrandContends(query);
        if (ObjectUtils.isEmpty(result.getPayload())) {
            log.info("品牌竞争查询结果为空");
            return new Payload<>();
        }
        return result;
    }

    /**
     * 查询设计方列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @GetMapping("/listProjectDesigners")
    @ApiOperation(value = "查询设计方列表")
    Payload<ProjectDesignerResDTO> listProjectDesigners(@Valid ProjectDesignerQuery query) throws Exception {
        Optional.ofNullable(query).orElseThrow(() -> new ApplicationException("query:{}null"));
        log.info("query:{}", JsonUtil.bean2JsonString(query));
        Payload<ProjectDesignerResDTO> result = customerProjectClient.listProjectDesigners(query);
        if (ObjectUtils.isEmpty(result.getPayload())) {
            log.info("设计方查询结果为空");
            return new Payload<>();
        }
        return result;
    }

    /**
     * 查询项目进度列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @GetMapping("/listProjectProgresss")
    @ApiOperation(value = "查询项目进度列表")
    Payload<List<ProjectProgressResDTO>> listProjectProgresss(@Valid ProjectProgressQuery query) throws Exception {
        Optional.ofNullable(query).orElseThrow(() -> new ApplicationException("query:{}null"));
        log.info("query:{}", JsonUtil.bean2JsonString(query));
        Payload<List<ProjectProgressResDTO>> result = customerProjectClient.listProjectProgresss(query);
        if (ObjectUtils.isEmpty(result.getPayload())) {
            log.info("项目进度查询结果为空");
            return new Payload<>();
        }
        return result;
    }
}

