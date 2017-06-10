package iq.ven.workflow.models.impl;

import iq.ven.workflow.models.User;
import iq.ven.workflow.models.UserFile;
import iq.ven.workflow.models.UserTypes;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class UserImpl implements User {

    private BigInteger userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date registrationDate;
    private UserTypes userRole;
    private List<UserFile> filesUserHaveAccessTo;

    public UserImpl() {
    }

    private UserImpl(UserBuilder builder) {
        this.userId = builder.userId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
        this.registrationDate = builder.registrationDate;
        this.userRole = builder.userRole;
        this.filesUserHaveAccessTo = builder.filesUserHaveAccessTo;
    }

    @Override
    public BigInteger getUserId() {
        return userId;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getFullName() {
        return lastName + " " + firstName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Date getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public UserTypes getUsersRole() {
        return userRole;
    }

    @Override
    public List<UserFile> getFilesUserHaveAccessTo() {
        return filesUserHaveAccessTo;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setUserRole(UserTypes userRole) {
        this.userRole = userRole;
    }

    public void setFilesUserHaveAccessTo(List<UserFile> filesUserHaveAccessTo) {
        this.filesUserHaveAccessTo = filesUserHaveAccessTo;
    }

    public static class UserBuilder {
        private BigInteger userId;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private Date registrationDate;
        private UserTypes userRole;
        private List<UserFile> filesUserHaveAccessTo;

        public UserBuilder(String firstName, String lastName, String email, String password, UserTypes userRole) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.userRole = userRole;
        }


        public UserBuilder buildUserId(BigInteger userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder buildRegistrationDate(Date registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public UserBuilder buildFilesUserHaveAccessTo(List<UserFile> filesUserHaveAccessTo) {
            this.filesUserHaveAccessTo = filesUserHaveAccessTo;
            return this;
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
