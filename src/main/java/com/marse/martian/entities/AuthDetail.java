package com.marse.martian.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

@Entity
@Table(name = "auth_detail")
@XmlRootElement
public class AuthDetail extends AbstractEntity implements UserDetails {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "user_name", nullable = false, unique = true)
	private String username;

	@NotNull
	@Column(name = "password", nullable = false, unique = true)
	private String password;

	@NotNull
	@Column(name = "is_enabled", nullable = false)
	private Boolean enabled;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_authorities", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "ID", nullable = false), inverseJoinColumns = @JoinColumn(name = "auth_id", referencedColumnName = "ID", unique = false))
	private Collection<Authority> authorities;

	public AuthDetail() {
	}

	public AuthDetail(String username, String password, Boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}
	 
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public Collection<Authority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setAuthority(Collection<Authority> authorities) {
		this.authorities = authorities;
	}

	public List<String> getAuthoritiesString() {
		List<String> authNames = null;
		if (!CollectionUtils.isEmpty(getAuthorities())) {
			authNames = new ArrayList<String>();
			for (Authority authDto : getAuthorities()) {
				authNames.add(authDto.getAuthority());
			}
		}
		return authNames;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		AuthDetail user = (AuthDetail) o;

		if (!getId().equals(user.getId()))
			return false;
		if (!username.equals(user.username))
			return false;
		if (!password.equals(user.password))
			return false;
		return enabled.equals(user.enabled);

	}

	@Override
	public int hashCode() {
		int result = getId().hashCode();
		result = 31 * result + username.hashCode();
		result = 31 * result + password.hashCode();
		result = 31 * result + enabled.hashCode();
		return result;
	}
}
