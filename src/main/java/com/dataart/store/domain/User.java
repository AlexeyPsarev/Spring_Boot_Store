package com.dataart.store.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User
{
	public User() 
	{
	}

	public User(String username, String password, String fullname)
	{
		this.username = username;
		this.password = password;
		this.fullname = fullname;
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getFullname()
	{
		return fullname;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public void setUsername(String value)
	{
		username = value;
	}
	
	public void setPassword(String value)
	{
		password = value;
	}
	
	public void setFullname(String value)
	{
		fullname = value;
	}
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password", columnDefinition = "CHAR(80)")
	private String password;
	
	@Column(name = "full_name")
	private String fullname;
}
