package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserInfo, Integer> {

    UserInfo findUserByEmail(String email);
}
