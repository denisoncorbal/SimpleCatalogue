package br.com.dgc.simplecatalogue;

import br.com.dgc.simplecatalogue.model.entity.Role;
import br.com.dgc.simplecatalogue.model.entity.User;
import br.com.dgc.simplecatalogue.service.RoleService;
import br.com.dgc.simplecatalogue.service.UserService;
import java.util.Set;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class SimpleCatalogueRunner implements ApplicationRunner {

  private final RoleService roleService;

  private final UserService userService;

  public SimpleCatalogueRunner(RoleService roleService, UserService userService){
    this.roleService = roleService;
    this.userService = userService;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Role roleAdmin = new Role(1L, "ADMIN", "Administrative role with complete access");
    Role userRole = new Role(2L, "USER", "User access for catalogue purpose");
    roleAdmin = roleService.createRole(roleAdmin);
    roleService.createRole(userRole);

    User admin = new User(1L, args.getOptionValues("username").get(0), args.getOptionValues("password").get(0), "admin", Set.of(roleAdmin));
    userService.createUser(admin);
  }
}
