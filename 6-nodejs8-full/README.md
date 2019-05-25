Deploy
------
```bash
sls deploy
```
Invoke
------
Serverless Framework CLI
```bash
sls invoke -f add-product -p event.json -l
```
AWS CLI
```bash
aws lambda invoke --function-name nodejs8-full-performancesl --region eu-west-1 --payload file://event.json output.log
```

Performance
----------
Cold start: **68.87 ms**
```
REPORT Duration: 68.87 ms  Billed Duration: 100 ms  Memory Size: 1024 MB  Max Memory Used: 124 MB
```

Tweaks
----------
- Don't package AWS SDK, since it's already in the runtime
- Enable AWS SDK DynamoDB HTTP Keep-Alive (connection reuse)

