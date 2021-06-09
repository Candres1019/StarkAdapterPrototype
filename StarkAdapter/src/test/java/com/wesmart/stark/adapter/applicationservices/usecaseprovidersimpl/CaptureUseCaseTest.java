package com.wesmart.stark.adapter.applicationservices.usecaseprovidersimpl;

import static org.assertj.core.api.Assertions.assertThat;

import com.wesmart.stark.adapter.infraestructure.port.out.webserviceclient.StarkRestClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CaptureUseCaseTest {

	@InjectMocks
	CaptureUseCase captureUseCase;

	@Mock
	StarkRestClient starkRestClient;

	@Test
	void shouldHaveStarkRestClient() {

		assertThat(captureUseCase)
				.isNotNull()
				.hasFieldOrPropertyWithValue("starkRestClient", starkRestClient);
	}

}
