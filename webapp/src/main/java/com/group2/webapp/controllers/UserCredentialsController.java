package com.group2.webapp.controllers;

import com.group2.dao.UserCredentialsDao;
import com.group2.model.UserCredentials;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
public class UserCredentialsController {

    private UserCredentialsDao dao = new UserCredentialsDao();

    public UserCredentialsController() throws SQLException {}


    @PostMapping("/user-credentials")
    @ResponseBody
    public boolean register(@RequestBody UserCredentials uc)  {
        try {
            dao.addUserCredentials(uc);
            return true;
        } catch(SQLException e) {
            e.getMessage();
            return false;
        }
    }

    @GetMapping("/user-credentials")
    @ResponseBody
    public ResponseEntity<Boolean> login(@RequestBody UserCredentials uc) {
        UserCredentials uc2 = null;
        try {
            uc2 = dao.getUserCredentialsByLoginName(uc.getLogin_name());
        } catch(SQLException e) {
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
        }
        if(uc.equals(uc2)) {
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
        }


    }
}
