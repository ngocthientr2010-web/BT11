package entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "fullname", length = 50)
    private String fullname;

    @Column(name = "phone")
    private Integer phone;

    @Column(name = "passwd", length = 32, nullable = false)
    private String passwd;

    @Column(name = "signup_date")
    private LocalDateTime signupDate;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rating> ratings;

    public Users() {}

    public Users(String email, String fullname, Integer phone, String passwd) {
        this.email = email;
        this.fullname = fullname;
        this.phone = phone;
        this.passwd = passwd;
    }

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public Integer getPhone() { return phone; }
    public void setPhone(Integer phone) { this.phone = phone; }
    public String getPasswd() { return passwd; }
    public void setPasswd(String passwd) { this.passwd = passwd; }
    public LocalDateTime getSignupDate() { return signupDate; }
    public void setSignupDate(LocalDateTime signupDate) { this.signupDate = signupDate; }
    public LocalDateTime getLastLogin() { return lastLogin; }
    public void setLastLogin(LocalDateTime lastLogin) { this.lastLogin = lastLogin; }
    public Boolean getIsAdmin() { return isAdmin; }
    public void setIsAdmin(Boolean isAdmin) { this.isAdmin = isAdmin; }
}