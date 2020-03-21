package com.geografie.ora_de_geografie.ui.controller;

import com.geografie.ora_de_geografie.service.EmailSenderService;
import com.geografie.ora_de_geografie.service.UserService;
import com.geografie.ora_de_geografie.shared.dto.UserDto;
import com.geografie.ora_de_geografie.ui.model.request.UserRegisterRequestModel;
import com.geografie.ora_de_geografie.ui.model.response.UserRest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    EmailSenderService emailSenderService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserRest createUser(@RequestBody UserRegisterRequestModel userRegisterRequestModel) throws Exception {
        ModelMapper modelMapper = new ModelMapper();

        UserDto userDto = modelMapper.map(userRegisterRequestModel, UserDto.class);

        UserDto createdUser = userService.createUser(userDto);
        UserRest userRest = modelMapper.map(createdUser, UserRest.class);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userDto.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("andonealexandru19@gmail.com");
        mailMessage.setText("To confirm your account please clik here: " +
                "https://itec2019rockthecode.herokuapp.com/users/confirm-account?token=" + createdUser.getEmailConfirmationToken());

        emailSenderService.sendEmail(mailMessage);

        return userRest;
    }

    @GetMapping(path = "/confirm-account")
    public String confirmUserAccount(@RequestParam("token") String confirmationToken) {

        UserDto userDto = userService.getUserByEmailConfirmatonToken(confirmationToken);

        if (userDto != null) {
            UserDto userDto1 = userService.getUser(userDto.getEmail());
            userDto1.setEnabled(true);
            //TODO : simplify to only one userDto

            userService.updateUser(userDto1);

            return "Enabled account";
        }

        return "Error";
    }

}
