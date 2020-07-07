package com.platform.testbase;

import java.util.List;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import javax.annotation.Resource;
import java.math.BigDecimal;
import com.platform.dal.mapper.platform.*;
import com.platform.dal.model.platform.*;

/**
 * @author RayLiu
 * Created on 2020/07/07.
 */
@Service
public class PlatformTestBase {

	@Resource
	UserMapper userMapper;

    /**
	 * 插入user表数据
	 */
	public void insertUser(User user) {
	userMapper.insert(user);
	}

    /**
	 * 插入user表数据
	 */
	public void insertUser(
		Integer id, 
		String mobile, 
		String password, 
		String email, 
		String status, 
		String role, 
		Date creatAt
	) {
		if (creatAt == null) {
			creatAt = new Date();
		}
	User user = new User();
			user.setId(id);
			user.setMobile(mobile);
			user.setPassword(password);
			user.setEmail(email);
			user.setStatus(status);
			user.setRole(role);
			user.setCreatAt(creatAt);
	userMapper.insert(user);
	}


	/**
	 * 根据id删除user表数据
	 */
	public void cleanUserById(Integer id) {
			UserExample exam = new UserExample();
		exam.createCriteria().andIdEqualTo(id);
			userMapper.deleteByExample(exam);
	}


    /**
	 * 根据id查询user表数据
	 */
	public List<User> findUserById(Integer id) {
			UserExample exam = new UserExample();
		exam.createCriteria().andIdEqualTo(id);
		return userMapper.selectByExample(exam);
	}

    /**
	 * 根据id更新user表数据
	 */
	public void updateUserById(Integer id,User user) {
			UserExample exam = new UserExample();
		exam.createCriteria().andIdEqualTo(id);
			userMapper.updateByExampleSelective(user, exam);
	}

}
