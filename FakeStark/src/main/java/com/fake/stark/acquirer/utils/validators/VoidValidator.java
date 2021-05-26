package com.fake.stark.acquirer.utils.validators;

import com.fake.stark.acquirer.entities.PurchaseOrder;
import com.fake.stark.acquirer.services.TransactionStates;
import org.springframework.stereotype.Service;

/**
 * Defines the structure for an void validator
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public interface VoidValidator {

	/**
	 * Check the validity of an purchase order of type void
	 *
	 * @param purchaseOrder - The PurchaseOrder to be processed
	 * @return The state of the PurchaseOrder after the validation
	 */
	TransactionStates validateVoid(final PurchaseOrder purchaseOrder);

}
