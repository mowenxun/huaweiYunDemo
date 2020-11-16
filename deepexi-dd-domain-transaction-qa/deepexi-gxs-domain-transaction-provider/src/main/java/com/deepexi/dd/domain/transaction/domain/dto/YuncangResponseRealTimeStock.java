package com.deepexi.dd.domain.transaction.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-25 16:05
 */
@Data
public class YuncangResponseRealTimeStock {

    private Integer code;

    private String count;

    private String data;

    private String message;

    private String msg;

    private List<YuncangRealTimeStock> rows;

    private String total;
}
