package vn.pbl.command;

import vn.pbl.core.dto.ExerciseDTO;
import vn.pbl.core.web.command.AbstractCommand;

public class ExerciseCommand extends AbstractCommand<ExerciseDTO> {
    public ExerciseCommand() {
        this.pojo = new ExerciseDTO();
    }
}
