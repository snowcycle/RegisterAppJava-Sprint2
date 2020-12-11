//------------------------------------------------------------
//Jacob Dedman
//Created TransactionRepository.java

package edu.uark.registerapp.models.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import edu.uark.registerapp.models.entities.TransactionEntity;

public interface TransactionRepository extends CrudRepository<TransactionEntity, UUID> {
	Optional<TransactionEntity> findById(UUID id);
	Optional<TransactionEntity> findByLookupCode(String lookupCode);
}