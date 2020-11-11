package com.deepexi.dd.middle.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.dao.OrderConsigneeInfoDAO;
import com.deepexi.dd.middle.order.domain.OrderConsigneeInfoDO;
import com.deepexi.dd.middle.order.domain.dto.OrderConsigneeInfoDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderConsigneeInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderConsigneeInfoQuery;
import com.deepexi.dd.middle.order.service.OrderConsigneeInfoService;
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
 * OrderConsigneeInfoServiceImpl
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class OrderConsigneeInfoServiceImpl implements OrderConsigneeInfoService {

    @Autowired
    private OrderConsigneeInfoDAO orderConsigneeInfoDao;

    @Override
    public List<OrderConsigneeInfoDTO> listOrderConsigneeInfos(OrderConsigneeInfoQuery query) {
        return ObjectCloneUtils.convertList(orderConsigneeInfoDao.listOrderConsigneeInfos(query), OrderConsigneeInfoDTO.class);
    }


    @Override
    public PageBean<OrderConsigneeInfoDTO> listOrderConsigneeInfosPage(OrderConsigneeInfoQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(orderConsigneeInfoDao.listOrderConsigneeInfos(query)), OrderConsigneeInfoDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除收货地址信息表,传入ID为空");
            return false;
        }
        return orderConsigneeInfoDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public OrderConsigneeInfoDTO insert(OrderConsigneeInfoDTO record) {
        if (record == null) {
            log.error("新增收货地址信息表,传入参数为空");
            return null;
        }
        OrderConsigneeInfoDO orderConsigneeInfoDo = record.clone(OrderConsigneeInfoDO.class);
        DomainUtils.initDomainCreatedInfo(orderConsigneeInfoDo);
        if (orderConsigneeInfoDao.insert(orderConsigneeInfoDo) > 0) {
            record.setId(orderConsigneeInfoDo.getId());
            return record;
        }
        log.error("新增收货地址信息表失败");
        return null;
    }

    @Override
    public OrderConsigneeInfoDTO selectById(Long id) {
        if (id == null) {
            log.error("查询收货地址信息表,传入ID为空");
            return null;
        }
        OrderConsigneeInfoDO orderConsigneeInfoDo = orderConsigneeInfoDao.selectById(id);
        if (orderConsigneeInfoDo == null) {
            return null;
        }
        return orderConsigneeInfoDo.clone(OrderConsigneeInfoDTO.class);
    }

    @Override
    public Boolean updateById(Long id, OrderConsigneeInfoDTO record) {
        if (id == null) {
            log.error("修改收货地址信息表,传入ID为空");
            return false;
        }
        record.setId(id);
        OrderConsigneeInfoDO orderConsigneeInfoDo = record.clone(OrderConsigneeInfoDO.class);
        DomainUtils.initUpdateTimeInfo(orderConsigneeInfoDo);
        return orderConsigneeInfoDao.updateById(orderConsigneeInfoDo);
    }

    /**
     * @param idList
     * @Description: 根据订单ID查询收货信息，用于出库单列表查询.
     * @Param: [idList]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.OrderDeliveryInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/6
     */
    @Override
    public List<OrderConsigneeInfoResponseDTO> searchOrderConsigneeInfoById(List<Long> idList) {
        List<OrderConsigneeInfoResponseDTO> list = Lists.newArrayList();
        if (Utils.isEmpty(idList)){
            log.error("查询出库单收货信息，传入ID为空");
            return list;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("sale_order_id",idList);
        queryWrapper.eq("is_deleted",0);
        list = orderConsigneeInfoDao.list(queryWrapper);
        return ObjectCloneUtils.convertList(list, OrderConsigneeInfoResponseDTO.class);
    }

    /**
     * @param key
     * @param value
     * @Description: 公共接口 根据字段 拿到拼接后的发货信息.
     * @Param: [key, value]
     * @return: java.lang.String
     * @Author: SongTao
     * @Date: 2020/7/8
     */
    @Override
    public String splicingShippingAddress(Object key,Object value) {
        String str ="";
        if (Objects.isNull(key)||Objects.isNull(value)){
            log.error("拼接物流信息,传入参数为空");
            return str;
        }
        List<OrderConsigneeInfoResponseDTO> list = searchConsigneeInfo(key, value);
        if (Utils.isNotEmpty(list)){//不管是通过主键ID还是订单ID 如是有数据 则一定是一一对应
            OrderConsigneeInfoResponseDTO dto = Utils.selectOne(list);
            StringBuilder builder = new StringBuilder();
            builder.append(dto.getProvinceName());//省份名称
            builder.append(dto.getCityName());//城市名称
            builder.append(dto.getAreaName());//区域名称
            builder.append(dto.getStreetName());//街道名称
            builder.append(dto.getDetailedAddress());//详细地址
            str = builder.toString();
        }
        return str;
    }

    /**
     * @param field
     * @param value
     * @Description: 根据字段和值 查对应的收货信息.
     * @Param: [field, value]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.OrderConsigneeInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/8
     */
    @Override
    public List<OrderConsigneeInfoResponseDTO> searchConsigneeInfo(Object field, Object value) {
        if (Objects.isNull(field)||Objects.isNull(value)){
            log.error("查询物流收货信息,传入参数为空");
            return Lists.newArrayList();
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(field,value);
        queryWrapper.eq("is_deleted",0);
        List<OrderConsigneeInfoDO> orderConsigneeInfoDOList = orderConsigneeInfoDao.list(queryWrapper);
        return ObjectCloneUtils.convertList(orderConsigneeInfoDOList,OrderConsigneeInfoResponseDTO.class);
    }


}