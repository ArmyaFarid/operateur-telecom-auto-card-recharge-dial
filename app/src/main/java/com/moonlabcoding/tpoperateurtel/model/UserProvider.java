package com.moonlabcoding.tpoperateurtel.model;

import java.util.List;

public class UserProvider {
    private List<User> mUsersList;

    public UserProvider(List<User> usersList) {
        mUsersList = usersList;
    }

    public User logUser(String username, String password){
        for(User user : mUsersList){
            if (user.getUserName().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
}
