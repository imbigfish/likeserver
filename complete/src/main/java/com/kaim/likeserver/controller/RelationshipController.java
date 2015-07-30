package com.kaim.likeserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaim.likeserver.dao.RelationshipDao;
import com.kaim.likeserver.dao.UserDao;
import com.kaim.likeserver.result.StatusResult;
import com.kaim.likeserver.result.StatusWrapper;

@RestController
@RequestMapping("/relationship")
public class RelationshipController {

	@Autowired
	private UserDao userDao	;

	@Autowired
	private RelationshipDao relationshiDao;

	@RequestMapping(value="/{loginId}/follow/{followerId}/{accessToken}")
	StatusWrapper followUser(@PathVariable String loginId, @PathVariable String followerId, @PathVariable String accessToken)
	{
		StatusResult status = new StatusResult();
		
		if(userDao.checkUserSession(loginId, accessToken))
		{
			if(!relationshiDao.followUser(loginId, followerId))
			{
				status.setResultCode(103);
				status.setResultDesc("Follow failed");
			}

		}
		else
		{
			status.setResultCode(101);
			status.setResultDesc("Token invalid");
		}
		
		return new StatusWrapper(status);
	}
	
	@RequestMapping(value="/{loginId}/unfollow/{followerId}/{accessToken}")
	StatusWrapper unfollowUser(@PathVariable String loginId, @PathVariable String followerId, @PathVariable String accessToken)
	{
		StatusResult status = new StatusResult();
		
		if(userDao.checkUserSession(loginId, accessToken))
		{
			if(!relationshiDao.unfollowUser(loginId, followerId))
			{
				status.setResultCode(103);
				status.setResultDesc("unfollow failed");
			}

		}
		else
		{
			status.setResultCode(101);
			status.setResultDesc("Token invalid");
		}
		
		return new StatusWrapper(status);
	}
}
