package xyz.mhmm.persistence;

import org.springframework.data.repository.CrudRepository;

import xyz.mhmm.domain.Login;

public interface LoginRepository extends CrudRepository<Login, Long> {

}
