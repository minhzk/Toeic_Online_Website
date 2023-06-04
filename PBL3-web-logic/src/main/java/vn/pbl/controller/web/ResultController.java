package vn.pbl.controller.web;

import vn.pbl.command.ResultCommand;
import vn.pbl.core.common.util.SessionUtil;
import vn.pbl.core.dto.ExaminationDTO;
import vn.pbl.core.dto.ResultDTO;
import vn.pbl.core.dto.UserDTO;
import vn.pbl.core.service.utils.SingletonDaoUtil;
import vn.pbl.core.web.common.WebConstant;
import vn.pbl.core.web.utils.SingletonServiceUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user-result.html")
public class ResultController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
        ResultCommand resultCommand = new ResultCommand();
        String email = (String) SessionUtil.getInstance().getValue(request,"login_name");
        UserDTO userDTO = SingletonServiceUtil.getUserServiceInstance().findEqualUnique("email",email);
        Object[] objects = SingletonServiceUtil.getResultServiceInstance().findResultByUserId(userDTO.getUserId());
        resultCommand.setListResult((List<ResultDTO>) objects[1]);
        resultCommand.setTotalItems(Integer.parseInt(objects[0].toString()));
        request.setAttribute(WebConstant.LIST_ITEMS,resultCommand);
        RequestDispatcher rd = request.getRequestDispatcher("views/web/user/result.jsp");
        rd.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
