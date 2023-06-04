package vn.pbl.core.service;

import vn.pbl.core.dto.CommentReadDTO;

import java.util.Map;

public interface CommentReadService {
    Object[] findAllCommentByProperties(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit, Integer readGuidelineId);
    CommentReadDTO saveComment(String email, Integer readGuidelineId, String content);
}
