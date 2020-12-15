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
          label="还款期间">
          <a-input-number v-decorator="[ 'paymentNumber', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="最近一次还款截止日">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'lastRepayment', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="应还本金">
          <a-input-number v-decorator="[ 'currentBalance', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="应还利息">
          <a-input-number v-decorator="[ 'currentInterest', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="应还本息合计">
          <a-input-number v-decorator="[ 'currentInterestBalanceSum', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="账户余额-当前">
          <a-input-number v-decorator="[ 'accountBalance', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="线上订单额">
          <a-input-number v-decorator="[ 'onlineOrders', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="品牌监管额">
          <a-input-number v-decorator="[ 'brandSupervisionAmount', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="营收">
          <a-input-number v-decorator="[ 'revenue', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="覆盖率">
          <a-input-number v-decorator="[ 'coverageRate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="差额">
          <a-input-number v-decorator="[ 'balance', {}]" />
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
    name: "MoneyMonitorModal",
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
          add: "/loan/moneyMonitor/add",
          edit: "/loan/moneyMonitor/edit",
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
          this.form.setFieldsValue(pick(this.model,'customerName','unifiedSocialCreditCode','paymentNumber','currentBalance','currentInterest','currentInterestBalanceSum','accountBalance','onlineOrders','brandSupervisionAmount','revenue','coverageRate','balance'))
		  //时间格式化
          this.form.setFieldsValue({lastRepayment:this.model.lastRepayment?moment(this.model.lastRepayment):null})
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
            formData.lastRepayment = formData.lastRepayment?formData.lastRepayment.format('YYYY-MM-DD HH:mm:ss'):null;
            
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