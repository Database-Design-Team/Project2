package com.group2.webapp.controllers;

import com.group2.dao.UserAccountDao;
import com.group2.model.UserAccount;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
public class UserAccountController {

    private UserAccountDao dao = new UserAccountDao();

    public UserAccountController() throws SQLException {}


    @PostMapping("/user-account-register")
    @ResponseBody
    public ResponseEntity<Boolean> register(@RequestBody UserAccount uc) {
        try {
            dao.addUserAccount(uc);
            // return true;
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (SQLException e) {
            e.getMessage();
            return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
        }
    }
    
    @PostMapping("/user-account-login")
    @ResponseBody
    public ResponseEntity<Boolean> login(@RequestBody UserAccount uc) {
        UserAccount uc2 = null;
        try {
            uc2 = dao.getUserAccountByLoginName(uc.getUsername());
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
