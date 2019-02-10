package com.challenge.idincrement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.challenge.idincrement.entity.IdEntity;

public interface IdEntityRepository extends JpaRepository<IdEntity, String> {

	@Query("SELECT apiKey FROM IdEntity AS ie where ie.email = :email and ie.password = :password and ie.tableName = :tableName")
	String findApiKeyByEmailPasswordTablename(@Param("email") String email, @Param("password") String password, @Param("tableName") String tableName);

}
