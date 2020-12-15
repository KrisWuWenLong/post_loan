<template>
  <j-modal
    :title="title"
    :width="800"
    :visible="visible"
    :confirmLoading="confirmLoading"
    switchFullscreen
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
      
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="客户名称">
          <a-input placeholder="请输入客户名称" v-decorator="['customerName', validatorRules.customerName ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="统一社会信用代码">
          <a-input placeholder="请输入统一社会信用代码" v-decorator="['unifiedSocialCreditCode', validatorRules.unifiedSocialCreditCode ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="还款日期">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'repaymentDate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="计息天数">
          <a-input-number v-decorator="[ 'interestDays', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="贷款本金余额（元）">
          <a-input-number v-decorator="[ 'loanPrincipalBalance', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="期间应还本金（元）">
          <a-input-number v-decorator="[ 'currentBalance', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="期间应还利息（元）">
          <a-input-number v-decorator="[ 'currentInterest', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="应还本息合计（元）">
          <a-input-number v-decorator="[ 'currentInterestBalanceSum', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="信托报酬（元）">
          <a-input-number v-decorator="[ 'trusteeRevenue', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="保管费（元）">
          <a-input-number v-decorator="[ 'custodianFee', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="贷款管理费（元）">
          <a-input-number v-decorator="[ 'loanManagementFee', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="其他费用增值税等（元）">
          <a-input-number v-decorator="[ 'otherFee', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="优先级本金余额（元）">
          <a-input-number v-decorator="[ 'priorityPrincipalBalance', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="优先级本金分配（元）">
          <a-input-number v-decorator="[ 'priorityPrincipalAllocation', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="优先级信托收益（元）">
          <a-input-number v-decorator="[ 'priorityTrustIncome', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="次级本金余额（元）">
          <a-input-number v-decorator="[ 'principalBalance', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="次级本金分配（元）">
          <a-input-number v-decorator="[ 'subordinatedPrincipalAllocation', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="次级收益分配（元）">
          <a-input-number v-decorator="[ 'secondaryIncomeDistribution', {}]" />
        </a-form-item>
		
      </a-form>
    </a-spin>
  </j-modal>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from "moment"

  export default {
    name: "RepaymentScheduleModal",
    data () {
      return {
        title:"操作",
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },

        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules:{
        customerName:{rules: [{ required: true, message: '请输入客户名称!' }]},
        unifiedSocialCreditCode:{rules: [{ required: true, message: '请输入统一社会信用代码!' }]},
        },
        url: {
          add: "/loan/repaymentSchedule/add",
          edit: "/loan/repaymentSchedule/edit",
        },
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'customerName','unifiedSocialCreditCode','interestDays','loanPrincipalBalance','currentBalance','currentInterest','currentInterestBalanceSum','trusteeRevenue','custodianFee','loanManagementFee','otherFee','priorityPrincipalBalance','priorityPrincipalAllocation','priorityTrustIncome','principalBalance','subordinatedPrincipalAllocation','secondaryIncomeDistribution'))
		  //时间格式化
          this.form.setFieldsValue({repaymentDate:this.model.repaymentDate?moment(this.model.repaymentDate):null})
        });

      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            //时间格式化
            formData.repaymentDate = formData.repaymentDate?formData.repaymentDate.format('YYYY-MM-DD HH:mm:ss'):null;
            
            console.log(formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })



          }
        })
      },
      handleCancel () {
        this.close()
      },


    }
  }
</script>

<style lang="less" scoped>

</style>