package com.alejua;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.lettuce.core.api.StatefulRedisConnection;

@Singleton
public class SaludoService {

	private final Logger logger = LoggerFactory.getLogger(SaludoService.class);
	
	@Inject 
	StatefulRedisConnection<String, String> redisConnection;

	@Inject
	UserRepository userRepository;
	
	public String getSaludo(String name) {
		logger.info("SaludoService::getSaludo");
		if (name == null) name = "";
		return "Hola " + name.toUpperCase();
	}
	
	public String getObjetos() throws InterruptedException, ExecutionException {
		return redisConnection.async().get("objetos").get();
	}
	
	public CompletableFuture<? extends Iterable<User>> getUsers() {
		logger.info("SaludoService::getUsers");
		return userRepository.findAll();
	}

}
