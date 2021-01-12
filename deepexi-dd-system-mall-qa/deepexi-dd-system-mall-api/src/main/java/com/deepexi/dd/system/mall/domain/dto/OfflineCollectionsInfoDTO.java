package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @ClassName OfflineCollectionsInfoDTO
 * @Description 收款信息
 * @Author SongTao
 * @Date 2020-07-27
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OfflineCollectionInfoDTO")
public class OfflineCollectionsInfoDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = -4260884674250369860L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "账户名称",required = true)
    @NotEmpty(message = "账户名称为空")
    private String name;

    @ApiModelProperty(value = "开户银行",required = true)
    @NotEmpty(message = "开户银行为空")
    private String bankName;

    @ApiModelProperty(value = "银行账号",required = true)
    @NotEmpty(message = "银行账号为空")
    private String bankAccount;

    @ApiModelProperty(value = "所属支行",required = true)
    @NotEmpty(message = "所属支行为空")
    private String branchBankAddress;

    @ApiModelProperty(value = "是否默认收款账户：0是、1否")
    private Integer isProceedsAccount;
}
