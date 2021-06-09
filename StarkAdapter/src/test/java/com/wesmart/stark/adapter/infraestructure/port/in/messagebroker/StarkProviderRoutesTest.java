package com.wesmart.stark.adapter.infraestructure.port.in.messagebroker;

import static org.assertj.core.api.Assertions.assertThat;

import com.wesmart.stark.adapter.application.in.handler.StarkAdapterHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StarkProviderRoutesTest {

	@InjectMocks
	StarkProviderRoutes starkProviderRoutes;

	@Mock
	StarkAdapterHandler starkAdapterHandler;

	@Test
	void shouldHaveStarkProviderRoutes() {

		assertThat(starkProviderRoutes)
				.isNotNull()
				.hasFieldOrPropertyWithValue("starkAdapterHandler", starkAdapterHandler);
	}
}
