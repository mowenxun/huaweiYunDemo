package com.deepexi.dd.system.mall.util;

import java.util.UUID;

/**
 * @author tianwen
 * @version 1.0
 * @date 2020-03-19 9:55 上午
 */
public class UUIDUtils {
    /**
     * 32位默认长度的uuid
     * (获取32位uuid)
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * (获取指定长度uuid)
     *
     * @return
     */
    public static String getUUID(int len) {
        if (0 >= len) {
            return null;
        }

        String uuid = getUUID();
        StringBuffer str = new StringBuffer();

        for (int i = 0; i < len; i++) {
            str.append(uuid.charAt(i));
        }

        return str.toString();
    }

    public static void main(String[] args) {
        String uuid = getUUID(6);
        System.out.println(uuid);
    }
}
