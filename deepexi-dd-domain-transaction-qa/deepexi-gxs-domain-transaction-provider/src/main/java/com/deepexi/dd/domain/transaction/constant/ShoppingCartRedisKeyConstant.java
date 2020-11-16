package com.deepexi.dd.domain.transaction.constant;

/**
 * 购物车Redis缓存常量定义
 * @author yangwu
 * @date 2020/7/6
 */
public class ShoppingCartRedisKeyConstant {
    /**
     * 购物车
     */
    private static final String SHOPPING_CART="shoppingCart:";
    /**
     * 租户
     */
    private static final String TENANT_ID=SHOPPING_CART+"tenant_id_%s:";
    /**
     * 应用
     */
    private static final String APP_ID=TENANT_ID+"app_id_%s:";
    /**
     * 用户
     */
    public static final String USER_ID=APP_ID+"user_id_%s:";
    /**
     * 活动
     */
    public static final String ACTIVITIES_ID=USER_ID+"activities_id%s:";
}
