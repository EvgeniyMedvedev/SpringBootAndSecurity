package boot.controller;

import boot.model.Role;
import boot.model.User;
import boot.service.UserService;
import boot.transfer.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public List<UserDTO> getAll() {
        List<UserDTO> all = new ArrayList<>();
        for (User user:repository.getAll()){
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
    public UserDTO getUser(@PathVariable int id){
        User byId = repository.getById(id);
        UserDTO userDTO = new UserDTO(
                byId.getId(),
                byId.getLogin(),
                byId.getPassword(),
                byId.getName(),
                byId.getRoles().iterator().next().getAuthority());
        return userDTO;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        repository.add(user);
        logger.debug(user.toString() + "\nCreated");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @PostMapping(value="/{id}/delete")
//    public ResponseEntity<User> delete(@PathVariable("id") int id) {
//        repository.delete(id);
//
//        return ResponseEntity.ok().build();
//    }
}
