package com.deepexi.dd.system.mall.domain.vo.customer;

import com.deepexi.dd.system.mall.domain.vo.BaseResponseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName BusinessPartnerResponseVO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-14
 * @Version 1.0
 **/
@Data
@ApiModel
public class BusinessPartnerResponseVO extends BaseResponseVO {

    private static final long serialVersionUID = -7247086227423329576L;

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Long id;
    /**
     * 伙伴名称
     */
    @ApiModelProperty(value = "伙伴名称")
    private String name;
    /**
     * 伙伴code
     */
    @ApiModelProperty(value = "伙伴code")
    private String code;
    /**
     * 伙伴组织id
     */
    @ApiModelProperty(value = "伙伴组织id")
    private Long orgId;
    /**
     * 信用代码
     */
    @ApiModelProperty(value = "信用代码")
    private String creditCode;
    /**
     * 所在国家
     */
    @ApiModelProperty(value = "所在国家")
    private String countryCode;
    /**
     * 所在省份
     */
    @ApiModelProperty(value = "所在省份")
    private String provinceCode;
    /**
     * 所在城市
     */
    @ApiModelProperty(value = "所在城市")
    private String cityCode;
    /**
     * 所在区/县
     */
    @ApiModelProperty(value = "所在区/县")
    private String downtownCode;
    /**
     * 所在街道
     */
    @ApiModelProperty(value = "所在街道")
    private String streetCode;
    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    private String detailAdress;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String updatedBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updatedTime;
    /**
     * 删除状态 0 未删除 1删除
     */
    @ApiModelProperty(value = "删除状态 0 未删除 1删除")
    private Boolean deleted;
    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private Integer version;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 企业性质id
     */
    @ApiModelProperty(value = "企业性质id")
    private Long natureCompanyId;
    /**
     * 营业执照号
     */
    @ApiModelProperty(value = "营业执照号")
    private String businessLicenseNo;
    /**
     * 法人代表
     */
    @ApiModelProperty(value = "法人代表")
    private String corporateRepresentative;
    /**
     * 注册资金
     */
    @ApiModelProperty(value = "注册资金")
    private BigDecimal registeredCapital;
    /**
     * 成立日期
     */
    @ApiModelProperty(value = "成立日期")
    private Date registrationTime;
    /**
     * 开始营业期限
     */
    @ApiModelProperty(value = "开始营业期限")
    private Date startOperationPeriod;
    /**
     * 结束营业期限
     */
    @ApiModelProperty(value = "结束营业期限")
    private Date endOperationPeriod;
    /**
     * 经营范围
     */
    @ApiModelProperty(value = "经营范围")
    private String businessScope;
    /**
     * 登记机关
     */
    @ApiModelProperty(value = "登记机关")
    private String registrationAuthority;
    /**
     * 登记状态
     */
    @ApiModelProperty(value = "登记状态")
    private Integer registrationStatus;
    /**
     * 核准日期
     */
    @ApiModelProperty(value = "核准日期")
    private Date approvalDate;
    /**
     * 所在省名称
     */
    @ApiModelProperty(value = "所在省名称")
    private String provinceName;
    /**
     * 所在市名称
     */
    @ApiModelProperty(value = "所在市名称")
    private String cityName;
    /**
     * 所在区县名称
     */
    @ApiModelProperty(value = "所在区县名称")
    private String downtownName;
    /**
     * 所在街道名称
     */
    @ApiModelProperty(value = "所在街道名称")
    private String streetName;
    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private Long roleId;
    /**
     * 年销售额
     */
    @ApiModelProperty(value = "年销售额")
    private BigDecimal annualSales;
    /**
     * 银行对公账户
     */
    @ApiModelProperty(value = "银行对公账户")
    private String bankCorporateAccount;
    /**
     * 店铺面积
     */
    @ApiModelProperty(value = "店铺面积")
    private BigDecimal area;
    /**
     * 法人姓名
     */
    @ApiModelProperty(value = "法人姓名")
    private String nameLegalPerson;
    /**
     * 法人手机号码
     */
    @ApiModelProperty(value = "法人手机号码")
    private String mobileLegalPerson;
    /**
     * 身份证号码
     */
    @ApiModelProperty(value = "身份证号码")
    private String idNumber;
    /**
     * 企业类型id
     */
    @ApiModelProperty(value = "企业类型id")
    private Long companyTypeId;

    /**
     * 企业类型名称
     */
    @ApiModelProperty(value = "企业类型名称")
    private String companyTypeName;

    @ApiModelProperty(value = "文档列表")
    private List<MerchantDocumentResponseVO> docList;

    @ApiModelProperty(value = "门店信息")
    private List<StoreResponseVO> storeList;

    @ApiModelProperty(value = "仓库信息")
    private List<WarehouseResponseVO> warehouseList;

    @ApiModelProperty(value = "信用账户信息")
    private List<CreditResponseVO> creditList;

    @ApiModelProperty(value = "资金账户信息")
    private List<CapitalResponseVO> capitalList;
}
