package vn.pbl.core.service;

import java.util.Map;

public interface ExaminationService {
    Object[] findExaminationByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
}
