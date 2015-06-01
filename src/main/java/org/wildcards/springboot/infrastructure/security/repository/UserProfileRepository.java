package org.wildcards.springboot.infrastructure.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wildcards.springboot.infrastructure.security.model.UserProfile;

/**
 * 
 * @author jojo
 *
 */
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

	UserProfile findByUsernameEquals(String username);

}
