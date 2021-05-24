package com.fake.stark.acquirer.persistence.dao;

import com.fake.stark.acquirer.entities.PurchaseOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PurchaseOrderDao {

	PurchaseOrder getPurchaseOrderById(@Param("id") String id);

	void insertPurchaseOrder(@Param("purchaseOrder") PurchaseOrder purchaseOrder);

	void updatePurchaseOrder(@Param("purchaseOrder") PurchaseOrder purchaseOrder);

}
