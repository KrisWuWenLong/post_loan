package org.jeecg.modules.loan.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.loan.entity.RepaymentCalculation;
import org.jeecg.modules.loan.service.IRepaymentCalculationService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 还款测算
 * @Author: jeecg-boot
 * @Date:   2020-12-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags="还款测算")
@RestController
@RequestMapping("/loan/repaymentCalculation")
public class RepaymentCalculationController extends JeecgController<RepaymentCalculation, IRepaymentCalculationService> {
	@Autowired
	private IRepaymentCalculationService repaymentCalculationService;
	
	/**
	 * 分页列表查询
	 *
	 * @param repaymentCalculation
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "还款测算-分页列表查询")
	@ApiOperation(value="还款测算-分页列表查询", notes="还款测算-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(RepaymentCalculation repaymentCalculation,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RepaymentCalculation> queryWrapper = QueryGenerator.initQueryWrapper(repaymentCalculation, req.getParameterMap());
		Page<RepaymentCalculation> page = new Page<RepaymentCalculation>(pageNo, pageSize);
		IPage<RepaymentCalculation> pageList = repaymentCalculationService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param repaymentCalculation
	 * @return
	 */
	@AutoLog(value = "还款测算-添加")
	@ApiOperation(value="还款测算-添加", notes="还款测算-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody RepaymentCalculation repaymentCalculation) {
		repaymentCalculationService.save(repaymentCalculation);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param repaymentCalculation
	 * @return
	 */
	@AutoLog(value = "还款测算-编辑")
	@ApiOperation(value="还款测算-编辑", notes="还款测算-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody RepaymentCalculation repaymentCalculation) {
		repaymentCalculationService.updateById(repaymentCalculation);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "还款测算-通过id删除")
	@ApiOperation(value="还款测算-通过id删除", notes="还款测算-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		repaymentCalculationService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "还款测算-批量删除")
	@ApiOperation(value="还款测算-批量删除", notes="还款测算-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.repaymentCalculationService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "还款测算-通过id查询")
	@ApiOperation(value="还款测算-通过id查询", notes="还款测算-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		RepaymentCalculation repaymentCalculation = repaymentCalculationService.getById(id);
		return Result.ok(repaymentCalculation);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param repaymentCalculation
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, RepaymentCalculation repaymentCalculation) {
      return super.exportXls(request, repaymentCalculation, RepaymentCalculation.class, "还款测算");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, RepaymentCalculation.class);
  }

}
