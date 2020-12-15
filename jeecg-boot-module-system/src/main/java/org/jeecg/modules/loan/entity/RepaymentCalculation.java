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
 * @Description: 还款测算
 * @Author: jeecg-boot
 * @Date:   2020-12-15
 * @Version: V1.0
 */
@Data
@TableName("repayment_calculation")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="repayment_calculation对象", description="还款测算")
public class RepaymentCalculation {
    
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
	/**还款期数*/
	@Excel(name = "还款期数", width = 15)
    @ApiModelProperty(value = "还款期数")
	private java.lang.Integer repaymentPeriods;
	/**收款日期*/
	@Excel(name = "收款日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "收款日期")
	private java.util.Date collectionDays;
	/**计息天数*/
	@Excel(name = "计息天数", width = 15)
    @ApiModelProperty(value = "计息天数")
	private java.lang.Integer interestDays;
	/**贷款本金余额（元）*/
	@Excel(name = "贷款本金余额（元）", width = 15)
    @ApiModelProperty(value = "贷款本金余额（元）")
	private java.math.BigDecimal loanPrincipalBalance;
	/**期间应还本金（元）*/
	@Excel(name = "期间应还本金（元）", width = 15)
    @ApiModelProperty(value = "期间应还本金（元）")
	private java.math.BigDecimal currentBalance;
	/**期间应还利息（元）*/
	@Excel(name = "期间应还利息（元）", width = 15)
    @ApiModelProperty(value = "期间应还利息（元）")
	private java.math.BigDecimal currentInterest;
	/**应还本息合计（元）*/
	@Excel(name = "应还本息合计（元）", width = 15)
    @ApiModelProperty(value = "应还本息合计（元）")
	private java.math.BigDecimal currentInterestBalanceSum;
}
