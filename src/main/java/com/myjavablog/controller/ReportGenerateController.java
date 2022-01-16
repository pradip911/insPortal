package com.myjavablog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.myjavablog.model.PaymentHistory;
import com.myjavablog.service.PaymentDashboardService;
import com.myjavablog.service.ReportGenerationService;

@RestController
public class ReportGenerateController {
	@Autowired ReportGenerationService reportService;
	
	@Autowired PaymentDashboardService paymentDashboardService;

	@GetMapping("/getReport/api/v1")
	public String generateReport() {
		return reportService.generateReport();
	}
	
	@GetMapping("/downloadReportv1")
	public ModelAndView downloadReport() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<PaymentHistory> paymentHistory=paymentDashboardService.getSuccessfulPayment(auth.getName());
		modelAndView.addObject("paymentSuccess",paymentHistory);
		modelAndView.setViewName("user/availableReport");
		return modelAndView;
		
	}
	
}
