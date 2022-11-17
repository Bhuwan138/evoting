/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting.dto;

/**
 *
 * @author Bhuwan Pandey
 */
public class UserDetails {
    private String ctznNo;
    private String password;
    private String userName;
    private String address;
    private String city;
    private String email;
    private String mobileNo;
    
    public UserDetails(){};

    public UserDetails(String ctznNo, String password, String userName, String address, String city, String email, String mobileNo) {
        this.ctznNo = ctznNo;
        this.password = password;
        this.userName = userName;
        this.address = address;
        this.city = city;
        this.email = email;
        this.mobileNo = mobileNo;
    }

    public String getCtznNo() {
        return ctznNo;
    }

    public void setCtznNo(String ctznNo) {
        this.ctznNo = ctznNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserDetails{");
        sb.append("ctznNo=").append(ctznNo);
        sb.append(", password=").append(password);
        sb.append(", userName=").append(userName);
        sb.append(", address=").append(address);
        sb.append(", city=").append(city);
        sb.append(", email=").append(email);
        sb.append(", mobileNo=").append(mobileNo);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
