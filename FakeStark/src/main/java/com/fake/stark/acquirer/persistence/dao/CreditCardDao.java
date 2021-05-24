package com.fake.stark.acquirer.persistence.dao;

import com.fake.stark.acquirer.entities.CreditCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CreditCardDao {

	CreditCard getCreditCardByUser(@Param("identification") Integer identification);

}
