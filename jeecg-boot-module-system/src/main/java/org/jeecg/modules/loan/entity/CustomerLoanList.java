package org.jeecg.modules.loan.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 客户贷款列表
 * @Author: jeecg-boot
 * @Date:   2020-12-15
 * @Version: V1.0
 */
@Data
@TableName("customer_loan_list")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="customer_loan_list对象", description="客户贷款列表")
public class CustomerLoanList {
    
	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
	private java.lang.Integer id;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private java.lang.String customerName;
	/**统一社会信用代码*/
	@Excel(name = "统一社会信用代码", width = 15)
    @ApiModelProperty(value = "统一社会信用代码")
	private java.lang.String unifiedSocialCreditCode;
	/**贷款金额（元）*/
	@Excel(name = "贷款金额（元）", width = 15)
    @ApiModelProperty(value = "贷款金额（元）")
	private java.math.BigDecimal loanAmount;
	/**贷款期限（月）*/
	@Excel(name = "贷款期限（月）", width = 15)
    @ApiModelProperty(value = "贷款期限（月）")
	private java.lang.Integer loanPeriod;
	/**贷款利率*/
	@Excel(name = "贷款利率", width = 15)
    @ApiModelProperty(value = "贷款利率")
	private java.math.BigDecimal loanRate;
	/**贷款日期*/
	@Excel(name = "贷款日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "贷款日期")
	private java.util.Date loanCreatetime;
	/**所属信托公司*/
	@Excel(name = "所属信托公司", width = 15)
    @ApiModelProperty(value = "所属信托公司")
	private java.lang.String subordinateTrustCompany;
	/**期数*/
	@Excel(name = "期数", width = 15)
    @ApiModelProperty(value = "期数")
	private java.lang.String periods;
	/**剩余本金（元）*/
	@Excel(name = "剩余本金（元）", width = 15)
    @ApiModelProperty(value = "剩余本金（元）")
	private java.math.BigDecimal residualPrincipal;
	/**剩余利息（元）*/
	@Excel(name = "剩余利息（元）", width = 15)
    @ApiModelProperty(value = "剩余利息（元）")
	private java.math.BigDecimal residualInterest;
	/**贷款状态（1：待还款|2：已完成|0：已取消|3：已逾期）*/
	@Excel(name = "贷款状态（1：待还款|2：已完成|0：已取消|3：已逾期）", width = 15)
    @ApiModelProperty(value = "贷款状态（1：待还款|2：已完成|0：已取消|3：已逾期）")
	private java.lang.String loanState;
}
