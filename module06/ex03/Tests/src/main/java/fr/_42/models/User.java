package fr._42.models;

public class User {

    private int identifier;
    private String Login;
    private String Password;
    private boolean authenticated;

    public User(int identifier, String login, String password, boolean authenticated) {
        this.identifier = identifier;
        this.Login = login;
        this.Password = password;
        this.authenticated = authenticated;
    }
    public int getIdentifier() {
        return identifier;

    }
    public String getLogin() {
        return Login;
    }
    public String getPassword() {
        return Password;
    }
    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
    public void setLogin(String login) {
        Login = login;
    }
   public  void setPassword(String password) {
        Password = password;
    }
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    @Override
    public String toString() {
        return "User with id " + identifier + " and login " + Login + " and password " + Password + " and  " + (authenticated ? "authenticated" : "not authenticated");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if (identifier != user.identifier) return false;
        if (authenticated != user.authenticated) return false;
        return Login.equals(user.Login);
    }

}
