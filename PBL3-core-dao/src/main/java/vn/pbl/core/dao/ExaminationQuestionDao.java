package vn.pbl.core.dao;

import vn.pbl.core.data.dao.GenericDao;
import vn.pbl.core.persistence.entity.ExaminationQuestionEntity;

import java.util.Map;

public interface ExaminationQuestionDao extends GenericDao<Integer, ExaminationQuestionEntity> {
    Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit, Integer examinationId);
}
