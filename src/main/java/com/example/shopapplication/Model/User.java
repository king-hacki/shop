package com.example.shopapplication.Model;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users",
       uniqueConstraints = {
       @UniqueConstraint(columnNames = "username"),
       @UniqueConstraint(columnNames = "password")
})
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private Byte[] image;

    @NotBlank(message = "Field 'first_name' must be filled")
    private String firstName;

    @NotBlank(message = "Field 'last_name' must be filled")
    private String lastName;

    @NotBlank(message = "Field 'username' must be filled")
    @Size(max = 20)
    private String username;

    @NotBlank(message = "Field 'email' must be filled")
    @Size(max = 40)
    @Email(message = "This is not email, please enter correct data")
    private String email;

    @NotBlank(message = "Field 'password' must be filled")
    @Size(min = 8,max = 120)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(){}

    public User(String username,String firstName, String lastName,String email,String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public String toString() {
        return "{" +
                "username" + this.getUsername() + "\n" +
                "firstName" + this.getFirstName() + "\n" +
                "lastName" + this.getLastName() + "\n" +
                "email" + this.getEmail() + "\n" +
                "ShoppingCart" + this.getShoppingCart().getMobilePhoneList() + "\n" +
                "}";
    }
}
