package service;

import dao.UserDao;
import entity.Users;

import java.time.LocalDateTime;

public class UserService {
    private final UserDao userDao = new UserDao();

    public Users login(String email, String password) {
        Users user = userDao.findByEmail(email);

        if (user == null) {
            return null;
        }

        if (!checkPassword(password, user.getPasswd())) {
            return null;
        }

        userDao.updateLastLogin(user);
        return user;
    }

    private boolean checkPassword(String rawPassword, String storedPassword) {
        return rawPassword.equals(storedPassword);
    }

    public boolean emailExists(String email) {
        return userDao.findByEmail(email) != null;
    }

    public String register(String email, String fullname, Integer phone, String password) {
        if (userDao.findByEmail(email) != null) {
            return "Email này đã được đăng ký!";
        }

        Users user = new Users();
        user.setEmail(email);
        user.setFullname(fullname);
        user.setPhone(phone);
        user.setPasswd(password);
        user.setSignupDate(LocalDateTime.now());
        user.setIsAdmin(false);

        userDao.save(user);
        return null;
    }
}
