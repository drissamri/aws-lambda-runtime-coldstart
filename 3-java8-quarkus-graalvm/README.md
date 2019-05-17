Build
-----
`mvn clean package -DskipTests=true -Dnative=true -Dnative-image.docker-build=true`

Quarkus Java:
-------
Local invoke: `sls invoke local -f hello --path event.json`  
Deploy: `sls deploy --type java`  
Invoke: `sls invoke -f hello -l --path event.json`  

Quarkus Native:
------

Invoke local: `sls invoke local -f hello --path event.json --type native`  
Deploy: `sls deploy --type native`  
Invoke AWS Lambda: `sls invoke -f hello -l --path event.json`  
Invoke AWS API Gateway: 
```
curl -X POST \
    https://api.drissamri.com/quarkus-native/product \
    -H 'Content-Type: application/json' \
    -d '{ "name": "iPhone" }'
```


Issues:
---
No DynamoDB support yet: https://github.com/quarkusio/quarkus/pull/2193