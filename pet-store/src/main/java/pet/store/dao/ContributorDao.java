package pet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pet.store.controller.model.Contributor;

public interface ContributorDao extends JpaRepository<Contributor, Long>{

}
