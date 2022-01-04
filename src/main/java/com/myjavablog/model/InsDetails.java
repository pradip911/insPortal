package com.myjavablog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "INSURANCE_DETAILS")
public class InsDetails {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "INS_ID")
	    private long id;
	    @Column(name = "INS_NAME")
	   
	    private String emnsName;
	    @Column(name = "INS_ADDRESS")
	    
	    
	    
	   
	    private String insAddress;
	    
	    @Column(name = "INS_EMAIL")
	    
	    
	    
		   
	    private String insEmail;
	    
		public String getInsEmail() {
			return insEmail;
		}
		public void setInsEmail(String insEmail) {
			this.insEmail = insEmail;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getEmnsName() {
			return emnsName;
		}
		public void setEmnsName(String emnsName) {
			this.emnsName = emnsName;
		}
		public String getInsAddress() {
			return insAddress;
		}
		public void setInsAddress(String insAddress) {
			this.insAddress = insAddress;
		}
}
