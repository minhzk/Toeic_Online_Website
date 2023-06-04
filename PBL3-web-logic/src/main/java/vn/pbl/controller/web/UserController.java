package vn.pbl.controller.web;

import vn.pbl.command.UserCommand;
import vn.pbl.core.common.util.SessionUtil;
import vn.pbl.core.dto.RoleDTO;
import vn.pbl.core.dto.UserDTO;
import vn.pbl.core.web.common.WebConstant;
import vn.pbl.core.web.utils.FormUtil;
import vn.pbl.core.web.utils.SingletonServiceUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user-information.html")
public class UserController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login_name = (String) SessionUtil.getInstance().getValue(request,"login_name");
        //String login_name = request.getParameter("login_name");
        String email = "email";
        UserDTO dto = SingletonServiceUtil.getUserServiceInstance().findEqualUnique(email,login_name);
        UserCommand command = new UserCommand();
        command.setPojo(dto);
        request.setAttribute(WebConstant.FORM_ITEM,command);
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/user/information.jsp");
        rd.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserCommand command = FormUtil.populate(UserCommand.class, request);
        UserDTO pojo = command.getPojo();
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(3);
        pojo.setRoleDTO(roleDTO);
        String email = (String) SessionUtil.getInstance().getValue(request,"login_name");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        Integer age = Integer.valueOf(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        pojo.setEmail(email);
        pojo.setPassword(password);
        pojo.setName(name);
        pojo.setAge(age);
        pojo.setAddress(address);
        pojo.setPhone(phone);
        pojo.setGender(gender);
        pojo.setIsActive(1);
        pojo.setIsVip(1);
        SingletonServiceUtil.getUserServiceInstance().updateUser(pojo);
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/user/information.jsp");
        rd.forward(request, response);

    }
}

