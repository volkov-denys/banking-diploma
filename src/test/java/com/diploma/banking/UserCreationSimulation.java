package com.diploma.banking;

import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.core.CoreDsl.rampUsersPerSec;
import static io.gatling.javaapi.http.HttpDsl.header;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

import com.github.javafaker.Faker;

import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.OpenInjectionStep.RampRate.RampRateOpenInjectionStep;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpDsl;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public class UserCreationSimulation extends Simulation {

    private static final HttpProtocolBuilder HTTP_PROTOCOL_BUILDER = setupProtocolForSimulation();

    private static final Iterator<Map<String, Object>> FEEDER = setupTestFeeder();

    private static final ScenarioBuilder POST_REQUEST_SCENARIO = buildPostRequestScenario();

    public UserCreationSimulation() {

        setUp(POST_REQUEST_SCENARIO.injectOpen(postEndpointInjectionModel())
                .protocols(HTTP_PROTOCOL_BUILDER))
                .assertions(
                        global().responseTime().max().lte(10000),
                        global().successfulRequests().percent().gt(90d)
                );
    }

    private RampRateOpenInjectionStep postEndpointInjectionModel() {
        int totalUserCount = 200;
        double userRampUpPerInterval = 50;
        double rampUpIntervalSeconds = 10;

        int totalRampUptimeSeconds = 30;
        int steadyStateDurationSeconds = 60;
        return rampUsersPerSec(userRampUpPerInterval / (rampUpIntervalSeconds / 60)).to(totalUserCount)
                .during(Duration.ofSeconds(totalRampUptimeSeconds + steadyStateDurationSeconds));
    }

    private static HttpProtocolBuilder setupProtocolForSimulation() {
        return HttpDsl.http.baseUrl("http://localhost:8080")
                .acceptHeader("application/json")
                .maxConnectionsPerHost(10)
                .userAgentHeader("Performance Testing");
    }

    private static Iterator<Map<String, Object>> setupTestFeeder() {
        Faker faker = new Faker();
        Iterator<Map<String, Object>> iterator;
        iterator = Stream.generate(() -> {
                    Map<String, Object> stringObjectMap = new HashMap<>();
                    stringObjectMap.put("login", faker.name().username());
                    stringObjectMap.put("password", faker.name().fullName());
                    stringObjectMap.put("name", faker.name().firstName());
                    stringObjectMap.put("surname", faker.name().lastName());
                    stringObjectMap.put("birthDate", LocalDate.of(2000, 12, 12));
                    stringObjectMap.put("documentNumber", faker.idNumber().ssnValid());
                    return stringObjectMap;
                })
                .iterator();
        return iterator;
    }

    private static ScenarioBuilder buildPostRequestScenario() {
        return CoreDsl.scenario("Load Test Registering User")
                .feed(FEEDER)
                .exec(http("register-user-request").post("/user/register")
                        .header("Content-Type", "application/json")
                        .body(StringBody("{ \"login\": \"${login}\",  \"password\": \"${password}\",\"name\": \"${name}\",\"surname\": \"${surname}\",\"birthDate\": \"${birthDate}\",\"documentNumber\": \"${documentNumber}\"}"))
                        .check(status().is(200)));
    }
}
