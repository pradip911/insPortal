package com.myjavablog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myjavablog.model.PolicyEntity;

public interface PolicyCenterRepository extends JpaRepository<PolicyEntity, Integer>{

	@Query("select u from PolicyEntity u where u.policyHolderContactNo= :policyHolderContactNo")
    Optional<PolicyEntity> getInsuranceDetailsByContactNoInDb(@Param("policyHolderContactNo") String policyHolderContactNo);
	
	@Query("select u from PolicyEntity u where u.policyHolderContactNo= :policyHolderContactNo")
    PolicyEntity getInsuranceDetailsByContactNo(@Param("policyHolderContactNo") String policyHolderContactNo);
}
