package com.example.esercizi_develhope_sito.EserciziDevelhopeSito.Repository;

import com.develhope.demo.EserciziDevelhopeSito.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
