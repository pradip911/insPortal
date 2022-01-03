package com.myjavablog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myjavablog.model.Cdr;

public interface CdrRepository extends JpaRepository<Cdr, Long>{
	@Query("select u from Cdr u where u.name= :name")
    Optional<Cdr> getCDrByNameInDb(@Param("name") String name);

}
