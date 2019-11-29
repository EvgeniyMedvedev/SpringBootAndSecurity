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

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private UserService repository;

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    @PostMapping(value = "/edit")
    public ResponseEntity<User> edit(@RequestBody User user) {
        repository.add(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/users")
    public List<User> getAll() {
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
        return repository.getAll();
    }

    @GetMapping(path = "/{id}")
    public User getUser(@PathVariable int id) {
        return repository.getById(id);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        repository.add(user);
        logger.info(user.toString() + " - Created");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/delete")
    public void delete(@PathVariable("id") int id) {
        repository.delete(id);
        logger.info("№ " + id + " - удален");
    }

    @PostMapping(value = "/{id}/edit")
    public ResponseEntity<User> edit(@PathVariable("id") int id, @ModelAttribute("user") User user) {
        logger.info(user.toString() + "\n");
        repository.add(user);
        logger.info(user.toString() + " - Edited");

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
