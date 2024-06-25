package api.dao.model;

import java.util.Objects;

public class User extends AbstractModel {
    private String email;
    private String password;

    public User(){

    };

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(long id, String email, String password) {
        super.setId(id);
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return super.getId();
    }

    public void setId(long id) {
        super.setId( id);
    }
    public void setId(int id) {
        super.setId( id);
    }public void setId(Integer id) {
        super.setId( id);
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + super.getId() +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
