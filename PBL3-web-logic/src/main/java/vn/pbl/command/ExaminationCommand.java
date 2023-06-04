package vn.pbl.command;

import vn.pbl.core.dto.ExaminationDTO;
import vn.pbl.core.web.command.AbstractCommand;

public class ExaminationCommand extends AbstractCommand<ExaminationDTO> {
    public ExaminationCommand() {
        this.pojo = new ExaminationDTO();
    }
}
