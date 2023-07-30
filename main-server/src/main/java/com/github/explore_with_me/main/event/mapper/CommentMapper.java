package com.github.explore_with_me.main.event.mapper;

import com.github.explore_with_me.main.event.dto.CommentDto;
import com.github.explore_with_me.main.event.model.Comment;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "authorName", source = "comment.author.name")
    CommentDto commentToCommentDto(Comment comment);

    @Mapping(target = "authorName", source = "comment.author.name")
    List<CommentDto> commentToCommentDto(List<Comment> comments);
}
