package com.fake.stark.acquirer.entities;

import com.fake.stark.acquirer.utils.jsonmappers.PurchaseOrderSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PurchaseOrder entity Class
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = PurchaseOrderSerializer.class)
public class PurchaseOrder {

	/**
	 * The PurchaseOrder id
	 *
	 * @param id of the PurchaseOrder
	 * @return id of the PurchaseOrder
	 */
	private String id;

	/**
	 * The PurchaseOrder status
	 *
	 * @param status of the PurchaseOrder
	 * @return status of the PurchaseOrder
	 */
	private String status;

	/**
	 * The PurchaseOrder description
	 *
	 * @param description of the PurchaseOrder
	 * @return description of the PurchaseOrder
	 */
	private String description;

	/**
	 * The PurchaseOrder shippingAddress
	 *
	 * @param shippingAddress of the PurchaseOrder
	 * @return shippingAddress of the PurchaseOrder
	 */
	private String shippingAddress;

	/**
	 * The PurchaseOrder payment
	 *
	 * @param payment of the PurchaseOrder
	 * @return payment of the PurchaseOrder
	 */
	private Payment payment;

}
