package com.tryit.services.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tryit.services.taskmanagement.models.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	@Query(value="SELECT * FROM user where user.username= :username",nativeQuery = true)
	public User findUserByname(String username);
}
