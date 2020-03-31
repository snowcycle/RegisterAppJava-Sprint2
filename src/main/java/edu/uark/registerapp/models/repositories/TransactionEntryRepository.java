package edu.uark.registerapp.models.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import edu.uark.registerapp.models.entities.TransactionEntryEntity;

public interface TransactionEntryRepository extends CrudRepository<TransactionEntryEntity, UUID> {
	Optional<TransactionEntryEntity> findById(UUID id);
	List<TransactionEntryEntity> findByProductId(UUID productId);
	List<TransactionEntryEntity> findByTransactionId(UUID transactionId);
	Optional<TransactionEntryEntity> findByTransactionIdAndProductId(UUID transactionId, UUID productId);
}
