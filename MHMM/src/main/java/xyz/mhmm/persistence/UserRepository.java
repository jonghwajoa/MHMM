package xyz.mhmm.persistence;

import org.springframework.data.repository.CrudRepository;

import xyz.mhmm.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
