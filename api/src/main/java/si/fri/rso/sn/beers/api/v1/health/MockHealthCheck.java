package si.fri.rso.sn.beers.api.v1.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import si.fri.rso.sn.beers.services.configuration.AppProperties;



import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Health
@ApplicationScoped
public class MockHealthCheck implements HealthCheck {

    @Inject
    private AppProperties appProperties;

    public HealthCheckResponse call() {

        HealthCheckResponseBuilder healthCheckResponseBuilder =
                HealthCheckResponse.named(MockHealthCheck.class.getSimpleName());

        if (appProperties.isHealthy()) {
            return healthCheckResponseBuilder.up().build();
        } else {
            return healthCheckResponseBuilder.down().build();
        }
    }

}
