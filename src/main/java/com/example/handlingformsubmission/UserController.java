package com.example.handlingformsubmission;
 
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
@Controller
public class UserController {
	public UserService userService;
	public UserController(UserService userService) {
		this.userService=userService;
	}
 @GetMapping("/viewUser")
	public String viewUser(Model model) {
		return "viewUser";
	}
 @GetMapping("/user_login")
	public String userLogin(Model model) {
		return "user_login";
	}
 
 @GetMapping("/user_signup")
	public String userSignup(Model model) {
		return "user_signup";
	}

	@GetMapping("/user_dashboard")
	public String userDashboard(Model model){
		return "user_dashboard";
	}

	@GetMapping("/booking")
	public String booking(Model model){
		return "booking";
	}

	@GetMapping("/booking_2")
	public String payment(Model model){
		return "booking_2";
	}

	@PostMapping("/user_signup")
	@ResponseBody
	public String confirmSignup(@RequestBody String string) throws ExecutionException, InterruptedException {
		// System.out.println(name);
		String email;
		String mobNo;
		String vehNo;
		String fname;
		String lname;
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode tree = mapper.readTree(string);
            JsonNode node = tree.get("email");
            email = node.textValue();
			node = tree.get("mobNo");
            mobNo = node.textValue();
			node = tree.get("vehNo");
            vehNo = node.textValue();
			node = tree.get("fname");
            fname = node.textValue();
			node = tree.get("lname");
            lname = node.textValue();
			System.out.println(email+" "+mobNo+" "+lname+" "+fname+" "+vehNo);
			User u=new User();
			u.setEmail(email);
			u.setFname(fname);
			u.setLname(lname);
			u.setMobNo(mobNo);
			u.setVehicleNo(vehNo);
			userService.createCRUD(u);
        }
        catch(Exception e){
			e.printStackTrace();
            email = "0.0";
        }
       

		// System.out.println(new UserService().deleteCRUD(name));
		return "confirmSignup";
	}
}