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
          label="信托产品编号">
          <a-input placeholder="请输入信托产品编号" v-decorator="['trustProductNumber', validatorRules.trustProductNumber ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="信托机构名">
          <a-input placeholder="请输入信托机构名" v-decorator="['trustInstitutionName', validatorRules.trustInstitutionName ]" />
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
          label="还款期间">
          <a-input placeholder="请输入还款期间" v-decorator="['paymentNumber', validatorRules.paymentNumber ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="期间应收本金">
          <a-input-number v-decorator="[ 'currentBalance', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="期间应收利息">
          <a-input-number v-decorator="[ 'currentInterest', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="期间实收本金">
          <a-input-number v-decorator="[ 'realBalance', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="期间实收利息">
          <a-input-number v-decorator="[ 'realInterest', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="信托报酬">
          <a-input-number v-decorator="[ 'trusteeRevenue', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="保管费">
          <a-input-number v-decorator="[ 'custodianFee', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="贷款管理费">
          <a-input-number v-decorator="[ 'loanManagementFee', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="其他费用增值税等">
          <a-input-number v-decorator="[ 'otherFee', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="优先级本金余额">
          <a-input-number v-decorator="[ 'priorityPrincipalBalance', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="优先级本金分配">
          <a-input-number v-decorator="[ 'priorityPrincipalAllocation', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="优先级信托收益">
          <a-input-number v-decorator="[ 'priorityTrustIncome', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="次级本金余额">
          <a-input-number v-decorator="[ 'principalBalance', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="次级本金分配">
          <a-input-number v-decorator="[ 'subordinatedPrincipalAllocation', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="次级收益分配">
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
    name: "LoanProceedsModal",
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
        trustProductNumber:{rules: [{ required: true, message: '请输入信托产品编号!' }]},
        trustInstitutionName:{rules: [{ required: true, message: '请输入信托机构名!' }]},
        periods:{rules: [{ required: true, message: '请输入期数!' }]},
        paymentNumber:{rules: [{ required: true, message: '请输入还款期间!' }]},
        },
        url: {
          add: "/loan/loanProceeds/add",
          edit: "/loan/loanProceeds/edit",
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
          this.form.setFieldsValue(pick(this.model,'trustProductNumber','trustInstitutionName','periods','paymentNumber','currentBalance','currentInterest','realBalance','realInterest','trusteeRevenue','custodianFee','loanManagementFee','otherFee','priorityPrincipalBalance','priorityPrincipalAllocation','priorityTrustIncome','principalBalance','subordinatedPrincipalAllocation','secondaryIncomeDistribution'))
		  //时间格式化
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