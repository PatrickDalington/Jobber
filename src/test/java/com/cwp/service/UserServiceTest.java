package com.cwp.service;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.http.ResponseEntity;

import com.cwp.dto.UserRequestDTO;
import com.cwp.dto.UserResponseDTO;
import com.cwp.model.User;
import com.cwp.repository.UserRepository;
import com.cwp.services.UserService;

public class UserServiceTest {

    @Test
    void registerUser_successful() {
        // Fake UserRepository
        class FakeUserRepository implements UserRepository {
            Map<String, User> db = new HashMap<>();

            @Override
            public User findByEmail(String email) {
                return db.get(email);
            }

            @Override
            public User save(User user) {
                user.setId(1L);
                db.put(user.getEmail(), user);
                return user;
            }

			@Override
			public void flush() {
				// TODO Auto-generated method stub

			}

			@Override
			public <S extends User> S saveAndFlush(S entity) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void deleteAllInBatch(Iterable<User> entities) {
				// TODO Auto-generated method stub

			}

			@Override
			public void deleteAllByIdInBatch(Iterable<Long> ids) {
				// TODO Auto-generated method stub

			}

			@Override
			public void deleteAllInBatch() {
				// TODO Auto-generated method stub

			}

			@Override
			public User getOne(Long id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public User getById(Long id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public User getReferenceById(Long id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends User> List<S> findAll(Example<S> example) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends User> List<S> saveAll(Iterable<S> entities) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<User> findAll() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<User> findAllById(Iterable<Long> ids) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Optional<User> findById(Long id) {
				// TODO Auto-generated method stub
				return Optional.empty();
			}

			@Override
			public boolean existsById(Long id) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public long count() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void deleteById(Long id) {
				// TODO Auto-generated method stub

			}

			@Override
			public void delete(User entity) {
				// TODO Auto-generated method stub

			}

			@Override
			public void deleteAllById(Iterable<? extends Long> ids) {
				// TODO Auto-generated method stub

			}

			@Override
			public void deleteAll(Iterable<? extends User> entities) {
				// TODO Auto-generated method stub

			}

			@Override
			public void deleteAll() {
				// TODO Auto-generated method stub

			}

			@Override
			public List<User> findAll(Sort sort) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Page<User> findAll(Pageable pageable) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends User> Optional<S> findOne(Example<S> example) {
				// TODO Auto-generated method stub
				return Optional.empty();
			}

			@Override
			public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends User> long count(Example<S> example) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public <S extends User> boolean exists(Example<S> example) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public <S extends User, R> R findBy(Example<S> example,
					Function<FetchableFluentQuery<S>, R> queryFunction) {
				// TODO Auto-generated method stub
				return null;
			}

            // Stub other unused methods if necessary (or leave blank)
        }

        UserRepository simulatedRepo = new FakeUserRepository();

        UserService userService = new UserService();
        userService.userRepository = simulatedRepo;

        UserRequestDTO dto = new UserRequestDTO();
        dto.setName("Olumba");
        dto.setEmail("olumba@example.com");
        dto.setPassword("123456");
        dto.setRole("EMPLOYER");

        ResponseEntity<UserResponseDTO> response = userService.registerUser(dto);

        assertEquals("Olumba", response.getBody().getName());
        assertEquals("EMPLOYER", response.getBody().getRole());
    }
}