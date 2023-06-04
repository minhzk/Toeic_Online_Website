package vn.pbl.command;

import vn.pbl.core.dto.ExerciseQuestionDTO;
import vn.pbl.core.web.command.AbstractCommand;

public class ExerciseQuestionCommand extends AbstractCommand<ExerciseQuestionDTO> {
    public ExerciseQuestionCommand() {
        this.pojo = new ExerciseQuestionDTO();
    }
    private Integer exerciseId;
    private String answerUser;
    private boolean checkAnswer;

    public Integer getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Integer exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getAnswerUser() {
        return answerUser;
    }

    public void setAnswerUser(String answerUser) {
        this.answerUser = answerUser;
    }

    public boolean isCheckAnswer() {
        return checkAnswer;
    }

    public void setCheckAnswer(boolean checkAnswer) {
        this.checkAnswer = checkAnswer;
    }
}
