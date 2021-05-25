package com.fake.stark.acquirer.utils.validators;

import com.fake.stark.acquirer.entities.PurchaseOrder;
import com.fake.stark.acquirer.services.TransactionStates;
import org.springframework.stereotype.Service;

@Service
public interface AuthorizationValidator {

	TransactionStates validateAuthorization(final PurchaseOrder purchaseOrder);

}
