package com.dmcorrales.api.modules.user.controllers;

import com.dmcorrales.api.commons.api.controller.GenericController;
import com.dmcorrales.api.commons.api.service.GenericService;
import com.dmcorrales.api.modules.user.entities.User;
import com.dmcorrales.api.modules.user.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends GenericController<String, User> {

    @Autowired
    UserServiceImpl service;

    @Override
    protected GenericService<String, User> getService() {
        return service;
    }
}
