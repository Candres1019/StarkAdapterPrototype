package com.fake.stark.acquirer.persistence.dao;

import com.fake.stark.acquirer.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

	User getUserById(@Param("identification") Integer identification);

}
