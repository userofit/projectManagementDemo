package cafytech.projectManagementDemo.api;


import cafytech.projectManagementDemo.exception.EmailExistsException;
import cafytech.projectManagementDemo.model.User;
import cafytech.projectManagementDemo.registration.OnRegistrationCompleteEvent;
import cafytech.projectManagementDemo.registration.UserDto;
import cafytech.projectManagementDemo.service.UserService;
import cafytech.projectManagementDemo.util.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class RegistrationController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;



    //"/user/registration" -- this path return html file in response body, Content-Type:"text/html;charset=UTF-8"
    //"/register" worked -- return Content-Type:"application/json;charset=UTF-8"
    @PostMapping(path = "/register", consumes = "application/json") //w
    public GenericResponse registerUserAccount(@RequestBody UserDto accountDto, final HttpServletRequest request )throws EmailExistsException {

        LOGGER.debug("Registering user account with information: {}", accountDto);

        User registeredUser = userService.registerNewUserAccount(accountDto);

        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registeredUser, request.getLocale(), getAppUrl(request)));
        return new GenericResponse("success");
    }


    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }


}
