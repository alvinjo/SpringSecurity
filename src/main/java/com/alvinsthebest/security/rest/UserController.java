package com.alvinsthebest.security.rest;

import com.alvinsthebest.security.repo.model.User;
import com.alvinsthebest.security.service.UserService;
import com.alvinsthebest.security.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(Constants.USER_INFO)
    public ResponseEntity<?> getInfo(@RequestParam Long id){
        return ResponseEntity.ok(userService.getInfo(id));
    }

    @GetMapping(Constants.USER_INFO_FROM_TOKEN)
    public ResponseEntity<?> getInfoUsingJustToken(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(user.getInfo());
    }

    @PostMapping(Constants.USER_SAVE)
    public ResponseEntity<?> save(@RequestBody User user){
            userService.save(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping(Constants.ADMIN_SECRET)
    public ResponseEntity<?> secretStuff(){
        return ResponseEntity.ok(Constants.JUST_THE_FACTS);
    }

    @GetMapping(Constants.ADMIN_ALL)
    public List<User> all() {
        return userService.all();
    }

}
