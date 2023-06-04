package vn.pbl.core.service.impl;

import vn.myclass.core.utils.CommentReadBeanUtil;
import vn.myclass.core.utils.UserBeanUtil;
import vn.pbl.core.dto.CommentReadDTO;
import vn.pbl.core.persistence.entity.*;
import vn.pbl.core.service.CommentReadService;
import vn.pbl.core.service.utils.SingletonDaoUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentReadServiceImpl implements CommentReadService {
    @Override
    public Object[] findAllCommentByProperties(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit, Integer readGuidelineId) {
        List<CommentReadDTO> result = new ArrayList<CommentReadDTO>();
        String whereClause = null;
        if (readGuidelineId != null) {
            whereClause = " AND readguidelineid = "+readGuidelineId+"";
        }
        Object[] objects = SingletonDaoUtil.getCommentReadDaoInstance().findByProperty(property,sortExpression,sortDirection,offset,limit,whereClause);
        for(CommentReadEntity item: (List<CommentReadEntity>)objects[1]) {
            CommentReadDTO dto = CommentReadBeanUtil.entity2Dto(item);
            UserEntity userEntity = SingletonDaoUtil.getUserDaoInstance().findById(item.getUser().getUserId());
            dto.setUser(UserBeanUtil.entity2Dto(userEntity));
            result.add(dto);
        }
        objects[1] = result;
        return objects;
    }

    @Override
    public CommentReadDTO saveComment(String email, Integer readGuidelineId, String content) {
        CommentReadDTO commentReadDTO = new CommentReadDTO();
        if(email != null && readGuidelineId != null && content != null ) {
            UserEntity userEntity = SingletonDaoUtil.getUserDaoInstance().findEqualUnique("email",email);
            ReadGuidelineEntity readGuidelineEntity = SingletonDaoUtil.getReadGuidelineDaoInstance().findById(readGuidelineId);
            CommentReadEntity commentReadEntity = new CommentReadEntity();
            commentReadEntity.setUser(userEntity);
            commentReadEntity.setReadGuideline(readGuidelineEntity);
            commentReadEntity.setContent(content);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            commentReadEntity.setCreatedDate(timestamp);
            commentReadEntity =  SingletonDaoUtil.getCommentReadDaoInstance().save(commentReadEntity);
            commentReadDTO = CommentReadBeanUtil.entity2Dto(commentReadEntity);
        }
        return commentReadDTO;
    }
}
