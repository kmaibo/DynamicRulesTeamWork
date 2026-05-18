package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.primary.Users;
import pro.sky.telegrambot.repository.primary.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

   private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users findById(long id) {

        logger.info("findById " + id);
        logger.error("findById " + id + " not found");

        return userRepository.findById(id).orElseThrow();
    }

    public Users create(Users user) {

        logger.info("create" + user);
        logger.error("create" + user + " not found");

        return userRepository.save(user);
    }

    public Users update(long id, Users user) {

        logger.info("findById " + id);
        logger.error("findById " + id + " not found");
        Users edited = userRepository.findById(id).orElseThrow();
        edited.setName(user.getName());
        edited.setEmail(user.getEmail());
        edited.setPhone(user.getPhone());
        logger.info("edited" + edited);
        logger.error("edited" + edited + " not found");
        return userRepository.save(edited);
    }

    public void delete(long id) {
        logger.info("delete user" + id);
        logger.error("delete user" + id + " not found");

        userRepository.deleteById(id);
    }
}
