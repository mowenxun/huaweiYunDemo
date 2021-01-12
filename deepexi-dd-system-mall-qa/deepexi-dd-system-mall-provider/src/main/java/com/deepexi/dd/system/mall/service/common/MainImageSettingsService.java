package com.deepexi.dd.system.mall.service.common;

import com.deepexi.dd.domain.common.domain.dto.CommonMainImageSettingsResponseDTO;
import com.deepexi.dd.domain.common.domain.query.CommonMainImageSettingsRequestQuery;

import java.util.List;

public interface MainImageSettingsService {
    /**
     * 分页查询主图设置列表
     *
     * @return
     */
    List<CommonMainImageSettingsResponseDTO> listCommonMainImageSettingssPage(CommonMainImageSettingsRequestQuery query) throws Exception;


    /**
     * 查询主图设置详情
     *
     * @param id
     * @return
     */
    CommonMainImageSettingsResponseDTO selectById(Long id) throws Exception;
}
