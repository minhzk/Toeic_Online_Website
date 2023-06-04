package vn.pbl.core.service;

import java.util.Map;

public interface ExerciseQuestionService {
    Object[] findExerciseQuestionByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit,Integer exerciseId);
}
