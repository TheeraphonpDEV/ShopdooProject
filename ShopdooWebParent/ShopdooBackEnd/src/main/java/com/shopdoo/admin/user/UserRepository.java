package com.shopdoo.admin.user;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.shopdoo.common.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer>{
		@Query("SELECT u FROM User u WHERE u.email = :email")
		public User getUserByEmail(@Param("email") String email);
		
		public Long countById(Integer id);
		@Query("UPDATE  User u SET u.enabled = ?2 WHERE u.id = ?1")
		@Modifying
		public  void updateEnabledStatus(Integer id, boolean enabled);

}
