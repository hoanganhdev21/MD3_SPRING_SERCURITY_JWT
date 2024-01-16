package com.securityjwt.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserLogin {
    //    private String userName;
//    private String password;
    @NotNull(message = "Không đuược null")
    @NotEmpty(message = "Không được rỗng")
    @NotBlank(message = "Không được để trống")
    @Size // dùng cho chuỗi
    @Length // dùng cho chuỗi
//    @Min()
//    @Max() // dùng cho số
//    @Pattern() // dùng pattern để validate
    private String username;

    @NotNull(message = "Không đuược null")
    @NotEmpty(message = "Không được rỗng")
    @NotBlank(message = "Không được để trống")
    private String password;
}
