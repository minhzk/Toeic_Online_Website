package vn.pbl.core.daoimpl;

import vn.pbl.core.dao.ExaminationQuestionDao;
import vn.pbl.core.data.daoimpl.AbstractDao;
import vn.pbl.core.persistence.entity.ExaminationQuestionEntity;

import java.util.Map;

public class ExaminationQuestionDaoImpl extends AbstractDao<Integer, ExaminationQuestionEntity> implements ExaminationQuestionDao {
    @Override
    public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit, Integer examinationId) {
        String whereClause = null;
        if (examinationId != null) {
            whereClause = " AND examination.examinationId = "+examinationId+"";
        }
        return super.findByProperty(property, sortExpression, sortDirection, offset, limit, whereClause);
    }
}
