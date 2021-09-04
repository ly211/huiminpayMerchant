package com.huiminpay.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "商品实体类对象")
@Data
@TableName("merchant")
public class Merchant implements Serializable {

    @ApiModelProperty("主键id")
    @TableId("id")
    private Long id;

    @ApiModelProperty("商铺名称")
    @TableField("merchant_name")
    private String merchantName;

    @ApiModelProperty("商铺编号")
    @TableField("merchant_no")
    private String merchantNo;

    @ApiModelProperty("商铺地址")
    @TableField("merchant_address")
    private String merchantAddress;

    @ApiModelProperty("商铺类型")
    @TableField("merchant_type")
    private String merchantType;

    @ApiModelProperty("商铺营业执照")
    @TableField("business_licenses_img")
    private String businessLicensesImg;

    @ApiModelProperty("商铺法人身份证正面")
    @TableField("id_card_front_img")
    private String idCardFrontImg;

    @ApiModelProperty("商铺法人身份证反面")
    @TableField("id_card_after_img")
    private String idCardAfterImg;

    @ApiModelProperty("商铺负责人名称")
    @TableField("username")
    private String username;

    @ApiModelProperty("商铺负责人联系方式")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty("商铺联系人地址")
    @TableField("contacts_address")
    private String contactsAddress;

    @ApiModelProperty("商铺审核状态")
    @TableField("audit_status")
    private String auditStatus;

    @ApiModelProperty("租户id")
    @TableId("tenant_id")
    private String tenantId;

}
