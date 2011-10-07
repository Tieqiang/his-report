/**
 * <p> File: org.openmrs.module.report.web.controller.reportRole.ReportRoleListController.java </p>
 * <p> Project: report-omod </p>
 * <p> Copyright (c) 2011 HISP Technologies. </p>
 * <p> All rights reserved. </p>
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Email: chuyennmth@gmail.com</p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Apr 29, 2011 4:12:00 PM </p>
 * <p> Update date: Apr 29, 2011 4:12:00 PM </p>
 **/

package org.openmrs.module.report.web.controller.reportRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.openmrs.Role;
import org.openmrs.api.context.Context;
import org.openmrs.module.report.BirtReportService;
import org.openmrs.module.report.model.BirtReport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p> Class: ReportRoleListController </p>
 * <p> Package: org.openmrs.module.report.web.controller.reportRole </p> 
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Apr 29, 2011 4:12:00 PM </p>
 * <p> Update date: Apr 29, 2011 4:12:00 PM </p>
 **/
@Controller("ReportRoleListController")
@RequestMapping("/module/report/reportRoleList.form")
public class ReportRoleListController {
	@RequestMapping(method = RequestMethod.GET)
	public String viewReportRole(
			Model model) {
		Set<Role> roles = Context.getAuthenticatedUser().getAllRoles();
		List<String> roleIds = new ArrayList<String>();
		if(CollectionUtils.isNotEmpty(roles)){
			for(Role role : roles){
				roleIds.add(role.getRole());
			}
			BirtReportService birtReportService =  Context.getService(BirtReportService.class);
			List<BirtReport> reports = birtReportService.listBirtReportByRole(roleIds, false);
			model.addAttribute("reports", reports);
		}
		return "/module/report/reportRole/reportRoleList";
	}
}
