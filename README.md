# rso-beers
Microservice for beers

```bash
docker run -d --name pg-beers -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=beers -p 5432:5432 postgres:10.5
```