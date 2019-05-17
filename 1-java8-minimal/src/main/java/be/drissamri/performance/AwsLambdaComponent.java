package be.drissamri.performance;

import be.drissamri.performance.services.PingService;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaggerAwsLambdaModule.class})
public interface AwsLambdaComponent {

    PingService pingService();
}
