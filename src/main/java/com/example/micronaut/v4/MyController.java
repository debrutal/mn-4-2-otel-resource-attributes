package com.example.micronaut.v4;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.problem.HttpStatusType;
import org.zalando.problem.Problem;
import org.zalando.problem.ThrowableProblem;

import java.net.URI;

@Controller
public class MyController {

    @Get
    public String doesFail() throws ThrowableProblem {
        throw Problem.builder()
            .withType(URI.create("https://example.org/out-of-stock"))
            .withTitle("Out of Stock")
            .withStatus(new HttpStatusType(HttpStatus.BAD_REQUEST))
            .withDetail("Item B00027Y5QG is no longer available")
            .with("product", "B00027Y5QG")
            .build();
    }

}
