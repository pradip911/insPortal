package com.myjavablog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bt.rest.customexception.CustomerAlreadyExistException;
import com.myjavablog.model.PolicyEntity;
import com.myjavablog.repository.CdrRepository;
import com.myjavablog.repository.PolicyCenterRepository;
@Service
@Transactional
public class PolicyInterfaceImpl implements PolicyInterface{
	@Autowired PolicyCenterRepository cdrRepo;
	
	@Override
	public List<PolicyEntity> getAllCdrsDetails() {
		// TODO Auto-generated method stub
		return cdrRepo.findAll();
	}

	@Override
	public Optional<PolicyEntity> getCdrById(Integer id) {
		// TODO Auto-generated method stub
		return cdrRepo.findById(id);
	}

	@Override
	public void saveOrUpdateCompany(PolicyEntity cdr) throws CustomerAlreadyExistException {
		cdrRepo.save(cdr);
		
	}

	

	@Override
	public Optional<PolicyEntity> getCDrByName(String name) {
		// TODO Auto-generated method stub
		return cdrRepo.getInsuranceDetailsByContactNoInDb(name);
	}

	@Override
	public void deleteCompany(Integer id) {
		cdrRepo.deleteById(id);
		
	}

	@Override
	public PolicyEntity getCdrByContactNo(String contactNo) {
		// TODO Auto-generated method stub
		return cdrRepo.getInsuranceDetailsByContactNo(contactNo);
	}

	
	
}
