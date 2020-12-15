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
          label="贷款金额（元）">
          <a-input-number v-decorator="[ 'loanAmount', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="贷款期限（月）">
          <a-input-number v-decorator="[ 'loanPeriod', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="贷款利率">
          <a-input-number v-decorator="[ 'loanRate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="贷款日期">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'loanCreatetime', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="所属信托公司">
          <a-input placeholder="请输入所属信托公司" v-decorator="['subordinateTrustCompany', validatorRules.subordinateTrustCompany ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="期数">
          <a-input placeholder="请输入期数" v-decorator="['periods', validatorRules.periods ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="剩余本金（元）">
          <a-input-number v-decorator="[ 'residualPrincipal', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="剩余利息（元）">
          <a-input-number v-decorator="[ 'residualInterest', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="贷款状态（1：待还款|2：已完成|0：已取消|3：已逾期）">
          <a-input placeholder="请输入贷款状态（1：待还款|2：已完成|0：已取消|3：已逾期）" v-decorator="['loanState', {}]" />
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
    name: "CustomerLoanListModal",
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
        subordinateTrustCompany:{rules: [{ required: true, message: '请输入所属信托公司!' }]},
        periods:{rules: [{ required: true, message: '请输入期数!' }]},
        },
        url: {
          add: "/loan/customerLoanList/add",
          edit: "/loan/customerLoanList/edit",
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
          this.form.setFieldsValue(pick(this.model,'customerName','unifiedSocialCreditCode','loanAmount','loanPeriod','loanRate','subordinateTrustCompany','periods','residualPrincipal','residualInterest','loanState'))
		  //时间格式化
          this.form.setFieldsValue({loanCreatetime:this.model.loanCreatetime?moment(this.model.loanCreatetime):null})
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
            formData.loanCreatetime = formData.loanCreatetime?formData.loanCreatetime.format('YYYY-MM-DD HH:mm:ss'):null;
            
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