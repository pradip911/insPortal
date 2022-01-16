package com.myjavablog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myjavablog.model.PaymentHistory;
import com.myjavablog.repository.PaymentDashboardRepo;
@Service
@Transactional
public class PaymentHistDashImpl implements PaymentDashboardService{

	@Autowired
	PaymentDashboardRepo paymentDashRepo;
	
	@Override
	public List<PaymentHistory> getPendingPayments(String email) {
		return paymentDashRepo.getPaymentPendingByemailInDb(email);
	}

	@Override
	public List<PaymentHistory> getSuccessfulPayment(String email) {
		return paymentDashRepo.getSuccessPaymentByemailInDb(email);
	}

}
