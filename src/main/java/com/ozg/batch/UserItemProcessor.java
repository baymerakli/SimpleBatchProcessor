package com.ozg.batch;

import java.util.Map;

import org.springframework.batch.item.ItemProcessor;

import com.ozg.batch.model.User;

public class UserItemProcessor implements ItemProcessor<Map<String, Object>, User> {

    @Override
    public User process(Map<String, Object> s) throws Exception {
	User user = new User();
	user.setFirstName((String) s.get("firstName"));
	user.setLastName((String) s.get("lastName"));
	return user;
    }

}