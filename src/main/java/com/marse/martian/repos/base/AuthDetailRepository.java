package com.marse.martian.repos.base;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marse.martian.entities.AuthDetail;

@Repository
public interface AuthDetailRepository extends BaseRepository<AuthDetail, Long> {

	Optional<AuthDetail> findOneByUsername(String username);

	@Modifying
	@Query(nativeQuery = true, value = "update auth_detail set password = :password where user_name = :userName")
	void updatePassword(@Param("password") String password, @Param("userName") String userName);

}
