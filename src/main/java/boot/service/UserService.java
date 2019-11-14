package boot.service;

import boot.model.User;

public interface UserService {
    Iterable<User> getAll() ;

    void add(User t);

    User getById(int id) ;

    void delete(int id);

    void updateUser(User user);

    User getByLogin(String login);
}
