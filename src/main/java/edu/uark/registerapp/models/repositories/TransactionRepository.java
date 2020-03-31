package edu.uark.registerapp.models.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import edu.uark.registerapp.models.entities.TransactionEntity;

public interface TransactionRepository extends CrudRepository<TransactionEntity, UUID> {
	Optional<TransactionEntity> findById(UUID id);
	List<TransactionEntity> findByCashierId(UUID cashierId);
}
