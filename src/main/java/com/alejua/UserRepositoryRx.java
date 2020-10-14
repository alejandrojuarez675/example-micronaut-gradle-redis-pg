package com.alejua;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.RxJavaCrudRepository;

@Repository
public interface UserRepositoryRx extends RxJavaCrudRepository<User, Long> {
}
