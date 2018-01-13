package ua.epam.spring.hometask.domain;

import ua.epam.spring.hometask.util.annotation.IsEmail;
import ua.epam.spring.hometask.util.enums.UserRole;

import java.time.LocalDate;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Yuriy_Tkach
 */
public class User extends DomainObject {

    private static final String ANONYMOUS = "anonymous";

    public static final User anonymousUser;
    static {
        anonymousUser = new User();
        anonymousUser.setFirstName(ANONYMOUS);
        anonymousUser.setLastName(ANONYMOUS);
        anonymousUser.setEmail("fake@email.com");
        anonymousUser.setUserRoles(Set.of(UserRole.CUSTOMER));
    }

    private String firstName;

    private String lastName;

    @IsEmail
    private String email;

    private String password;

    private Set<UserRole> userRoles;

    private LocalDate birthday;

    private NavigableSet<Ticket> tickets = new TreeSet<>();

    public boolean isAnonymous(){
        return anonymousUser.equals(this);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(@IsEmail String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public NavigableSet<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(NavigableSet<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket){
        tickets.add(ticket);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public void addUserRole(UserRole role){
        this.userRoles.add(role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        return true;
    }

}
