package com.alejua;

import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.StreamSupport;

@Singleton
public class SaludoService {

	private final Logger logger = LoggerFactory.getLogger(SaludoService.class);
	
	@Inject 
	StatefulRedisConnection<String, String> redisConnection;

	@Inject
	UserRepository userRepository;

	@Inject
	UserRepositoryRx userRepositoryRx;

	public SaludoDTO getSaludo(String name) {
		logger.info("SaludoService::getSaludo");
		if (name == null) name = "";
		return new SaludoDTO("Hola " + name.toUpperCase());
	}
	
	public RedisFuture<String> getObjetos() {
		logger.info("SaludoService::getObjetos");
		return redisConnection.async().get("objetos");
	}
	
	public CompletableFuture<Iterator<UserDTO>> getUsers() throws ExecutionException, InterruptedException {
		logger.info("SaludoService::getUsers");
		return CompletableFuture.completedFuture(
				StreamSupport.stream(userRepository.findAll().get().spliterator(), false)
						.map(user -> new UserDTO(user.getNombre()))
						.iterator());
	}

	public Flowable<UserDTO> getUsersRx() {
		logger.info("SaludoService::getUsersRx");
		return userRepositoryRx.findAll()
				.map(user -> new UserDTO(user.getNombre()));
	}

}
