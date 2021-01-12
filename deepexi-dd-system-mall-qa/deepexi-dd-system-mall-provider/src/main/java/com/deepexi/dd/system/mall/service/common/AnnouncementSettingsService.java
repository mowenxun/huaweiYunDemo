package com.deepexi.dd.system.mall.service.common;

import com.deepexi.dd.domain.common.domain.dto.CommonAnnouncementSettingsDTO;
import com.deepexi.dd.domain.common.domain.dto.CommonAnnouncementSettingsResponseDTO;
import com.deepexi.dd.domain.common.domain.query.CommonAnnouncementSettingsRequestQuery;
import com.deepexi.util.pageHelper.PageBean;

public interface AnnouncementSettingsService {
    /**
     * 分页查询公告设置列表
     *
     * @return
     */
    PageBean<CommonAnnouncementSettingsResponseDTO> listCommonAnnouncementSettingssPage(CommonAnnouncementSettingsRequestQuery query) throws Exception;

    /**
     * 查询公告设置详情
     *
     * @param id
     * @return
     */
    CommonAnnouncementSettingsResponseDTO selectById(Long id) throws Exception;
}
