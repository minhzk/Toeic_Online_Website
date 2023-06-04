package vn.pbl.core.service;

import vn.pbl.core.dto.ExaminationQuestionDTO;
import vn.pbl.core.dto.ResultDTO;

import java.util.List;

public interface ResultService {
    ResultDTO saveResult(String userName, Integer examinationId, List<ExaminationQuestionDTO> examinationQuestions);
    Object[] findResultByUserId(Integer userId);
}
