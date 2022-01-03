package com.myjavablog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myjavablog.model.Cdr;
import com.myjavablog.repository.CdrRepository;
@Service
@Transactional
public class CdrServiceImpl implements CdrService{

	@Autowired CdrRepository cdrRepo;
	@Override
	public List<Cdr> getAllCdrsDetails() {
		return cdrRepo.findAll();
	}
	
	@Override
	public void saveOrUpdateCompany(Cdr cdr) {
		cdrRepo.save(cdr);
	}
	@Override
	public void deleteCompany(Long id) {
		cdrRepo.deleteById(id);
	}

	@Override
	public Cdr getCdrById(Long id) {
		return cdrRepo.findById(id).get();
	}

	@Override
	public Optional<Cdr> getCDrByName(String name) {
		// TODO Auto-generated method stub
		return cdrRepo.getCDrByNameInDb(name);
	}

	/*@Override
	public Cdr getCDrByName(String name) {
		// TODO Auto-generated method stub
		return cdrRepo.getCDrByNameInDb(name);
	}*/

	
	/*@Override
	public Cdr getCDrByName(Optional<String> name) {
		//return null;
		return cdrRepo.getCDrByNameInDb(name);
	}

	*/

	
	

}
