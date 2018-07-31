package com.marse.martian.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authorities", uniqueConstraints = { @UniqueConstraint(columnNames = { "authority_name" }) })
public class Authority extends AbstractEntity implements GrantedAuthority {
 
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "authority_name", nullable = false, unique = true)
	private String authorityName;

	@NotNull
	@Column(name = "authority_description", nullable = true, updatable = true)
	private String authorityDescription;

	private String getAuthorityName() {
		return authorityName;
	}

	private void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getAuthorityDescription() {
		return authorityDescription;
	}

	public void setAuthorityDescription(String authorityDescription) {
		this.authorityDescription = authorityDescription;
	}

	@Override
	public String getAuthority() {
		return getAuthorityName();
	}

	public void setAuthority(String authority) {
		setAuthorityName(authority);
	}

}
