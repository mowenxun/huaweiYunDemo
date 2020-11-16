package com.deepexi.dd.domain.transaction.manager;

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.tool.domain.dto.ToolLinkListResponseDTO;
import com.deepexi.dd.domain.tool.domain.dto.ToolLinkNextBusinessResponseDTO;
import com.deepexi.dd.domain.tool.domain.query.ToolLinkListRequestQuery;
import com.deepexi.dd.domain.transaction.enums.ResultEnum;
import com.deepexi.dd.domain.transaction.remote.order.SaleOrderInfoClient;
import com.deepexi.dd.domain.transaction.remote.order.ToolLinkClient;
import com.deepexi.dd.domain.transaction.service.common.ToolLinkService;
import com.deepexi.dd.middle.tool.domain.dto.ToolLinkResponseDTO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName : AbstractToolAction
 * @Description : 业务链路动作抽象类
 * @Author : yuanzaishun
 * @Date: 2020-08-01 15:17
 */
public abstract class AbstractToolAction<T> {
    @Autowired
    protected ToolLinkClient toolLinkClient;
    protected String SUCCESS="0";
    /**
     * 获取需要扭转的对象
     * @param id
     * @return
     */
    public abstract T  getOrderObj(Long id) throws ApplicationException;

    /**
     * 验证对象是否可以扭转
     * @param t
     * @return
     */
    public abstract boolean validationOrder(T t) throws ApplicationException;

    /**
     * 获取扭转规则
     * @param t
     * @return
     */
    public abstract String getRuleData(T t) throws ApplicationException;

    /**
     * 扭转后回调操作
     * @param toolLinkNextBusinessResponseDTO
     * @throws ApplicationException
     */
    public abstract void callback(ToolLinkNextBusinessResponseDTO toolLinkNextBusinessResponseDTO,T t) throws ApplicationException;

    /**
     * 获取当前单号所属链路的版本
     * @param id
     * @return
     */
    public  Integer getVersion(Long id) throws Exception{
        if (Objects.isNull(id)){//订单不存在
            throw new ApplicationException(ResultEnum.ORDER_NOT_FOUND);
        }
        ToolLinkListRequestQuery query = new ToolLinkListRequestQuery();
        query.setListId(id);
        query.setListType(getListType());
        Payload<List<ToolLinkListResponseDTO>> listPayload = toolLinkClient.listToolLinkLists(query);
        List<ToolLinkResponseDTO> toolLinkList = GeneralConvertUtils.convert2List(listPayload.getPayload(), ToolLinkResponseDTO.class);
        if (CollectionUtils.isEmpty(toolLinkList)){
            throw new ApplicationException(ResultEnum.ORDER_NOT_FOUND);
        }
        return toolLinkList.get(0).getVersion();
    }

    /**
     * 单据类型
     * @return
     */
    public abstract String getListType();


}
