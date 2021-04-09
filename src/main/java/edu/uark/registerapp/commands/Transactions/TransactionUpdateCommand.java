package edu.uark.registerapp.commands.transactions;

import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.commands.exceptions.UnprocessableEntityException;
import edu.uark.registerapp.models.api.Transaction;  //Transaction capitalized by Jacob
import edu.uark.registerapp.models.entities.TransactionEntity;
import edu.uark.registerapp.models.repositories.TransactionRepository;

@Service
public class transactionUpdateCommand implements ResultCommandInterface<transaction> {
	@Transactional
	@Override
	public transaction execute() {
		this.validateProperties();

		final Optional<transactionEntity> transactionEntity =
			this.transactionRepository.findById(this.transactionId);
		if (!transactionEntity.isPresent()) { // No record with the associated record ID exists in the database.
			throw new NotFoundException("transaction");
		}

		// Synchronize any incoming changes for UPDATE to the database.
		this.apitransaction = transactionEntity.get().synchronize(this.apitransaction);

		// Write, via an UPDATE, any changes to the database.
		this.transactionRepository.save(transactionEntity.get());

		return this.apitransaction;
	}

	// Helper methods
	private void validateProperties() {
		if (StringUtils.isBlank(this.apitransaction.getLookupCode())) {
			throw new UnprocessableEntityException("lookupcode");
		}
	}

	// Properties
	private UUID transactionId;
	public UUID gettransactionId() {
		return this.transactionId;
	}
	public transactionUpdateCommand settransactionId(final UUID transactionId) {
		this.transactionId = transactionId;
		return this;
	}

	private transaction apitransaction;
	public transaction getApitransaction() {
		return this.apitransaction;
	}
	public transactionUpdateCommand setApitransaction(final transaction apitransaction) {
		this.apitransaction = apitransaction;
		return this;
	}
	
	@Autowired
	private TransactionRepository transactionRepository;		//Transaction capitalized by Jacob
}