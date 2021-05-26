package com.wesmart.stark.adapter.application.uts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

@Service
public interface UtsRequester {

	JsonNode getCreditCardInfo(String token) throws JsonProcessingException;

}
