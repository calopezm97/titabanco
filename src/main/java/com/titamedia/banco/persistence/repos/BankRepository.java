package com.titamedia.banco.persistence.repos;

import com.titamedia.banco.persistence.entities.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, Long> {
}
