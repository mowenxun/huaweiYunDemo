package com.deepexi.dd.system.mall.controller.common;

import com.deepexi.dd.domain.common.domain.dto.CommonAnnouncementSettingsResponseDTO;
import com.deepexi.dd.domain.common.domain.query.CommonAnnouncementSettingsRequestQuery;
import com.deepexi.dd.system.mall.domain.dto.CommonAnnouncementSettingsAppResponseDTO;
import com.deepexi.dd.system.mall.domain.query.AnnouncementSettingsAppRequestQuery;
import com.deepexi.dd.system.mall.service.common.AnnouncementSettingsService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = "公告设置管理")
@RequestMapping("/admin-api/v1/common/commonAnnouncementSettings")
public class AnnouncementSettingsController {
    @Autowired
    private AnnouncementSettingsService announcementSettingsService;

    /**
     * 分页查询公告列表
     *
     * @return
     */
    @ApiOperation(value = "分页查询公告列表")
    @GetMapping("/page")
    Payload<PageBean<CommonAnnouncementSettingsAppResponseDTO>> listCommonAnnouncementSettingssPage(@Valid AnnouncementSettingsAppRequestQuery query) {
        CommonAnnouncementSettingsRequestQuery model = query.clone(CommonAnnouncementSettingsRequestQuery.class);

        try {
            PageBean<CommonAnnouncementSettingsResponseDTO> result = announcementSettingsService.listCommonAnnouncementSettingssPage(model);
            return new Payload<>(GeneralConvertUtils.convert2PageBean(result, CommonAnnouncementSettingsAppResponseDTO.class));
        } catch (Exception e) {
            return new Payload<>(null, "500", e.getMessage());
        }
    }

    /**
     * 查询公告设置详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询公告设置详情")
    @GetMapping("/{id}")
    Payload<CommonAnnouncementSettingsAppResponseDTO> selectById(@PathVariable("id") Long id) {
        try {
            CommonAnnouncementSettingsAppResponseDTO announcementSettingsAppResponseDTO = GeneralConvertUtils.conv(announcementSettingsService.selectById(id), CommonAnnouncementSettingsAppResponseDTO.class);
            return new Payload<>(announcementSettingsAppResponseDTO);
        } catch (ApplicationException e) {
            return new Payload<>(null, e.getCode(), e.getMessage());
        } catch (Exception e) {
            return new Payload<>(null, "500", e.getMessage());
        }
    }
}
