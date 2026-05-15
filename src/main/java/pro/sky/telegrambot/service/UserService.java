package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.primary.Users;
import pro.sky.telegrambot.repository.primary.UserRepository;

import java.util.List;

@Service
public class UserService {

   private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users findById(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public List<Users> findAll() {
        return userRepository.findAll();
    }

    public Users create(Users user) {
        return userRepository.save(user);
    }

    public Users update(long id, Users user) {
        Users edited = userRepository.findById(id).orElseThrow();
        edited.setName(user.getName());
        edited.setEmail(user.getEmail());
        edited.setPhone(user.getPhone());
        return userRepository.save(edited);
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

}
