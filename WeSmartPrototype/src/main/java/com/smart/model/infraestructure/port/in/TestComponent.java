package com.smart.model.infraestructure.port.in;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.model.entities.Customer;
import com.smart.model.entities.PaymentOrder;
import com.smart.model.entities.Seller;
import com.smart.model.entities.Transaction;
import org.springframework.stereotype.Component;

//@Component
public class TestComponent {

	public TestComponent() {

		Date date = new GregorianCalendar(2021, Calendar.MAY, 20).getTime();
		Customer customer = new Customer(1019151322, "CC", "CustomerName", "CustomerLastName", "customer@mail.com", "+57 3174414419",
										 "Cl 5 No 1B - 72", date, date);
		Seller seller = new Seller(1, "Real Madrid", "712361-21", "5423", "+3 123131312", "real.madrid@mail.com", "La castellana", date,
								   date);
		PaymentOrder paymentOrder = new PaymentOrder(UUID.randomUUID(), "JAGSGB3-3131", "Test payment order", "Cl 5 No 1B - 72", date,
													 customer, seller);
		Transaction transaction = new Transaction(UUID.randomUUID(), "a560358a-1078-49e5-b426-fc397de8617b", "authorization", "COP",
												  "InVerification", 119000.0, 19000.0, 100000.0, date, date, paymentOrder);
		try {
			String serialized = new ObjectMapper().writeValueAsString(transaction);
			System.out.println(serialized);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
