package com.group2.webapp.controllers;

import com.group2.dao.UserCredentialsDao;
import com.group2.model.UserCredentials;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

@Controller
public class UserCredentialsController {

    private UserCredentialsDao dao = new UserCredentialsDao();

    public UserCredentialsController() throws SQLException {}


    @PostMapping("/user-credentials-register")
    @ResponseBody
    public ResponseEntity<Boolean> register(@RequestBody UserCredentials uc) {
        try {
            dao.addUserCredentials(uc);
            // return true;
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (SQLException e) {
            e.getMessage();
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }
    }
    
    @PostMapping("/user-credentials-login")
    @ResponseBody
    public ResponseEntity<Boolean> login(@NotNull @RequestBody UserCredentials uc) {
        UserCredentials uc2;
        try {
            uc2 = dao.getUserCredentialsByLoginName(uc.getLogin_name());
        } catch(SQLException e) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        if(uc.equals(uc2)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }
    }

    // @PostMapping("/user-credentials-login")
    // @ResponseBody
    // public boolean login(@RequestBody UserCredentials uc) {
    //     UserCredentials uc2 = null;
    //     try {
    //         uc2 = dao.getUserCredentialsByLoginName(uc.getLogin_name());
    //     } catch (SQLException e) {
    //         return false;
    //     }
    //     return uc.equals(uc2);
    // }
}
