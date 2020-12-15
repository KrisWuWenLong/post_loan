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
 * @Description: 贷款收益表
 * @Author: jeecg-boot
 * @Date:   2020-12-15
 * @Version: V1.0
 */
@Data
@TableName("loan_proceeds")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="loan_proceeds对象", description="贷款收益表")
public class LoanProceeds {
    
	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
	private java.lang.Integer id;
	/**信托产品编号*/
	@Excel(name = "信托产品编号", width = 15)
    @ApiModelProperty(value = "信托产品编号")
	private java.lang.String trustProductNumber;
	/**信托机构名*/
	@Excel(name = "信托机构名", width = 15)
    @ApiModelProperty(value = "信托机构名")
	private java.lang.String trustInstitutionName;
	/**期数*/
	@Excel(name = "期数", width = 15)
    @ApiModelProperty(value = "期数")
	private java.lang.String periods;
	/**还款期间*/
	@Excel(name = "还款期间", width = 15)
    @ApiModelProperty(value = "还款期间")
	private java.lang.String paymentNumber;
	/**期间应收本金*/
	@Excel(name = "期间应收本金", width = 15)
    @ApiModelProperty(value = "期间应收本金")
	private java.math.BigDecimal currentBalance;
	/**期间应收利息*/
	@Excel(name = "期间应收利息", width = 15)
    @ApiModelProperty(value = "期间应收利息")
	private java.math.BigDecimal currentInterest;
	/**期间实收本金*/
	@Excel(name = "期间实收本金", width = 15)
    @ApiModelProperty(value = "期间实收本金")
	private java.math.BigDecimal realBalance;
	/**期间实收利息*/
	@Excel(name = "期间实收利息", width = 15)
    @ApiModelProperty(value = "期间实收利息")
	private java.math.BigDecimal realInterest;
	/**信托报酬*/
	@Excel(name = "信托报酬", width = 15)
    @ApiModelProperty(value = "信托报酬")
	private java.math.BigDecimal trusteeRevenue;
	/**保管费*/
	@Excel(name = "保管费", width = 15)
    @ApiModelProperty(value = "保管费")
	private java.math.BigDecimal custodianFee;
	/**贷款管理费*/
	@Excel(name = "贷款管理费", width = 15)
    @ApiModelProperty(value = "贷款管理费")
	private java.math.BigDecimal loanManagementFee;
	/**其他费用增值税等*/
	@Excel(name = "其他费用增值税等", width = 15)
    @ApiModelProperty(value = "其他费用增值税等")
	private java.math.BigDecimal otherFee;
	/**优先级本金余额*/
	@Excel(name = "优先级本金余额", width = 15)
    @ApiModelProperty(value = "优先级本金余额")
	private java.math.BigDecimal priorityPrincipalBalance;
	/**优先级本金分配*/
	@Excel(name = "优先级本金分配", width = 15)
    @ApiModelProperty(value = "优先级本金分配")
	private java.math.BigDecimal priorityPrincipalAllocation;
	/**优先级信托收益*/
	@Excel(name = "优先级信托收益", width = 15)
    @ApiModelProperty(value = "优先级信托收益")
	private java.math.BigDecimal priorityTrustIncome;
	/**次级本金余额*/
	@Excel(name = "次级本金余额", width = 15)
    @ApiModelProperty(value = "次级本金余额")
	private java.math.BigDecimal principalBalance;
	/**次级本金分配*/
	@Excel(name = "次级本金分配", width = 15)
    @ApiModelProperty(value = "次级本金分配")
	private java.math.BigDecimal subordinatedPrincipalAllocation;
	/**次级收益分配*/
	@Excel(name = "次级收益分配", width = 15)
    @ApiModelProperty(value = "次级收益分配")
	private java.math.BigDecimal secondaryIncomeDistribution;
}
