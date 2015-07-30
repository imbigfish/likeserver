package com.kaim.likeserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kaim.likeserver.dao.UserDao;
import com.kaim.likeserver.dto.UserInfo;
import com.kaim.likeserver.result.SingleUser;
import com.kaim.likeserver.result.StatusResult;
import com.kaim.likeserver.result.UserResult;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserDao userDao;

	@RequestMapping(method=RequestMethod.POST)
    public UserInfo createUser(@RequestBody UserInfo user) {
    	return userDao.createUser(user);
    }
    
	@RequestMapping(value="/login/{loginId}/{password}", method=RequestMethod.GET)
    public SingleUser loginUserByPassord(@PathVariable String loginId, @PathVariable String password){
		UserInfo userInfo = userDao.loginUser(loginId, password);
		
		if(userInfo != null)
		{
			return new SingleUser(userInfo, new StatusResult());
		}
		else
		{
			return new SingleUser(new UserInfo(), new StatusResult(101, "login failed"));
			
		}
    }

	@RequestMapping(value="/{loginId}/token/{token}", method=RequestMethod.GET)
    public SingleUser loginUserByToken(@PathVariable String loginId, @PathVariable String token){
		UserInfo userInfo = userDao.loginUsingSession(loginId, token);
		
		if(userInfo != null)
		{
			return new SingleUser(userInfo, new StatusResult());
		}
		else
		{
			return new SingleUser(new UserInfo(), new StatusResult(101, "login failed"));
			
		}
    }
	
	@RequestMapping(value="/{loginId}/follower/{page}/{token}", method=RequestMethod.GET)
	public UserResult getFollower(@PathVariable String loginId, @PathVariable Integer page, @PathVariable String token)
	{
		if(userDao.checkUserSession(loginId, token))
		{
			return userDao.getUserFollower(loginId, page);
		}
		else
		{
			return new UserResult(new StatusResult(1001, "Invalid Token"));
		}
	}

	@RequestMapping(value="/{loginId}/following/{page}/{token}", method=RequestMethod.GET)
	public UserResult getFollowing(@PathVariable String loginId, @PathVariable Integer page, @PathVariable String token)
	{
		if(userDao.checkUserSession(loginId, token))
		{
			return userDao.getUserFollowing(loginId, page);
		}
		else
		{
			return new UserResult(new StatusResult(1001, "Invalid Token"));
		}
	}
	
	@RequestMapping(value="/logout/{loginId}/{token}", method=RequestMethod.GET)
	public UserResult logoutUser(@PathVariable String loginId, @PathVariable String token)
	{
		if(userDao.logoutUser(loginId, token))
		{
			return new UserResult(new StatusResult());
		}
		else
		{
			return new UserResult(new StatusResult(1002, "Logout failed"));
		}
	}
}
