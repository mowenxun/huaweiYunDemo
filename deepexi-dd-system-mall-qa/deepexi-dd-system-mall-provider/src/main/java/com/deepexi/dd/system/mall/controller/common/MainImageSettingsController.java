package com.deepexi.dd.system.mall.controller.common;

import com.alibaba.fastjson.JSON;
import com.deepexi.dd.domain.common.domain.dto.CommonMainImageSettingsResponseDTO;
import com.deepexi.dd.domain.common.domain.query.CommonMainImageSettingsRequestQuery;
import com.deepexi.dd.system.mall.domain.dto.CommonMainImageSettingsAppResponseDTO;
import com.deepexi.dd.system.mall.domain.query.MainImageSettingsAppRequestQuery;
import com.deepexi.dd.system.mall.service.common.MainImageSettingsService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 广告设置管理
 */
@Api(tags = "广告设置管理")
@RestController
@RequestMapping("/admin-api/v1/common/commonMainImageSettings")
@Slf4j
public class MainImageSettingsController {
    @Autowired
    private MainImageSettingsService mainImageSettingsService;

    /**
     * 分页查询广告列表
     *
     * @param query
     * @return
     */
    @ApiOperation("查询广告列表")
    @GetMapping("/list")
    Payload<List<CommonMainImageSettingsAppResponseDTO>> listCommonMainImageSettingssP(@Valid MainImageSettingsAppRequestQuery query) {
        CommonMainImageSettingsRequestQuery model = query.clone(CommonMainImageSettingsRequestQuery.class);
        try {
            log.info("listCommonMainImageSettingssP-1");
            List<CommonMainImageSettingsResponseDTO> result = mainImageSettingsService.listCommonMainImageSettingssPage(model);
            log.info("mainImageSettingsService.listCommonMainImageSettingssPage-result:"+ JSON.toJSONString(result));
            return new Payload<>(GeneralConvertUtils.convert2List(result, CommonMainImageSettingsAppResponseDTO.class));
        } catch (Exception e) {
            return new Payload<>(null, "500", e.getMessage());
        }

    }

    /**
     * 根据ID查询主图设置
     *
     * @param id
     * @return
     */
    @ApiOperation("根据ID查询主图设置")
    @GetMapping("/{id}")
    public Payload<CommonMainImageSettingsAppResponseDTO> selectById(@PathVariable("id") Long id) {
        try {
            CommonMainImageSettingsAppResponseDTO saleOrderDetailAppResponseDTO = GeneralConvertUtils.conv(mainImageSettingsService.selectById(id), CommonMainImageSettingsAppResponseDTO.class);
            return new Payload<>(saleOrderDetailAppResponseDTO);
        } catch (ApplicationException e) {
            return new Payload<>(null, e.getCode(), e.getMessage());
        } catch (Exception e) {
            return new Payload<>(null, "500", e.getMessage());
        }
    }

}
