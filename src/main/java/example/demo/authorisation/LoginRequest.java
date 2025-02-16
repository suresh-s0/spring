package example.demo.authorisation;

public class LoginRequest {
    private String Email;
    private String Password;
    public String getEmail() {
        return Email;
    }
    public String getPassword() {
        return Password;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }
    public void setPassword(String Password) {
       this.Password = Password;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}
