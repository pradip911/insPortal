package com.myjavablog.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bt.rest.customexception.CustomerAlreadyExistException;
import com.myjavablog.model.NewPolicyEntity;
import com.myjavablog.model.PolicyEntity;
import com.myjavablog.service.PolicyInterface;

@RestController
@CrossOrigin(origins = "*")
public class PolicyController {
	private static final Logger logger = LogManager.getLogger(PolicyController.class);
	@Autowired
	private PolicyInterface crudService;
	
	@Autowired
    RestTemplate restTemplate;
	
	//@Autowired
	//private  EmailService emailService;
	
	@Autowired
    private JavaMailSender javaMailSender;

	@GetMapping("/policyList")
	public ResponseEntity<List<PolicyEntity>> getPolicyDetails() {
		return new ResponseEntity<List<PolicyEntity>>(crudService.getAllCdrsDetails(), HttpStatus.OK);
	}
	
	
	@GetMapping("/policyInfo/{id}")
	public ResponseEntity<Optional<PolicyEntity>> getPolicyDetailsById(@PathVariable Integer id) {
		return new ResponseEntity<Optional<PolicyEntity>>(crudService.getCdrById(id), HttpStatus.OK);
	}
	
	@PostMapping("/policyInfoPersist")
	public ResponseEntity<Void> SavePolicyInfo(@RequestBody PolicyEntity policyEntity) {
		PolicyEntity plentity=crudService.getCdrByContactNo(policyEntity.getPolicyHolderContactNo());
		if(null!=plentity)
		{
			throw new CustomerAlreadyExistException("Plicy already exist in DB");
			//crudService.saveOrUpdateCompany(policyEntity);
			//sendEmail("Policy Created with Id"+policyEntity.getId());
		}
		else {
			crudService.saveOrUpdateCompany(policyEntity);
			sendEmail("Policy Created with Id"+policyEntity.getId());
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/newPolicyCreate", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Void> createPolicy(@RequestBody NewPolicyEntity policyEntity) {
		
		restTemplate.postForObject("http://eurekaclientAccount/newInsurance/createUser", policyEntity, NewPolicyEntity.class);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
		//return new ResponseEntity<List<PolicyEntity>>(crudService.getAllCdrsDetails(), HttpStatus.OK);
	}
	@DeleteMapping("/deletePolicy")
	public ResponseEntity<Void> deletePolicy(@PathVariable Integer id)
	{
		crudService.deleteCompany(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	 void sendEmail(String message) {

	        SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo("pradipsahaait@gmail.com");

	        msg.setSubject("User is created");
	        msg.setText("Username created for "+message);

	        javaMailSender.send(msg);

	    }
	 @Bean
	    @LoadBalanced
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }
}
