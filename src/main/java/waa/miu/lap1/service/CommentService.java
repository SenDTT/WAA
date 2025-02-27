package waa.miu.lap1.service;

import waa.miu.lap1.entity.dto.input.InputCommentDto;
import waa.miu.lap1.entity.dto.output.SimpleCommentDto;

import java.util.List;

public interface CommentService {
    public void save(InputCommentDto comment);
    public SimpleCommentDto getComment(int id);
    public List<SimpleCommentDto> getPaginationComments(int postId);
    public List<SimpleCommentDto> getComments();
}
