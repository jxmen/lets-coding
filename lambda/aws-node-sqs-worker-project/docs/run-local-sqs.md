## local에 sqs 실행 with docker

```bash
pip3 install awscli-local aws-sam-cli
```

```bash
aws configure set default.sqs.endpoint http://localhost:9324
docker run -d -p 9324:9324 softwaremill/elasticmq
```

```bash
aws --endpoint-url http://localhost:9324 sqs create-queue --queue-name MyLocalQueue
```
