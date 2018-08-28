package Login;

public class Admin {

    private int adminid;
    private String FirstName;
    private String LastName;
    private String address;
    private String email;
    private int phoneNo;
    private String password;

    public Admin() {
    }

    public Admin(String FirstName, String LastName, String address, String email) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.address = address;
        this.email = email;
    }

    public Admin(int adminid, String firstName, String lastName, String address, String email, int phoneNo, String password) {
        this.adminid = adminid;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.address = address;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
    }

    public int getadminid() {
        return adminid;
    }

    public int getphoneNo() {
        return phoneNo;
    }

    public void adminid(int admin) {
        this.adminid = adminid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
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

    public void setphoneno(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String email() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
