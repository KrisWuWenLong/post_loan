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
 * @Description: 资金监测表
 * @Author: jeecg-boot
 * @Date:   2020-12-15
 * @Version: V1.0
 */
@Data
@TableName("money_monitor")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="money_monitor对象", description="资金监测表")
public class MoneyMonitor {
    
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
	/**还款期间*/
	@Excel(name = "还款期间", width = 15)
    @ApiModelProperty(value = "还款期间")
	private java.lang.Integer paymentNumber;
	/**最近一次还款截止日*/
	@Excel(name = "最近一次还款截止日", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最近一次还款截止日")
	private java.util.Date lastRepayment;
	/**应还本金*/
	@Excel(name = "应还本金", width = 15)
    @ApiModelProperty(value = "应还本金")
	private java.math.BigDecimal currentBalance;
	/**应还利息*/
	@Excel(name = "应还利息", width = 15)
    @ApiModelProperty(value = "应还利息")
	private java.math.BigDecimal currentInterest;
	/**应还本息合计*/
	@Excel(name = "应还本息合计", width = 15)
    @ApiModelProperty(value = "应还本息合计")
	private java.math.BigDecimal currentInterestBalanceSum;
	/**账户余额-当前*/
	@Excel(name = "账户余额-当前", width = 15)
    @ApiModelProperty(value = "账户余额-当前")
	private java.math.BigDecimal accountBalance;
	/**线上订单额*/
	@Excel(name = "线上订单额", width = 15)
    @ApiModelProperty(value = "线上订单额")
	private java.math.BigDecimal onlineOrders;
	/**品牌监管额*/
	@Excel(name = "品牌监管额", width = 15)
    @ApiModelProperty(value = "品牌监管额")
	private java.math.BigDecimal brandSupervisionAmount;
	/**营收*/
	@Excel(name = "营收", width = 15)
    @ApiModelProperty(value = "营收")
	private java.math.BigDecimal revenue;
	/**覆盖率*/
	@Excel(name = "覆盖率", width = 15)
    @ApiModelProperty(value = "覆盖率")
	private java.math.BigDecimal coverageRate;
	/**差额*/
	@Excel(name = "差额", width = 15)
    @ApiModelProperty(value = "差额")
	private java.math.BigDecimal balance;
}
