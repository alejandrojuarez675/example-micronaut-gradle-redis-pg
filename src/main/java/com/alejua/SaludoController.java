package com.alejua;

import java.util.Iterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import io.lettuce.core.RedisFuture;
import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.context.annotation.Parameter;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/example")
public class SaludoController {

	private final Logger logger = LoggerFactory.getLogger(SaludoController.class);

	@Inject
	SaludoService saludoService;
	
	@Get("/saludo")
	public SaludoDTO getSaludo() {
		logger.info("GET /saludo");
		return saludoService.getSaludo(null);
	}

	@Get("/saludo/{name}")
	public SaludoDTO getCustomSaludo(@Parameter String name) {
		logger.info("GET /saludo/{name}");
		return saludoService.getSaludo(name);
	}

	@Get("/objetos")
	public RedisFuture<String> getObjetos() {
		logger.info("GET /objetos");
		return saludoService.getObjetos();
	}
	
	@Get("/users")
	public CompletableFuture<Iterator<UserDTO>> getUsers() throws ExecutionException, InterruptedException {
		logger.info("GET /users");
		return saludoService.getUsers();
	}

	@Get("/users-rx")
	public Flowable<UserDTO> getUsersRx() {
		logger.info("GET /users-rx");
		return saludoService.getUsersRx();
	}
}
