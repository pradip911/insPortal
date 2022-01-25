package com.myjavablog.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.myjavablog.model.Cdr;
import com.myjavablog.model.Customer;
import com.myjavablog.model.InsDetails;
import com.myjavablog.model.PaymentHistory;
import com.myjavablog.model.User;
import com.myjavablog.repository.PaymentDashboardRepo;
import com.myjavablog.repository.RoleRepoJDBCTemplate;
import com.myjavablog.service.CustomerService;
import com.myjavablog.service.PaymentDashboardService;
import com.myjavablog.service.UserService;

@Controller
//@RequestMapping({ "/home", "/contact" })
public class UserController {
	
	private static final Logger logger = LogManager.getLogger(UserController.class);


    @Autowired
    private UserService userService;
    
    @Autowired
    private CustomerService custService;
    
    
    
    @Autowired
    PaymentDashboardService paymentDashboardService;
    
    @Autowired
    private RoleRepoJDBCTemplate roleRepoJdbcTemplate;
    
    
    @Autowired
    PaymentDashboardRepo paymentRepo;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }
    
    
    @RequestMapping(value="/newPolicyPage", method = RequestMethod.GET)
    public ModelAndView newPagePolicy(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/policyCreate");
        return modelAndView;
    }
    
    
    

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @RequestMapping(value="/admin/adminHome", method = RequestMethod.GET)
    public ModelAndView home(){
    	ModelAndView modelAndView = new ModelAndView();
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User user = userService.findUserByEmail(auth.getName());
    	System.out.println("auth name "+auth.getName());
    	if(auth.getName().contains("ins")) {
    		//roleRepoJdbcTemplate
    		List<Customer> listCustomer=custService.getAllCustomer();
    		System.out.println("customer Size !!!"+listCustomer.size());
    		modelAndView.addObject("listCustomer",listCustomer);
    		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
    		modelAndView.addObject("adminMessage","This Page is available to Users with Admin Role");
    		modelAndView.setViewName("admin/adminHome");
    	}
    	else {
    		List<Customer> listCustomer=custService.getAllCustomer();
    		System.out.println("customer Size !!!"+listCustomer.size());
    		modelAndView.addObject("listCustomer",listCustomer);
    		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
    		modelAndView.addObject("adminMessage","This Page is available to Users with Admin Role");
    		modelAndView.setViewName("admin/userHome");
    	}

    	return modelAndView;
    }
    
   
    @RequestMapping("/paymentLink")
    public ModelAndView paymentLink() {
    	ModelAndView modelAndView = new ModelAndView();
    	
    	List<PaymentHistory> paymentHostory=paymentRepo.findAll();
    	
    	for(PaymentHistory payHis:paymentHostory) {
    		
    		payHis.setPaymentDate(new Date());
    		payHis.setPaymentSuccess("Done");
    		paymentRepo.save(payHis);
    	}
    			
    	modelAndView.setViewName("user/paymentDashboard");
		return modelAndView;
    	
    }
    
    @RequestMapping("/new")
    public ModelAndView newCustomerForm() {
    	 ModelAndView modelAndView = new ModelAndView();
    	// System.out.println("username"+userName);
       // Customer customer = new Customer();
       //  model.put("customer", customer);
       // modelAndView.addAllObjects("customer",customer);
        modelAndView.setViewName("admin/newcustomer");
        return modelAndView;
    }
    
    @RequestMapping("/insuranceDetails")
    public ModelAndView insuranceDetails() {
    	ModelAndView modelAndView = new ModelAndView();
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	//auth.getName()
    	System.out.println("Auth name :"+auth.getName());
    	
    	List<InsDetails> insDetails=userService.getInsDetails(auth.getName());
    	modelAndView.addObject("insDetails",insDetails);
    	modelAndView.setViewName("user/insuranceDetails");
    	return modelAndView;
    }
    
    @RequestMapping("/paymenyPolicy")
    public ModelAndView paymentDetails() {
    	ModelAndView modelAndView = new ModelAndView();
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	//auth.getName()
    	//System.out.println("Auth name :"+auth.getName());
    	List<PaymentHistory> paymentHistory=paymentDashboardService.getPendingPayments(auth.getName());
    	System.out.println("Payment History size"+paymentHistory.size());
    	//modelAndView.addObject("username",auth.getName());
    	//modelAndView.addObject("paymentsize",paymentHistory.size());
    	modelAndView.addObject("paymentHistory",paymentHistory);
    	modelAndView.setViewName("user/paymentDashboard");
    	return modelAndView;
    }
    
    @RequestMapping("/InsuranceRegistration")
    public ModelAndView newInsuranceForm() {
    	 ModelAndView modelAndView = new ModelAndView();
      
        modelAndView.setViewName("InsuranceRegistration");
        return modelAndView;
    }
    
    @RequestMapping("/cdrinfo")
    public ModelAndView newCdrInfo() {
    	 ModelAndView modelAndView = new ModelAndView();
       // Customer customer = new Customer();
       //  model.put("customer", customer);
       // modelAndView.addAllObjects("customer",customer);
        modelAndView.setViewName("admin/ajax_crud");
        return modelAndView;
    }
    
    @RequestMapping("/delete")
    public ModelAndView updatePaymentInfo() {
    	 ModelAndView modelAndView = new ModelAndView();
       // Customer customer = new Customer();
       //  model.put("customer", customer);
       // modelAndView.addObject("paymentId",id);
        modelAndView.setViewName("user/payment");
        return modelAndView;
    }
    
    
    

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
    	custService.save(customer);
        return "redirect:/";
    	//return /admin/adminHome
    }
    @RequestMapping(value="/user/userHome", method = RequestMethod.GET)
    public ModelAndView user(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("userMessage","This Page is available to Users with User Role");
        modelAndView.setViewName("user/userHome");
        return modelAndView;
    }
    
    @RequestMapping(value = "/student/info", method = RequestMethod.PUT)
    public @ResponseBody String updateStudent(@RequestBody Cdr cdr){
     //LOG.info(student.toString());
     return "ok";
    }
    

}
