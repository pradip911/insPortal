package com.myjavablog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myjavablog.model.PaymentHistory;

public interface PaymentDashboardRepo extends JpaRepository<PaymentHistory, Long>{
	@Query("select u from PaymentHistory u where u.insEmail= :email and u.paymentSuccess='FALSE'")
    List<PaymentHistory> getPaymentPendingByemailInDb(@Param("email") String email);
	
	@Query("select u from PaymentHistory u where u.insEmail= :email and u.paymentSuccess='TRUE'")
    List<PaymentHistory> getSuccessPaymentByemailInDb(@Param("email") String email);
	
	
	
	

}
