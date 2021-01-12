package com.deepexi.dd.system.mall.controller.regionalOrder;

import com.deepexi.dd.system.mall.domain.query.regionalOrder.FrontCategoryPropertyQuery;
import com.deepexi.dd.system.mall.domain.vo.regionalOrder.FrontCategoryPropertyResponseVO;
import com.deepexi.dd.system.mall.service.regionalOrder.RegionalOrderService;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName RegionalOrderController
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-18
 * @Version 1.0
 **/
@RestController
@Api(value = "区域订货管理", tags = "区域订货管理")
@RequestMapping("/admin-api/v1/domain/regional/order")
public class RegionalOrderController {

    @Autowired
    private RegionalOrderService regionalOrderService;

    /**
     * @Description: 通过类目查询类目下面的属性.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/18
     */
    @GetMapping("/findCategoryProperty")
    @ApiOperation("查询类目下面的属性")
    Payload<List<FrontCategoryPropertyResponseVO>> findCategoryProperty(FrontCategoryPropertyQuery propertyQuery) throws Exception {
        List<FrontCategoryPropertyResponseVO> result = regionalOrderService.findCategoryProperty(propertyQuery);
        return new Payload<>(result);
    }

    /**
     * @Description: 通过类目查询类目下面的属性.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/18
     */
    @PostMapping("/findCategoryPropertyPost")
    @ApiOperation("查询类目下面的属性")
    Payload<List<FrontCategoryPropertyResponseVO>> findCategoryPropertyPost(@RequestBody @Valid FrontCategoryPropertyQuery propertyQuery) throws Exception {
        List<FrontCategoryPropertyResponseVO> result = regionalOrderService.findCategoryProperty(propertyQuery);
        return new Payload<>(result);
    }
}
