package com.deepexi.dd.system.mall.service.common.impl;

import com.deepexi.dd.domain.common.domain.dto.CommonAnnouncementSettingsResponseDTO;
import com.deepexi.dd.domain.common.domain.query.CommonAnnouncementSettingsRequestQuery;
import com.deepexi.dd.system.mall.remote.common.AnnouncementSettingsRemote;
import com.deepexi.dd.system.mall.service.common.AnnouncementSettingsService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AnnouncementSettingsServiceImpl implements AnnouncementSettingsService {
    private static final String SUCCESS = "0";
    @Autowired
    AnnouncementSettingsRemote announcementSettingsRemote;

    @Override
    public PageBean<CommonAnnouncementSettingsResponseDTO> listCommonAnnouncementSettingssPage(CommonAnnouncementSettingsRequestQuery query) throws Exception {
        Payload<PageBean<CommonAnnouncementSettingsResponseDTO>> result = announcementSettingsRemote.listCommonAnnouncementSettingssPage(query);
        return GeneralConvertUtils.convert2PageBean(result.getPayload(), CommonAnnouncementSettingsResponseDTO.class);
    }

    @Override
    public CommonAnnouncementSettingsResponseDTO selectById(Long id) throws Exception {
        Payload<CommonAnnouncementSettingsResponseDTO> payload = announcementSettingsRemote.selectById(id);

        if (SUCCESS.equals(payload.getCode())) {
            try {
                CommonAnnouncementSettingsResponseDTO result = GeneralConvertUtils.conv(payload.getPayload(), CommonAnnouncementSettingsResponseDTO.class);
                return result;
            } catch (Exception e) {
                log.error("获取公告详情失败", e);
                throw new ApplicationException("获取公告详情失败");
            }
        } else {
            throw new ApplicationException(payload.getMsg());
        }
    }
}
