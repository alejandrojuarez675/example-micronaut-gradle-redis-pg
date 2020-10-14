package com.alejua;

import java.util.Iterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.StreamSupport;

import javax.inject.Inject;

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
	public String getObjetos() {
		logger.info("GET /objetos");
		try {
			return saludoService.getObjetos();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return "Todo mal con redis";
		}
	}
	
	@Get("/users")
	public CompletableFuture<Iterator<UserDTO>> getUsers() throws ExecutionException, InterruptedException {
		logger.info("GET /users");
		return CompletableFuture.completedFuture(
				StreamSupport.stream(saludoService.getUsers().get().spliterator(), false)
						.map(user -> new UserDTO(user.getNombre()))
						.iterator());
	}
}
