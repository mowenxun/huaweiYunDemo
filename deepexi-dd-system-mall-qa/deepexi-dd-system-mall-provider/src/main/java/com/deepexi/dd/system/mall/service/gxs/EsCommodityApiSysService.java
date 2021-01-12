package com.deepexi.dd.system.mall.service.gxs;

import com.deepexi.commodity.dto.es.EsGoodInformationApiRequestVO;
import com.deepexi.commodity.dto.es.EsGoodInformationApiResponseVO;
import com.deepexi.dd.system.mall.domain.vo.commodity.EsGoodInformationApiSysResponseVO;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.ApiParam;

import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/12/16/14:33
 * @Description:
 */
public interface EsCommodityApiSysService {

    PageBean<EsGoodInformationApiSysResponseVO> getPage(EsGoodInformationApiRequestVO request);
}
