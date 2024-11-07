package narihy.mg.base.api.controller;

import narihy.mg.base.api.commons.controller.BaseController;
import narihy.mg.base.api.persistences.models.UserEntity;
import narihy.mg.base.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController<UserEntity, Long> {

    private final UserService userService;

    // Constructeur qui injecte UserService et passe au constructeur parent
    @Autowired
    public UserController(UserService userService) {
        super(userService);  // Passer userService au constructeur de BaseController
        this.userService = userService; // L'assignation de userService se fait ici
    }

}
