package vn.pbl.core.service;

import java.util.Map;

public interface ExaminationQuestionService {
    Object[] findExaminationQuestionByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit, Integer examinationId);
}
