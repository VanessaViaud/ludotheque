package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CopyRepository extends JpaRepository<Copy, Integer> {


 }
