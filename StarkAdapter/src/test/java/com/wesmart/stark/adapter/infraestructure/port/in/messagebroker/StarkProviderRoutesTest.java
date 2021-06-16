package com.wesmart.stark.adapter.infraestructure.port.in.messagebroker;

import static org.assertj.core.api.Assertions.assertThat;

import com.wesmart.stark.adapter.application.in.handler.StarkAdapterHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * StarkProviderRoutesTest class to test the StarkProviderRoutes class
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@ExtendWith(MockitoExtension.class)
class StarkProviderRoutesTest {

	@InjectMocks
	StarkProviderRoutes starkProviderRoutes;

	@Mock
	StarkAdapterHandler starkAdapterHandler;

	/**
	 * Test to ensure the correct dependency injection
	 */
	@Test
	void shouldHaveStarkProviderRoutes() {

		assertThat(starkProviderRoutes)
				.isNotNull()
				.hasFieldOrPropertyWithValue("starkAdapterHandler", starkAdapterHandler);
	}
}
