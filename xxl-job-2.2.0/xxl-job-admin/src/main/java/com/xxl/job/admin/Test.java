package com.xxl.job.admin;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/11/04/17:41
 * @Description:
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Test
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/11/4
 * @Version V1.0
 **/
public class Test {
    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        list.add(0l);
        if (list.contains(0l)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
