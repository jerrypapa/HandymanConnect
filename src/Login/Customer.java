package Login;

public class Customer {

    private int custid;
    private String FirstName;
    private String LastName;
    private String location;
    private String email;
    private int phoneNo;
    private String password;
    private String gender;
    private String username;
    private String dateRegistered;

    public Customer() {

    }

    public Customer(int custid, String firstName, String lastName, String location, String email, int phoneNo, String password, String gender, String username, String dateRegistered) {
        this.custid = custid;
        FirstName = firstName;
        LastName = lastName;
        this.location = location;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
        this.gender = gender;
        this.username = username;
        this.dateRegistered = dateRegistered;
    }

    public int getCustid() {
        return custid;
    }

    public void setCustid(int custid) {
        this.custid = custid;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered;
    }
}

