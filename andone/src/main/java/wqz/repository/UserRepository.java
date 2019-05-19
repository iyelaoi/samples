package wqz.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import wqz.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    @Transactional
	User findByUsername(String username);
}
