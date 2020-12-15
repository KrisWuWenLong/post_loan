<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">

          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="客户名称">
              <a-input placeholder="请输入客户名称" v-model="queryParam.customerName"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="统一社会信用代码">
              <a-input placeholder="请输入统一社会信用代码" v-model="queryParam.unifiedSocialCreditCode"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="还款日期">
              <a-input placeholder="请输入还款日期" v-model="queryParam.repaymentDate"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="计息天数">
              <a-input placeholder="请输入计息天数" v-model="queryParam.interestDays"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="贷款本金余额（元）">
              <a-input placeholder="请输入贷款本金余额（元）" v-model="queryParam.loanPrincipalBalance"></a-input>
            </a-form-item>
          </a-col>
          </template>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('还款计划')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        class="j-table-force-nowrap"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>
    <!-- table区域-end -->

    <!-- 表单区域 -->
    <repaymentSchedule-modal ref="modalForm" @ok="modalFormOk"></repaymentSchedule-modal>
  </a-card>
</template>

<script>
  import '@/assets/less/TableExpand.less'
  import RepaymentScheduleModal from './modules/RepaymentScheduleModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "RepaymentScheduleList",
    mixins:[JeecgListMixin],
    components: {
      RepaymentScheduleModal
    },
    data () {
      return {
        description: '还款计划管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
           },
		   {
            title: '客户名称',
            align:"center",
            dataIndex: 'customerName'
           },
		   {
            title: '统一社会信用代码',
            align:"center",
            dataIndex: 'unifiedSocialCreditCode'
           },
		   {
            title: '还款日期',
            align:"center",
            dataIndex: 'repaymentDate'
           },
		   {
            title: '计息天数',
            align:"center",
            dataIndex: 'interestDays'
           },
		   {
            title: '贷款本金余额（元）',
            align:"center",
            dataIndex: 'loanPrincipalBalance'
           },
		   {
            title: '期间应还本金（元）',
            align:"center",
            dataIndex: 'currentBalance'
           },
		   {
            title: '期间应还利息（元）',
            align:"center",
            dataIndex: 'currentInterest'
           },
		   {
            title: '应还本息合计（元）',
            align:"center",
            dataIndex: 'currentInterestBalanceSum'
           },
		   {
            title: '信托报酬（元）',
            align:"center",
            dataIndex: 'trusteeRevenue'
           },
		   {
            title: '保管费（元）',
            align:"center",
            dataIndex: 'custodianFee'
           },
		   {
            title: '贷款管理费（元）',
            align:"center",
            dataIndex: 'loanManagementFee'
           },
		   {
            title: '其他费用增值税等（元）',
            align:"center",
            dataIndex: 'otherFee'
           },
		   {
            title: '优先级本金余额（元）',
            align:"center",
            dataIndex: 'priorityPrincipalBalance'
           },
		   {
            title: '优先级本金分配（元）',
            align:"center",
            dataIndex: 'priorityPrincipalAllocation'
           },
		   {
            title: '优先级信托收益（元）',
            align:"center",
            dataIndex: 'priorityTrustIncome'
           },
		   {
            title: '次级本金余额（元）',
            align:"center",
            dataIndex: 'principalBalance'
           },
		   {
            title: '次级本金分配（元）',
            align:"center",
            dataIndex: 'subordinatedPrincipalAllocation'
           },
		   {
            title: '次级收益分配（元）',
            align:"center",
            dataIndex: 'secondaryIncomeDistribution'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/loan/repaymentSchedule/list",
          delete: "/loan/repaymentSchedule/delete",
          deleteBatch: "/loan/repaymentSchedule/deleteBatch",
          exportXlsUrl: "loan/repaymentSchedule/exportXls",
          importExcelUrl: "loan/repaymentSchedule/importExcel",
       },
    }
  },
  computed: {
    importExcelUrl: function(){
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
    }
  },
    methods: {
     
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>