package br.com.dgc.simplecatalogue.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Define a role used for authorization purpose on API.
 *
 * @since 1.0
 */
@Table(name = "tb_roles")
@Entity
public class Role implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String rolename;

  @Column private String description;

  /**
   * No args constructor for Role.
   *
   * @since 1.0
   */
  public Role() {
    this.id = 0L;
    this.rolename = "";
    this.description = "";
  }

  /**
   * All args constructor for Role.
   *
   * @param id Unique identifier of the Role.
   * @param rolename Name of the Role.
   * @param description Description of the Role.
   * @since 1.0
   */
  public Role(Long id, String rolename, String description) {
    this.id = id;
    this.rolename = rolename;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRolename() {
    return rolename;
  }

  public void setRolename(String rolename) {
    this.rolename = rolename;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
