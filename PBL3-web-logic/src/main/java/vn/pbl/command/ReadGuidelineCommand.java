package vn.pbl.command;

import vn.pbl.core.dto.ReadGuidelineDTO;
import vn.pbl.core.web.command.AbstractCommand;

public class ReadGuidelineCommand extends AbstractCommand<ReadGuidelineDTO> {
    public  ReadGuidelineCommand() {
        this.pojo = new ReadGuidelineDTO();
    }
}
