  
package edu.uark.registerapp.commands.transactions;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.models.api.Transaction;
import edu.uark.registerapp.models.entities.TransactionEntity;
import edu.uark.registerapp.models.repositories.TransactionRepository;

@Service
public class TransactionsQuery implements ResultCommandInterface<List<Transaction>> {
	@Override
	public List<Transaction> execute() {
		final LinkedList<Transaction> transactions = new LinkedList<Transaction>();

		for (final TransactionEntity transactionEntity : transactionRepository.findAll()) {
			transactions.addLast(new Transaction(transactionEntity));
		}
		
		return transactions;
	}

	@Autowired
	TransactionRepository transactionRepository;
}