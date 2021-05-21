package com.wesmart.stark.adapter.application.usecaseproviders;

import com.fasterxml.jackson.databind.JsonNode;
import com.wesmart.stark.adapter.applicationservices.entities.AuthorizationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.AuthorizationResponse;
import org.springframework.stereotype.Service;

@Service
public interface Authorization {

	AuthorizationResponse doAuthorization(AuthorizationMessage authorizationMessage, JsonNode creditCardJson);

}
