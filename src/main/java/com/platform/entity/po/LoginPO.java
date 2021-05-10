package com.platform.entity.po;

import lombok.Data;
import org.bouncycastle.cms.PasswordRecipientId;

import javax.annotation.security.DenyAll;
import java.io.Serializable;

@Data
public class LoginPO implements Serializable {

    private String username;
    private String password;
}
