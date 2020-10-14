package com.alejua;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.async.AsyncCrudRepository;

@Repository
public interface UserRepository extends AsyncCrudRepository<User, Long> {

}
