package vn.pbl.core.service.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.myclass.core.utils.ResultBeanUtil;
import vn.pbl.core.common.util.HibernateUtil;
import vn.pbl.core.dto.ExaminationQuestionDTO;
import vn.pbl.core.dto.ResultDTO;
import vn.pbl.core.persistence.entity.ExaminationEntity;
import vn.pbl.core.persistence.entity.ResultEntity;
import vn.pbl.core.persistence.entity.UserEntity;
import vn.pbl.core.service.ResultService;
import vn.pbl.core.service.utils.SingletonDaoUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ResultServiceImpl implements ResultService {
    @Override
    public ResultDTO saveResult(String email, Integer examinationId, List<ExaminationQuestionDTO> examinationQuestions) {
        ResultDTO result = new ResultDTO();
        if (email != null && examinationId != null && examinationQuestions != null) {
            UserEntity user = SingletonDaoUtil.getUserDaoInstance().findEqualUnique("email", email);
            ExaminationEntity examination = SingletonDaoUtil.getExaminationDaoInstance().findById(examinationId);
            ResultEntity resultEntity = new ResultEntity();
            calculateListenAndReadScore(resultEntity, examinationQuestions);
            initDataToResultEntity(resultEntity, user, examination);
            resultEntity= SingletonDaoUtil.getResultDaoInstance().save(resultEntity);
            result = ResultBeanUtil.entity2Dto(resultEntity);
        }
        return result;
    }

    private void initDataToResultEntity(ResultEntity resultEntity, UserEntity user, ExaminationEntity examination) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        resultEntity.setExamination(examination);
        resultEntity.setUser(user);
        resultEntity.setCreatedDate(timestamp);
    }

    private void calculateListenAndReadScore(ResultEntity resultEntity, List<ExaminationQuestionDTO> examinationQuestions) {
        int listenScore = 0;
        int readingScore = 0;
        for (ExaminationQuestionDTO item: examinationQuestions) {
            if (item.getAnswerUser() != null) {
                if (item.getAnswerUser().equals(item.getCorrectAnswer())) {
                    if (item.getNumber() <= 4) {
                        listenScore++;
                    } else {
                        readingScore++;
                    }
                }
            }
        }
        resultEntity.setListenScore(listenScore);
        resultEntity.setReadingScore(readingScore);
    }
    @Override
    public Object[] findResultByUserId(Integer userId) {
        String whereClause = "and userid = " + userId;
        Object[] objects = SingletonDaoUtil.getResultDaoInstance().findByProperty(null,null,null,null,null,whereClause);
        return objects;
    }

}
