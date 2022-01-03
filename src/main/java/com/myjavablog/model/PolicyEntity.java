package com.myjavablog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "POLICY_ENTITY")
public class PolicyEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "POLICY_ID")
    private int id;
	@Column(name = "POLICY_HOLDER_NAME")
	private String policyHoldername;
	
	@Column(name = "POLICY_HOLDER_ADDRESS")
	private String policyHolderAddress;
	
	@Column(name = "POLICY_HOLDER_AGE")
	private String policyHolderAge;
	
	@Column(name = "POLICY_HOLDER_SEX")
	private String policyHolderSex;
	
	@Column(name = "POLICY_NAME")
	private String policyName;
	
	@Column(name = "POLICY_HOLDER_CONTACT_NO")
	private String policyHolderContactNo;
	
	

}
