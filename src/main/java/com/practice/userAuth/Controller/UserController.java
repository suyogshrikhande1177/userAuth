package com.practice.userAuth.Controller;

import ch.qos.logback.core.util.StringUtil;
import com.practice.userAuth.Model.ERole;
import com.practice.userAuth.Model.Role;
import com.practice.userAuth.Model.User;
import com.practice.userAuth.Payload.Request.SignUpRequest;
import com.practice.userAuth.Payload.Response.MessageResponse;
import com.practice.userAuth.Service.RoleService;
import com.practice.userAuth.Service.UserService;
import com.practice.userAuth.Utils.ErrorConstrants;
import com.practice.userAuth.Utils.InitialError;
import com.practice.userAuth.Utils.SuccessMsg;
import com.practice.userAuth.exceptions.RecordNotFoundException;
import com.practice.userAuth.mappers.UserMapper;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserController {
//    @Autowired
//    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @PostMapping("/signup")
    @Transactional
    @CrossOrigin
    public ResponseEntity<?> resistorUser(@Validated @RequestBody SignUpRequest signUpRequest){
        if(this.userService.findUserName(signUpRequest.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse(InitialError.USERNAME_FOUND));
        }
        if (StringUtil.notNullNorEmpty(signUpRequest.getEmail()) &&this.userService.findEmailId(signUpRequest.getEmail())) {

            return ResponseEntity.badRequest().body(new MessageResponse(InitialError.EMAIL_FOUND));
        }
        User user = UserMapper.INSTANCE.toDto(signUpRequest);
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));

        Set<String> strRole = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if(strRole == null){
        Role userRole = this.roleService.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RecordNotFoundException(ErrorConstrants.RECORD_NOT_FOUND));
        roles.add(userRole);
        }else {
            strRole.forEach(role ->{
                    switch (role){
                        case "admin":
                            Role adminRole = this.roleService.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RecordNotFoundException(ErrorConstrants.RollNotFount));
                            roles.add(adminRole);

                        case "accountant":
                            Role accountantRole = this.roleService.findByName(ERole.ROLE_ACCOUNTANT)
                                    .orElseThrow(() -> new RecordNotFoundException(ErrorConstrants.RollNotFount));
                            roles.add(accountantRole);

                        case "staf":
                            Role stafRole = this.roleService.findByName(ERole.ROLE_STAFF)
                                    .orElseThrow(() -> new RecordNotFoundException(ErrorConstrants.RollNotFount));
                            roles.add(stafRole);

                        case  "customer":
                            Role customerRole = this.roleService.findByName(ERole.ROLE_CUSTOMER)
                                    .orElseThrow(() -> new RecordNotFoundException(ErrorConstrants.RollNotFount));
                            roles.add(customerRole);

                        default:
                            Role defaultRole = this.roleService.findByName(ERole.ROLE_USER)
                                    .orElseThrow(() -> new RecordNotFoundException(ErrorConstrants.RollNotFount));
                            roles.add(defaultRole);
                    }
                    });

        }
         user.setRoles(roles);
        this.userService.resitorUser(user);
        return ResponseEntity.ok(new MessageResponse(SuccessMsg.USER_SCC));
    }

}
