package vn.myclass.core.utils;

import vn.pbl.core.dto.CommentReadDTO;
import vn.pbl.core.persistence.entity.CommentReadEntity;

public class CommentReadBeanUtil {
    public static CommentReadDTO entity2Dto(CommentReadEntity entity) {
        CommentReadDTO dto = new CommentReadDTO();
        dto.setCommentId(entity.getCommentId());
        dto.setContent(entity.getContent());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    public static CommentReadEntity dto2Entity(CommentReadDTO dto) {
        CommentReadEntity entity = new CommentReadEntity();
        entity.setCommentId(dto.getCommentId());
        entity.setContent(dto.getContent());
        entity.setCreatedDate(dto.getCreatedDate());
        return entity;
    }
}
