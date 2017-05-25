package iq.ven.workflow.models.impl;

import iq.ven.workflow.models.User;
import iq.ven.workflow.models.UserTypes;

import java.math.BigInteger;
import java.util.Date;

public class UserImpl implements User {

    private BigInteger userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date registrationDate;
    private UserTypes userRole;

    private UserImpl(UserBuilder builder) {
        this.userId = builder.userId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
        this.registrationDate = builder.registrationDate;
        this.userRole = builder.userRole;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public UserTypes getUsersRole() {
        return userRole;
    }


    public static class UserBuilder {
        private BigInteger userId;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private Date registrationDate;
        private UserTypes userRole;

        public UserBuilder(BigInteger userId, String firstName, String lastName, String email, String password, Date registrationDate, UserTypes userRole) {
            this.userId = userId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.registrationDate = registrationDate;
            this.userRole = userRole;
        }

        public UserImpl buildUser() {
            return new UserImpl(this);
        }

    }

    @Override
    public String toString() {
        return "UserImpl{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registrationDate=" + registrationDate +
                ", userRole=" + userRole +
                '}';
    }
}
