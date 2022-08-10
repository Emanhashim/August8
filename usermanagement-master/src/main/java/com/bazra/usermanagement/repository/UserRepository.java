package com.bazra.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bazra.usermanagement.model.UserInfo;

/**
 * User Repository
 * @author Bemnet
 * @version 4/2022
 */
@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<UserInfo, Integer> {
//     Optional<UserInfo> findById(Integer id);
	 Optional<UserInfo> findByRoles(String roles);
	 Optional<UserInfo> findByUsername(String username);
	 Optional<UserInfo> findByEmail(String username);
	 Optional<UserInfo> findByResetPasswordToken(String resetPasswordToken);
	 

}
