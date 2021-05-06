package boot.controller;

import boot.model.User;
import boot.service.UserService;
import boot.transfer.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestUserController {

    private final UserService repository;

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    public RestUserController(UserService repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/edit")
    public ResponseEntity<?> edit(@RequestBody User user) {
        repository.add(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/users")
    public List<UserDTO> getAll() {
        List<UserDTO> all = new ArrayList<>();
        for (User user : repository.getAll()) {
            UserDTO userDTO = new UserDTO(
                    user.getId(),
                    user.getLogin(),
                    user.getPassword(),
                    user.getName(),
                    user.getRoles().iterator().next().getAuthority());
            all.add(userDTO);
        }
        return all;
    }

    @GetMapping(path = "/{id}")
    public User getUser(@PathVariable int id) {
        return repository.getById(id);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        repository.add(user);
        logger.info(user.toString() + " - was created");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/delete")
    public void delete(@PathVariable("id") int id) {
        repository.delete(id);
        logger.info("№ " + id + " - удален");
    }

    @PostMapping(value = "/{id}/edit")
    public ResponseEntity<?> edit(@PathVariable("id") int id, @ModelAttribute("user") User user) {
        repository.add(user);
        logger.info(user + " - was edited");

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
