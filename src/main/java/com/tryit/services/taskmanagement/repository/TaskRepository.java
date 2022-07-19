package com.tryit.services.taskmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tryit.services.taskmanagement.models.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{
	@Query(value="SELECT * FROM task  WHERE status != 'deleted'",nativeQuery = true)
	public List<Task> findAll();
}
