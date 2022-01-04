package com.myjavablog.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myjavablog.model.InsDetails;
import com.myjavablog.model.Role;
import com.myjavablog.model.User;
import com.myjavablog.repository.InsuranceDetailsRepo;
import com.myjavablog.repository.RoleRepository;
import com.myjavablog.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    InsuranceDetailsRepo insuranceDetailsRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
    	//Role userRole=null;
    	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    	user.setActive(1);
    	Role userRole = roleRepository.findByRole("ADMIN");
    	//System.out.println("email addres is "+user.getEmail());
    	//if(user.getEmail().contains("ins")) {
    	//	userRole = roleRepository.findByRole("ADMIN");
    	//}
    	//else {
    	//	System.out.println("user section !!!");
    	//	userRole = roleRepository.findByRole("USER");	
    	//}
    	user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
    	return userRepository.save(user);
    }

	@Override
	public List<InsDetails> getInsDetails(String insEmail) {
		// TODO Auto-generated method stub
		return insuranceDetailsRepo.getInsByNameInDb(insEmail);
	}
}
