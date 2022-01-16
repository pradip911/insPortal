package com.myjavablog.service;

import java.util.List;

import com.myjavablog.model.PaymentHistory;

public interface PaymentDashboardService {
	public List<PaymentHistory> getPendingPayments(String email);
	
	public List<PaymentHistory> getSuccessfulPayment(String email);
	

}
