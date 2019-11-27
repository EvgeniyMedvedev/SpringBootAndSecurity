package boot.service;

import boot.repository.RoleRepository;
import boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import boot.model.Role;
import boot.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository dao;

    private final RoleRepository rDao;

    PasswordEncoder encoder = new BCryptPasswordEncoder(11);

    @Autowired
    public UserServiceImpl(UserRepository dao, RoleRepository rDao) {
        this.dao = dao;
        this.rDao = rDao;
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return dao.findAll();
    }

    @Override
    @Transactional
    public void add(User t) {
        t.setPassword(encoder.encode(t.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(rDao.findById(1L).get());
        t.setRoles(roles);
        dao.save(t);
    }

    @Override
    @Transactional
    public User getById(int id) {
        return dao.findById(id).get();
    }

    @Override
    @Transactional
    public void delete(int id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        dao.save(user);
    }

    @Override
    public User getByLogin(String login) {
        return dao.findByLogin(login);
    }
}
