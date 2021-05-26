package com.fake.stark.acquirer.persistence.dao;

import com.fake.stark.acquirer.entities.PurchaseOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Defines the structure for PurchaseOrderDao service
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Mapper
public interface PurchaseOrderDao {

	/**
	 * Get the PurchaseOrder in the DB that match with the id sent
	 *
	 * @param id - Id of the purchase order to be searched
	 * @return - The PurchaseOrder found in the DB that has been found using the id
	 */
	PurchaseOrder getPurchaseOrderById(@Param("id") String id);

	/**
	 * Insert in the DB a PurchaseOrder
	 *
	 * @param purchaseOrder - The PurchaseOrder to be inserted in the DB
	 */
	void insertPurchaseOrder(@Param("purchaseOrder") PurchaseOrder purchaseOrder);

	/**
	 * Update a PurchaseOrder in the DB
	 *
	 * @param purchaseOrder - PurchaseOrder with the info to be updated
	 */
	void updatePurchaseOrder(@Param("purchaseOrder") PurchaseOrder purchaseOrder);

}
