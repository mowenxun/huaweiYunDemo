package com.deepexi.dd.middle.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.dao.SaleOutTaskDetailInfoDAO;
import com.deepexi.dd.middle.order.domain.SaleOutTaskDetailInfoDO;
import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskDetailInfoDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskDetailInfoMiddleRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskDetailInfoMiddleResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOutTaskDetailInfoQuery;
import com.deepexi.dd.middle.order.service.SaleOutTaskDetailInfoService;
import com.deepexi.dd.middle.order.util.DomainUtils;
import com.deepexi.dd.middle.order.util.Utils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.page.PageMethod;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName SaleOutTaskDetailInfoServiceImpl
 * @Description 销售出库商品明细业务
 * @Author SongTao
 * @Date 2020-07-07
 * @Version 1.0
 **/
@Service
@Slf4j
public class SaleOutTaskDetailInfoServiceImpl implements SaleOutTaskDetailInfoService {

    @Autowired
    private SaleOutTaskDetailInfoDAO saleOutTaskDetailInfoDAO;



    /**
     * @Description:  出库单新增时 新增关联的商品信息..
     * @Param: [dto]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/7
     */
    @Override
    public Boolean insert(List<SaleOutTaskDetailInfoMiddleRequestDTO> list) {
        if (Utils.isEmpty(list)){
            log.error("新增销售出库商品信息,传入参数为空");
            return null;
        }
        List<SaleOutTaskDetailInfoDO> saleOutTaskDetailInfoDOList = ObjectCloneUtils.convertList(list, SaleOutTaskDetailInfoDO.class);
        return saleOutTaskDetailInfoDAO.saveBatch(saleOutTaskDetailInfoDOList) ;
    }

    /**
     * @Description: 根据动态字段查出库单关联的商品信息.
     * @Param: [field, value]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SaleOutTaskDetailInfoMiddleResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/10
     */
    public List<SaleOutTaskDetailInfoMiddleResponseDTO> searchSaleOutTaskDetailInfo(Object field, Object value) {
        if (Objects.isNull(field)||Objects.isNull(value)){
            log.error("查询出库单关联的商品信息,传入参数为空");
            return Lists.newArrayList();
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(field,value);
        queryWrapper.eq("is_deleted",0);
        List<SaleOutTaskDetailInfoDO> list = saleOutTaskDetailInfoDAO.list(queryWrapper);
        return ObjectCloneUtils.convertList(list, SaleOutTaskDetailInfoMiddleResponseDTO.class);
    }


    @Override
    public List<SaleOutTaskDetailInfoDTO> listSaleOutTaskDetailInfos(SaleOutTaskDetailInfoQuery query) {
        return ObjectCloneUtils.convertList(saleOutTaskDetailInfoDAO.listSaleOutTaskDetailInfos(query), SaleOutTaskDetailInfoDTO.class);
    }



    @Override
    public PageBean<SaleOutTaskDetailInfoDTO> listSaleOutTaskDetailInfosPage(SaleOutTaskDetailInfoQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(saleOutTaskDetailInfoDAO.listSaleOutTaskDetailInfos(query)), SaleOutTaskDetailInfoDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除销售出库商品明细表,传入ID为空");
            return false;
        }
        return saleOutTaskDetailInfoDAO.deleteByIdIn(id) == id.size();
    }

    @Override
    public SaleOutTaskDetailInfoDTO insert(SaleOutTaskDetailInfoDTO record) {
        if (record == null) {
            log.error("新增销售出库商品明细表,传入参数为空");
            return null;
        }
        SaleOutTaskDetailInfoDO saleOutTaskDetailInfoDo = record.clone(SaleOutTaskDetailInfoDO.class);
        DomainUtils.initDomainCreatedInfo(saleOutTaskDetailInfoDo);
        if (saleOutTaskDetailInfoDAO.insert(saleOutTaskDetailInfoDo) > 0) {
            record.setId(saleOutTaskDetailInfoDo.getId());
            return record;
        }
        log.error("新增销售出库商品明细表失败");
        return null;
    }

    @Override
    public SaleOutTaskDetailInfoDTO selectById(Long id) {
        if (id == null) {
            log.error("查询销售出库商品明细表,传入ID为空");
            return null;
        }
        SaleOutTaskDetailInfoDO saleOutTaskDetailInfoDo = saleOutTaskDetailInfoDAO.selectById(id);
        if (saleOutTaskDetailInfoDo == null) {
            return null;
        }
        return saleOutTaskDetailInfoDo.clone(SaleOutTaskDetailInfoDTO.class);
    }

    @Override
    public Boolean updateById(Long id, SaleOutTaskDetailInfoDTO record) {
        if (id == null) {
            log.error("修改销售出库商品明细表,传入ID为空");
            return false;
        }
        record.setId(id);
        SaleOutTaskDetailInfoDO saleOutTaskDetailInfoDo = record.clone(SaleOutTaskDetailInfoDO.class);
        DomainUtils.initUpdateTimeInfo(saleOutTaskDetailInfoDo);
        return saleOutTaskDetailInfoDAO.updateById(saleOutTaskDetailInfoDo);
    }
}
