package vn.pbl.core.service.utils;

import vn.pbl.core.dao.ExaminationDao;
import vn.pbl.core.dao.ExerciseDao;
import vn.pbl.core.dao.ExerciseQuestionDao;
import vn.pbl.core.dao.ResultDao;
import vn.pbl.core.daoimpl.*;

public class SingletonDaoUtil {
    private static UserDaoImpl userDaoImpl = null;
    private static RoleDaoImpl roleDaoImpl = null;
    private static ListenGuidelineDaoImpl listenGuidelineDaoImpl = null;
    private static ReadGuidelineDaoImpl readGuidelineDaoImpl = null;
    private static CommentDaoImpl commentDaoImpl = null;
    private static CommentReadDaoImpl commentReadDaoImpl = null;
    private static ExaminationDaoImpl examinationDaoImpl = null;
    private static ExaminationQuestionDaoImpl examinationQuestionDaoImpl = null;
    private static ExerciseDaoImpl exerciseDaoImpl = null;
    private static ExerciseQuestionDaoImpl exerciseQuestionDaoImpl = null;
    private static ResultDaoImpl resultDaoImpl = null;
    private static NotificationDaoImpl notificationDaoImpl = null ;
    public static UserDaoImpl getUserDaoInstance() {
        if(userDaoImpl == null) {
            userDaoImpl = new UserDaoImpl();
        }
        return  userDaoImpl;
    }

    public static RoleDaoImpl getRoleDaoInstance() {
        if(roleDaoImpl == null) {
            roleDaoImpl = new RoleDaoImpl();
        }
        return  roleDaoImpl;
    }
    public static ListenGuidelineDaoImpl getListenGuidelineDaoInstance() {
        if(listenGuidelineDaoImpl == null) {
            listenGuidelineDaoImpl = new ListenGuidelineDaoImpl();
        }
        return  listenGuidelineDaoImpl;
    }
    public static ReadGuidelineDaoImpl getReadGuidelineDaoInstance() {
        if(readGuidelineDaoImpl == null) {
            readGuidelineDaoImpl = new ReadGuidelineDaoImpl();
        }
        return readGuidelineDaoImpl;
    }
    public static CommentDaoImpl getCommentDaoInstance() {
        if (commentDaoImpl == null) {
            commentDaoImpl = new CommentDaoImpl();
        }
        return commentDaoImpl;
    }
    public static CommentReadDaoImpl getCommentReadDaoInstance() {
        if (commentReadDaoImpl == null) {
            commentReadDaoImpl = new CommentReadDaoImpl();
        }
        return commentReadDaoImpl;
    }

    public static ExaminationDaoImpl getExaminationDaoInstance() {
        if (examinationDaoImpl == null) {
            examinationDaoImpl = new ExaminationDaoImpl();
        }
        return examinationDaoImpl;
    }

    public static ExaminationQuestionDaoImpl getExaminationQuestionDaoInstance() {
        if (examinationQuestionDaoImpl == null) {
            examinationQuestionDaoImpl = new ExaminationQuestionDaoImpl();
        }
        return examinationQuestionDaoImpl;
    }

    public static ExerciseDaoImpl getExerciseDaoInstance() {
        if (exerciseDaoImpl == null) {
            exerciseDaoImpl = new ExerciseDaoImpl();
        }
        return exerciseDaoImpl;
    }

    public static ExerciseQuestionDaoImpl getExerciseQuestionDaoInstance() {
        if (exerciseQuestionDaoImpl == null) {
            exerciseQuestionDaoImpl = new ExerciseQuestionDaoImpl();
        }
        return exerciseQuestionDaoImpl;
    }
    public static ResultDaoImpl getResultDaoInstance()  {
        if(resultDaoImpl == null) {
            resultDaoImpl = new ResultDaoImpl();
        }
        return resultDaoImpl;
    }
    public static NotificationDaoImpl getNotificationDaoInstance()  {
        if(notificationDaoImpl == null) {
            notificationDaoImpl = new NotificationDaoImpl();
        }
        return notificationDaoImpl;
    }
}
