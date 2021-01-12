package com.deepexi.dd.system.mall.controller.app;

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.system.mall.domain.vo.app.ListCustomerContractsRequestVO;
import com.deepexi.dd.system.mall.domain.vo.app.ListCustomerContractsResponseVO;
import com.deepexi.dd.system.mall.remote.customer.CustomerContractClient;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import domain.dto.ListCustomerContractsRequestDTO;
import domain.dto.ListCustomerContractsResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author dengWenShun
 * @version 1.0
 * @date 2020/8/29 20:13
 */
@Api(value = "合同", tags = "合同管理")
@RequestMapping("/admin-api/v1/customerContract")
@RestController
public class CustomerContractController {

    @Autowired
    private CustomerContractClient customerContractClient;

    /**
     * 查询合同列表
     *
     * @param query query
     * @return 合同列表
     */
    @GetMapping("/list")
    @ApiOperation("查询合同列表")
    Payload<List<ListCustomerContractsResponseVO>> listCustomerContracts(@Valid ListCustomerContractsRequestVO query) throws Exception {
        Payload<List<ListCustomerContractsResponseDTO>> listCustomerProjects
                = customerContractClient.listCustomerContracts(query.clone(ListCustomerContractsRequestDTO.class,
                CloneDirection.FORWARD));
        List<ListCustomerContractsResponseDTO> payload = listCustomerProjects.getPayload();
        return new Payload<>(GeneralConvertUtils.convert2List(payload, ListCustomerContractsResponseVO.class));
    }

    /**
     * 查询合同详情
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation("查询合同详情")
    @GetMapping("/{id}")
    Payload<ListCustomerContractsResponseDTO> selectById(@PathVariable(value = "id") Long id) throws Exception{
        return customerContractClient.selectById(id);
    }

    /**
     * 分页查询合同列表
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation("分页查询合同列表")
    @GetMapping("/page")
    Payload<PageBean<ListCustomerContractsResponseDTO>> listCustomerContractsPage(@Valid ListCustomerContractsRequestDTO query) throws Exception{
        return customerContractClient.listCustomerContractsPage(query);
    }
}
