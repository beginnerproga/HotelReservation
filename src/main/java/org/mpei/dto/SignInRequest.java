package org.mpei.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на аутентификацию")
public class SignInRequest {

    @Schema(description = "Имя пользователя", example = "Jon")
    @Size(min = 4, max = 50, message = "Имя пользователя должно содержать от 4 до 50 символов")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    private String username;

    @Schema(description = "Пароль", example = "my_1secret1_password")
    @Size(max = 50, message = "Длина пароля должна быть  до 50 символов")
    @NotBlank(message = "Пароль не может быть пустыми")
    private String password;
}