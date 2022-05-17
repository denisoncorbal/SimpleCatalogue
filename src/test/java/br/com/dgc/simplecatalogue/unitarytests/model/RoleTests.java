package br.com.dgc.simplecatalogue.unitarytests.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.dgc.simplecatalogue.unitarytests.model.entity.Role;
import org.junit.jupiter.api.Test;

public class RoleTests {

  @Test
  public void emptyRole(){
    Role role = new Role();

    assertNotNull(role.getId());
    assertNotNull(role.getRolename());
    assertNotNull(role.getDescription());
  }

  @Test
  public void equalsRoles(){
    Role role1 = new Role(0L, "rolename", "description");
    Role role2 = new Role(0L, "rolename", "description");

    assertEquals(role1.getId(), role2.getId());
    assertEquals(role1.getRolename(), role2.getRolename());
    assertEquals(role1.getDescription(), role2.getDescription());
  }

}
