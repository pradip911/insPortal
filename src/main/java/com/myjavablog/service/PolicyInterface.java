package com.myjavablog.service;

import java.util.List;
import java.util.Optional;

import com.bt.rest.customexception.CustomerAlreadyExistException;
import com.myjavablog.model.PolicyEntity;
//@Service
public interface PolicyInterface {
	public List<PolicyEntity> getAllCdrsDetails();
	public Optional<PolicyEntity> getCdrById(Integer id);
	public void saveOrUpdateCompany(PolicyEntity cdr) throws CustomerAlreadyExistException;
	public void deleteCompany(Integer id);
	public PolicyEntity getCdrByContactNo(String contactNo);
	public Optional<PolicyEntity> getCDrByName(String name);
}
