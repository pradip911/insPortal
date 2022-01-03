package com.myjavablog.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bt.rest.customexception.CustomerAlreadyExistException;
import com.myjavablog.model.Cdr;
/*@Service
@Transactional*/
public interface CdrService {
	
	public List<Cdr> getAllCdrsDetails();
	public Cdr getCdrById(Long id);
	public void saveOrUpdateCompany(Cdr cdr) throws CustomerAlreadyExistException;
	public void deleteCompany(Long id);
	
	public Optional<Cdr> getCDrByName(String name);
    
}
