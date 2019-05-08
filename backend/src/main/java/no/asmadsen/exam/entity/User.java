package no.asmadsen.exam.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@NamedQueries({
        @NamedQuery(name = User.GET_BY_EMAIL, query = "select u from User u where u.email = :email"),
        @NamedQuery(name = User.GET_BY_ALIAS, query = "select u from User u where u.alias = :alias and u.alias is not null")
})
@Entity
@Table(name = "users")
public class User {
    public static final String GET_BY_EMAIL = "GET_BY_EMAIL";
    public static final String GET_BY_ALIAS = "GET_BY_ALIAS";

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    @Email
    private String email;

    @Column(name = "password")
    private String passwordHash;

    @NotNull
    private Boolean enabled;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

    @Size(max = 255)
    private String alias;

    @NotBlank
    @Size(max = 255)
    private String firstName;

    @NotBlank
    @Size(max = 255)
    private String lastName;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
