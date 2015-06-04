package org.wildcards.springboot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wildcards.springboot.domain.model.MembershipCardRequest;

public interface MembershipCardRequestRepository extends JpaRepository<MembershipCardRequest, Long>{

}
