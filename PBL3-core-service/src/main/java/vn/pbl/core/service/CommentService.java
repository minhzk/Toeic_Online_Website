package vn.pbl.core.service;

import vn.pbl.core.dao.CommentDao;
import vn.pbl.core.dto.CommentDTO;

import java.util.List;
import java.util.Map;

public interface CommentService {
    Object[] findAllCommentByProperties(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit, Integer listenGuidelineId);
    CommentDTO saveComment(String email, Integer listenGuidelineId, String content);
}
