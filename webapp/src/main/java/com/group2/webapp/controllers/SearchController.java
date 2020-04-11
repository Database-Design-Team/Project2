package com.group2.webapp.controllers;

import com.group2.dao.AbstractBaseDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SearchController extends AbstractDBController {

    public SearchController() {
        super();
    }



}
