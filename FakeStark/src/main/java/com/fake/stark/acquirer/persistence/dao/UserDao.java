package com.fake.stark.acquirer.persistence.dao;

import com.fake.stark.acquirer.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Defines the structure for UserDao service
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Mapper
public interface UserDao {

	/**
	 * Get the user in the DB that match with the id sent
	 *
	 * @param identification - id identification of the user to be searched
	 * @return - The user that has been found using the id
	 */
	User getUserById(@Param("identification") Integer identification);

}
