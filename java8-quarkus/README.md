 WORK IN PROGRESS - NOT DEPLOYABLE TO AWS YET
 
 #### Development 
 ```
 mvn compile quarkus:dev
```

 #### Run native image
 Make sure GRAALVM_HOME environment variable is set:
 ```
 export GRAALVM_HOME=/Users/drissamri/tools/graalvm-ce-1.0.0-rc14/Contents/Home
 ```
 Create native image 
 ```
 ./mvnw package -Pnative
 ```
Run native image
```
 ./target/lambda-quarkus-1.0-SNAPSHOT-runner
```

```
2019-04-03 15:18:02,586 INFO  [io.quarkus] (main) Quarkus 0.12.0 started in 0.008s. Listening on: http://[::]:8080
2019-04-03 15:18:02,586 INFO  [io.quarkus] (main) Installed features: [cdi, resteasy]
```

