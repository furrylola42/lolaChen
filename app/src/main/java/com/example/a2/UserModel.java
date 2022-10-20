package com.example.a2;

import java.io.Serializable;

public class UserModel implements Serializable {

        private String uid;
        private String image;
        private String firstName;
        private String lastName;
        private String email;
        private String password;

        public String getUid() {
                return uid;
        }

        public void setUid(String uid) {
                this.uid = uid;
        }

        public String getImage() {
                return image;
        }

        public void setImage(String image) {
                this.image = image;
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

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }
}
