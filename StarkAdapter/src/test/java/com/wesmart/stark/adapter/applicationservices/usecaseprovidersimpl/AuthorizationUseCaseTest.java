package com.wesmart.stark.adapter.applicationservices.usecaseprovidersimpl;

import static org.assertj.core.api.Assertions.assertThat;

import com.wesmart.stark.adapter.infraestructure.port.out.webserviceclient.StarkRestClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * AuthorizationUseCaseTest class to test the AuthorizationUseCase class
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@ExtendWith(MockitoExtension.class)
class AuthorizationUseCaseTest {

	@InjectMocks
	AuthorizationUseCase authorizationUseCase;

	@Mock
	StarkRestClient starkRestClient;

	/**
	 * Test to ensure the correct dependency injection
	 */
	@Test
	void shouldHaveStarkRestClient() {

		assertThat(authorizationUseCase)
				.isNotNull()
				.hasFieldOrPropertyWithValue("starkRestClient", starkRestClient);
	}

}
