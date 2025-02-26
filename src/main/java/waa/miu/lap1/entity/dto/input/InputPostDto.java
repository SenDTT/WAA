package waa.miu.lap1.entity.dto.input;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class InputPostDto {
    int id;
    String title;
    String content;
    int author_id;
}
