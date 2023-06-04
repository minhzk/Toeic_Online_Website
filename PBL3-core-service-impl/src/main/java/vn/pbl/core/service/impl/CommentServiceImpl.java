package vn.pbl.core.service.impl;

import vn.myclass.core.utils.CommentBeanUtil;
import vn.myclass.core.utils.UserBeanUtil;
import vn.pbl.core.dao.CommentDao;
import vn.pbl.core.dto.CommentDTO;
import vn.pbl.core.dto.UserDTO;
import vn.pbl.core.persistence.entity.CommentEntity;
import vn.pbl.core.persistence.entity.ListenGuidelineEntity;
import vn.pbl.core.persistence.entity.UserEntity;
import vn.pbl.core.service.CommentService;
import vn.pbl.core.service.utils.SingletonDaoUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentServiceImpl implements CommentService {
    @Override
    public Object[] findAllCommentByProperties(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit,Integer listenGuidelineId) {
        List<CommentDTO> result = new ArrayList<CommentDTO>();
        String whereClause = null;
        if (listenGuidelineId != null) {
            whereClause = " AND listenguidelineid = "+listenGuidelineId+"";
        }
        Object[] objects = SingletonDaoUtil.getCommentDaoInstance().findByProperty(property,sortExpression,sortDirection,offset,limit,whereClause);
        for(CommentEntity item: (List<CommentEntity>)objects[1]) {
           CommentDTO dto = CommentBeanUtil.entity2Dto(item);
           UserEntity userEntity = SingletonDaoUtil.getUserDaoInstance().findById(item.getUser().getUserId());
           dto.setUser(UserBeanUtil.entity2Dto(userEntity));
           result.add(dto);
        }
        objects[1] = result;
        return objects;
    }

    @Override
    public CommentDTO saveComment(String email, Integer listenGuidelineId, String content) {
        CommentDTO commentDTO = new CommentDTO();
        if(email != null && listenGuidelineId != null && content != null ) {
            UserEntity userEntity = SingletonDaoUtil.getUserDaoInstance().findEqualUnique("email",email);
            ListenGuidelineEntity listenGuidelineEntity = SingletonDaoUtil.getListenGuidelineDaoInstance().findById(listenGuidelineId);
            CommentEntity commentEntity = new CommentEntity();
            commentEntity.setUser(userEntity);
            commentEntity.setListenGuideline(listenGuidelineEntity);
            commentEntity.setContent(content);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            commentEntity.setCreatedDate(timestamp);
            commentEntity =  SingletonDaoUtil.getCommentDaoInstance().save(commentEntity);
            commentDTO = CommentBeanUtil.entity2Dto(commentEntity);
        }
       return commentDTO;
    }
}
