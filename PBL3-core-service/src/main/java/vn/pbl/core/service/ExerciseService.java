package vn.pbl.core.service;

import java.util.Map;

public interface ExerciseService {
    Object[] findExerciseByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
}
