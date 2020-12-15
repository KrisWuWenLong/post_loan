package org.jeecg.modules.loan.service.impl;

import org.jeecg.modules.loan.entity.CustomerLoanList;
import org.jeecg.modules.loan.mapper.CustomerLoanListMapper;
import org.jeecg.modules.loan.service.ICustomerLoanListService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户贷款列表
 * @Author: jeecg-boot
 * @Date:   2020-12-15
 * @Version: V1.0
 */
@Service
public class CustomerLoanListServiceImpl extends ServiceImpl<CustomerLoanListMapper, CustomerLoanList> implements ICustomerLoanListService {

}
