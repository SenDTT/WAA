package waa.miu.lap1.entity.dtos;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Data
@Getter
@Service
public class SimplePostDto {
    long id;
    String title;
}
