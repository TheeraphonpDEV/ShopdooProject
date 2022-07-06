package com.shopdoo.admin.user;

import org.springframework.data.repository.CrudRepository;

import com.shopdoo.common.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{

}
