package com.fake.stark.acquirer.persistence.dao;

import java.util.List;

import com.fake.stark.acquirer.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

	Payment getPaymentById(@Param("id") String id);

	void insertPayment(@Param("payment") Payment payment);

	List<Payment> getAll();

	void updatePayment(@Param("payment") Payment payment);

}
