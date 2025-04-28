package com.example;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import java.util.Optional;

public class HttpTriggerFunction {
    @FunctionName("printRequestBody")
    public HttpResponseMessage run(
        @HttpTrigger(name = "req",
                     methods = {HttpMethod.POST},
                     authLevel = AuthorizationLevel.ANONYMOUS)
        HttpRequestMessage<Optional<String>> request,
        final ExecutionContext context
    ) {
        String body = request.getBody().orElse("No body");
        context.getLogger().info("Received request body: " + body);
        return request.createResponseBuilder(HttpStatus.OK)
                      .body("Received: " + body)
                      .build();
    }
}
