package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Copy;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CopyRepository extends MongoRepository<Copy, String> {


 }
