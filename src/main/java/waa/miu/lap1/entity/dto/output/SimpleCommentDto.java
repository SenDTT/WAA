package waa.miu.lap1.entity.dto.output;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SimpleCommentDto {
    private long id;
    private String text;
}
