package com.hacom.order.infrastructure.config;

import com.mongodb.ConnectionString;
//import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;

@Configuration
public class ConfigurationDataBase extends AbstractReactiveMongoConfiguration {
	
	//@Value("${mongodb.uri}")
	@Value("${spring.data.database.uri}")
    private String mongoUri;
	
	@Value("${app.database.host}")
    private String server;
	
	@Value("${app.database.port}")
    private String portServer;
	
	@Value("${app.database.username}")
    private String username;

    @Value("${app.database.password}")
    private String password;

    @Value("${app.database.database}")
    private String database;
	 
    @Override
    protected String getDatabaseName() {
        return new ConnectionString(mongoUri).getDatabase(); // Extrae el nombre desde la URI
    }
    
    /*@Override
    public MongoClient reactiveMongoClient() {
        ConnectionString connectString = new ConnectionString(mongoUri);
        MongoClientSettings settings = MongoClientSettings.builder()
												            .applyConnectionString(connectString)
												            .build();
        return MongoClients.create(settings);
    }*/
    
    @Override
    public MongoClient reactiveMongoClient() {
        String connectString = String.format("mongodb://%s:%s@%s:%s/%s", username, password, server, portServer, database);
        return MongoClients.create(connectString);
    }

}
