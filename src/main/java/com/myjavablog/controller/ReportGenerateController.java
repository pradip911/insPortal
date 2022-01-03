package com.myjavablog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myjavablog.service.ReportGenerationService;

@RestController
public class ReportGenerateController {
	@Autowired ReportGenerationService reportService;

	@GetMapping("/getReport/api/v1")
	public String generateReport() {
		return reportService.generateReport();
	}
	
}
