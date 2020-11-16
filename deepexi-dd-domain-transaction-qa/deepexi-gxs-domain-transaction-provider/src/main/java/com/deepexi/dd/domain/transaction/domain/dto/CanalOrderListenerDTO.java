package com.deepexi.dd.domain.transaction.domain.dto;

import lombok.Data;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-25 11:02
 * MQ取消订单结果
 */
@Data
public class CanalOrderListenerDTO {

    /**
     * 一体化系统单号
     */
    private String sourceOrderNo;

    /**
     * 1 创建单据成功
     * 2 云仓单据取消
     * 3 云仓单据已出库取消失败
     * 4 云仓单据退货入库中
     * 5 云仓单据已确认退货入库
     */
    private Integer dealType;

    /**
     * 单据类型
     */
    private Integer businessType;

    /**
     * 信息
     */
    private String message;
}
