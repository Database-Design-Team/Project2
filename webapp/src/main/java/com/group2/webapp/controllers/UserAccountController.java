package com.group2.webapp.controllers;

import com.group2.dao.UserAccountDao;
import com.group2.model.UserAccount;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

import javax.validation.constraints.NotNull;

@Controller
public class UserAccountController {

    private final UserAccountDao dao = new UserAccountDao();

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
    public ResponseEntity<Boolean> login(@NotNull @RequestBody UserAccount uc) {
        UserAccount uc2 = null;
        try {
            uc2 = dao.getUserAccountByLoginName(uc.getUsername());
        } catch (SQLException e) {
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
        }
        if (uc.equals(uc2)) {
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
        }

    }
    
    @GetMapping("/user-account-info")
    @ResponseBody
    public ResponseEntity<String> info(@RequestParam("username") String username) throws SQLException {
        UserAccount uc = dao.getUserAccountByLoginName(username);
        JSONObject json = new JSONObject();
        json.put("username", uc.getUsername());
        json.put("email", uc.getEmail());
        json.put("student_id", uc.getStudentID());
        json.put("password", uc.getPassword());
        json.put("date_joined", uc.getDateJoined());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<String>(json.toString(), headers, HttpStatus.OK);

    }
    
    @PutMapping("/update-password")
    public ResponseEntity<Boolean> changePassword(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            dao.updatePassword(username, password);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (SQLException e) {
            e.getMessage();
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }
    }
}
