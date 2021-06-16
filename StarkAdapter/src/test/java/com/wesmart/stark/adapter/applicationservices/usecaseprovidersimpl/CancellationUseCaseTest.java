package com.wesmart.stark.adapter.applicationservices.usecaseprovidersimpl;

import static org.assertj.core.api.Assertions.assertThat;

import com.wesmart.stark.adapter.infraestructure.port.out.webserviceclient.StarkRestClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * CancellationUseCaseTest class to test the CancellationUseCase class
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@ExtendWith(MockitoExtension.class)
class CancellationUseCaseTest {

	@InjectMocks
	CancellationUseCase cancellationUseCase;

	@Mock
	StarkRestClient starkRestClient;

	/**
	 * Test to ensure the correct dependency injection
	 */
	@Test
	void shouldHaveStarkRestClient() {

		assertThat(cancellationUseCase)
				.isNotNull()
				.hasFieldOrPropertyWithValue("starkRestClient", starkRestClient);
	}
}
