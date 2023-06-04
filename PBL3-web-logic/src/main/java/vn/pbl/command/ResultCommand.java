package vn.pbl.command;

import vn.pbl.core.dto.ResultDTO;
import vn.pbl.core.web.command.AbstractCommand;

public class ResultCommand extends AbstractCommand<ResultDTO> {
    public ResultCommand()  {
        this.pojo = new ResultDTO();
    }
}
