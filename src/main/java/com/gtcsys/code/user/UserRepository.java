package com.gtcsys.code.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	UserEntity findById(int id);

	UserEntity findByEmail(String email);
}
