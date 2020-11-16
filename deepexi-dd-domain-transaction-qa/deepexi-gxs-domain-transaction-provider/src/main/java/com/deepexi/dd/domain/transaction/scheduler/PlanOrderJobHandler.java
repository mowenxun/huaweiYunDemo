package com.deepexi.dd.domain.transaction.scheduler;

import com.deepexi.dd.domain.transaction.api.SaleOrderInfoApi;
import com.deepexi.dd.domain.transaction.service.SaleOrderInfoService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.xxl.annotation.JobHandler;
import com.deepexi.xxl.service.job.AbstractJobHandler;
import com.xxl.job.core.biz.model.ReturnT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@JobHandler(value = "PlanOrderJob")
@Slf4j
public class PlanOrderJobHandler extends AbstractJobHandler {

    @Autowired
    private SaleOrderInfoService saleOrderInfoService;

    @Override
    public ReturnT<String> execute(String s) {
        try{
            log.info("PlanOrderJob-execute");
            saleOrderInfoService.closePreMonthPlanOrder();
            return new ReturnT<>("成功");
        }
        catch (Exception e){
            return new ReturnT<>("失败:"+e.getMessage());
        }
    }
}