package pl.edu.agh.dsm.front.models;

/**
 * Created by Sakushu on 2014-04-24.
 */
public class UserModel {
    protected String login;
    protected String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
