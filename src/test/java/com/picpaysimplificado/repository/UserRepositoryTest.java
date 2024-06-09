package com.picpaysimplificado.repository;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dto.UserDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.swing.text.html.parser.Entity;

import java.math.BigDecimal;
import java.util.Optional;

import static com.picpaysimplificado.domain.user.UserType.COMMON;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get User successffuly from DB")
    void findByDocumentCase1() {
        String document = "99999999901";
        UserDTO data = new UserDTO("Fernanda", "Teste", document, new BigDecimal(10), "teste@gm.com", "1234", COMMON);
        this.createUser(data);

        Optional<User> result = this.userRepository.findByDocument(document);
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should get User from DB when user not exists")
    void findByDocumentCase2() {
        String document = "99999999901";

        Optional<User> result = this.userRepository.findByDocument(document);
        assertThat(result.isEmpty()).isTrue();
    }

    private User createUser(UserDTO data){
        User newUser = new User(data);
        this.entityManager.persist(newUser);
        return newUser;
    }
}