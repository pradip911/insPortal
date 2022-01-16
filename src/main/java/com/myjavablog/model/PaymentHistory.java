package com.myjavablog.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "PAYMENT_HISTORY")
public class PaymentHistory {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PAYMENT_ID")
    private long id;
    @Column(name = "INS_EMAIL")
    private String insEmail;
    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;
    @Column(name = "PAYMENT_SUCCESS")
    private String paymentSuccess;
    @Column(name = "POLICY_ID")
    private boolean policyId;
    
    public String getInsEmail() {
		return insEmail;
	}


	public void setInsEmail(String insEmail) {
		this.insEmail = insEmail;
	}


	public Date getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}


	public String isPaymentSuccess() {
		return paymentSuccess;
	}


	public void setPaymentSuccess(String paymentSuccess) {
		this.paymentSuccess = paymentSuccess;
	}


	public boolean isPolicyId() {
		return policyId;
	}


	public void setPolicyId(boolean policyId) {
		this.policyId = policyId;
	}


	
    
    
    
    
    
    
  
    
}
