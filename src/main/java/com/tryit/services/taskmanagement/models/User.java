package com.tryit.services.taskmanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Entity
@Data
public class User {
	@Id
	@Column(nullable = false, length = 100)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 100)
	private String username;
	@Column(nullable = false, length = 100)
	private String password;
}
