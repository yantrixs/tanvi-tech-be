package com.tanvi.tech.tanvitechbe.model;

public class ResetPassword extends BaseEntity {

    private static final long serialVersionUID = 1170736818292321925L;

    private String email;
    private String resetToken;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }
}
