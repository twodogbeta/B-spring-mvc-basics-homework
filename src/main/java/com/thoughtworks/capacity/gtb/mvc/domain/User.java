package com.thoughtworks.capacity.gtb.mvc.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;

    @NotNull(message = "用户名不能为空")
    @Size(min = 3, max = 10, message = "用户名长度必须为3到10位")
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,10}$", message ="用户名只能由字母、数字或下划线组成")
    private String username;

    @NotNull(message = "密码不能为空")
    @Size(min = 5, max = 12, message = "密码长度必须为5到12位")
    private String password;

    @Email(message = "邮箱格式不正确")
    private String email;
}