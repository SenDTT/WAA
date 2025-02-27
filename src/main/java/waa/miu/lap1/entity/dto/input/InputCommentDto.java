package waa.miu.lap1.entity.dto.input;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class InputCommentDto {
    String text;
    int post_id;
}
