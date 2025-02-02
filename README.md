## Playing Around with Prometheus and Grafana with Spring-Boot metrics

---

### Running Application

In the app directory create  ```.env``` file using 
```cp .env.example .env```, fill env variables and execute the docker command.

```
docker-compose up --build
```
Following applications will be running : 

* spring-application on ```localhost:8077```

* Prometheus on ```localhost:9090```

* Grafana on ```localhost:3000```

To add prometheus data source on Grafana dashboard use the 
end point ```http://prometheus:9090```

