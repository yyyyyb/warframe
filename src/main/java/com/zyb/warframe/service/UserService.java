package com.zyb.warframe.service;

import com.zyb.warframe.dao.UserDAO;
import com.zyb.warframe.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDAO userDao;

    public boolean isExist(String username) {
        User user = getByName(username);
        return null!=user;
    }

    public User getByName(String userName) {
        return userDao.findByUserName(userName);
    }

    public User get(String userName, String passWord){
        return userDao.getByUserNameAndPassWord(userName, passWord);
    }

    public void add(User user) {
        userDao.save(user);
    }

}
