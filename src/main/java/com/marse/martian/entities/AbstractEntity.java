package com.marse.martian.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;

import org.springframework.security.core.context.SecurityContextHolder;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public AbstractEntity(Long id) {
		this.Id = id;
	}
	
	public AbstractEntity() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(name = "created_on")
	private Calendar createdOn;

	@Column(name = "updated_on")
	private Calendar updatedOn;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@PrePersist
	public void prePersist() {
		AuthDetail loggedInUsernameObj = (AuthDetail) SecurityContextHolder.getContext().getAuthentication()
				.getDetails();
		String loggedInUsername = loggedInUsernameObj == null ? "SYSTEM" : loggedInUsernameObj.getUsername();
		setCreatedBy(loggedInUsername);
		setCreatedOn(Calendar.getInstance());
		setUpdatedBy(loggedInUsername);
		setUpdatedOn(Calendar.getInstance());

	}

	@PreUpdate
	public void preUpdate() {
		AuthDetail loggedInUsernameObj = (AuthDetail) SecurityContextHolder.getContext().getAuthentication()
				.getDetails();
		String loggedInUsername = loggedInUsernameObj == null ? "SYSTEM" : loggedInUsernameObj.getUsername();
		setUpdatedBy(loggedInUsername);
		setUpdatedOn(Calendar.getInstance());
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public Calendar getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Calendar updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
