package com.zyb.warframe.dao;

import com.zyb.warframe.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Integer> {
    User findByUserName(String userName);

    User getByUserNameAndPassWord(String userName,String passWord);
}
