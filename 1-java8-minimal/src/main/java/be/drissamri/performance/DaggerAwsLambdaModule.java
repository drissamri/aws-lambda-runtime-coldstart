package be.drissamri.performance;

import be.drissamri.performance.services.PingService;
import be.drissamri.performance.services.PingServiceImpl;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DaggerAwsLambdaModule
{
    @Provides
    @Singleton
    public PingService pingService(){
        return new PingServiceImpl();
    }
}
