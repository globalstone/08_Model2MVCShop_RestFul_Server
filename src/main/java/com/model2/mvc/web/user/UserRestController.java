package com.model2.mvc.web.user;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;

import java.util.Map;


//==> 회원관리 RestController
@RestController
@RequestMapping("/user/*")
public class UserRestController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	//setter Method 구현 않음
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
		
	public UserRestController(){
		System.out.println(this.getClass());
	}
	
	@RequestMapping( value="json/getUser/{userId}", method=RequestMethod.GET )
	public User getUser( @PathVariable String userId ) throws Exception{
		
		System.out.println("/user/json/getUser : GET");
		
		//Business Logic
		User user = userService.getUser(userId);
		return user;
	}
//=======================================================================================================
	@RequestMapping( value="json/login", method=RequestMethod.POST )
	public User login(	@RequestBody User user,
									HttpSession session ) throws Exception{
	
		System.out.println("/user/json/login : POST");
		//Business Logic
		System.out.println("::"+user);
		User dbUser=userService.getUser(user.getUserId());
		
		if( user.getPassword().equals(dbUser.getPassword())){
			session.setAttribute("user", dbUser);
		}
		
		return dbUser;
	}
//=======================================================================================================
	@RequestMapping( value="json/checkDuplication/{userId}", method=RequestMethod.POST )
	public boolean checkDuplication(@PathVariable String userId) throws Exception{

		System.out.println("/user/json/checkDuplication : POST");
		//Business Logic

		return userService.checkDuplication(userId);
    }
//=======================================================================================================
	@RequestMapping( value="json/listUser" )
	public Map<String, Object> listUser(@ModelAttribute("search") Search search) throws Exception{

		System.out.println("/user/listUser : GET / POST");

		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		// Business logic 수행
		Map<String , Object> map = userService.getUserList(search);

		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);

		// Model 과 View 연결
		map.put("resultPage", resultPage);
		map.put("userlist", map.get("list"));
		map.put("search", search);
		System.out.println(map);

		return map;
	}
//=======================================================================================================
	@RequestMapping( value="json/addUser", method=RequestMethod.POST )
	public User addUser( @RequestBody User user ) throws Exception {

		System.out.println("/user/addUser : POST");
		//Business Logic
		userService.addUser(user);

		return user;
	}
//=======================================================================================================
	@PostMapping(value="json/updateUser/{userId}")
	public User updateUser(@PathVariable String userId, @RequestBody User user) throws Exception {
		System.out.println("/user/updateUser : POST");
		//Business Logic
		userService.updateUser(user);
		return user;
	}
//=======================================================================================================
	@RequestMapping(value="json/logout", method=RequestMethod.GET)
	public boolean logout() throws Exception{
		System.out.println("/user/json/logout : GET");
		return true;
	}
}