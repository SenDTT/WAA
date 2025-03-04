package waa.miu.lap1.entity.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDto {
    long id;
    String name;
    String email;
    String password;
}
