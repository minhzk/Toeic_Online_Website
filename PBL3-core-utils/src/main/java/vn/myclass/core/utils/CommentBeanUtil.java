package vn.myclass.core.utils;

import vn.pbl.core.dto.CommentDTO;
import vn.pbl.core.dto.RoleDTO;
import vn.pbl.core.persistence.entity.CommentEntity;
import vn.pbl.core.persistence.entity.RoleEntity;

public class CommentBeanUtil {
    public static CommentDTO entity2Dto(CommentEntity entity) {
        CommentDTO dto = new CommentDTO();
        dto.setCommentId(entity.getCommentId());
        dto.setContent(entity.getContent());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    public static CommentEntity dto2Entity(CommentDTO dto) {
        CommentEntity entity = new CommentEntity();
        entity.setCommentId(dto.getCommentId());
        entity.setContent(dto.getContent());
        entity.setCreatedDate(dto.getCreatedDate());
        return entity;
    }
}

