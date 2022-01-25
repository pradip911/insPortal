package com.myjavablog.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bt.rest.customexception.CustomerAlreadyExistException;
import com.myjavablog.model.Cdr;
import com.myjavablog.model.Mail;
import com.myjavablog.service.CdrService;
//import com.myjavablog.service.EmailService;

@RestController
@CrossOrigin(origins = "*")
public class CdrController {
	private static final Logger logger = LogManager.getLogger(CdrController.class);
	@Autowired
	private CdrService crudService;
	
	//@Autowired
	//private  EmailService emailService;
	
	@Autowired
    private JavaMailSender javaMailSender;

	@GetMapping("/companyList")
	public ResponseEntity<List<Cdr>> getCompanyList() {
		return new ResponseEntity<List<Cdr>>(crudService.getAllCdrsDetails(), HttpStatus.OK);
	}

	@GetMapping("/company/{id}")
	public ResponseEntity<Cdr> getCompany(@PathVariable Long id) {
		return new ResponseEntity<Cdr>(crudService.getCdrById(id), HttpStatus.OK);
	}

	@PostMapping("/company/save")
	public ResponseEntity<Void> saveOrUpdateCompany(@RequestBody Cdr company) {
		Optional<Cdr> cdr=crudService.getCDrByName(company.getName());
		
		if(cdr.isPresent()){
			throw new CustomerAlreadyExistException("Customer Already exist , Please try with diffrent name"+company.getName());
		}
		else{
		crudService.saveOrUpdateCompany(company);
		sendEmail(company.getName());
		}
		return new ResponseEntity<Void>(HttpStatus.OK);				
	}
	
	
	

	@DeleteMapping("/company/delete/{id}")
	public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
		crudService.deleteCompany(id);
		
		//emailService.sendPreConfiguredMail("Deleted Records"+id);
		
		//emailService.sendMail(mail);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	 void sendEmail(String message) {

	        SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo("pradipsahaait@gmail.com");

	        msg.setSubject("User is created");
	        msg.setText("Username created for "+message);

	        javaMailSender.send(msg);

	    }
	 

}
