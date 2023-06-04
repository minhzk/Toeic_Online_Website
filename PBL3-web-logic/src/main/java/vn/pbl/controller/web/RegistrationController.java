package vn.pbl.controller.web;

import vn.pbl.command.UserCommand;
import vn.pbl.core.common.util.SessionUtil;
import vn.pbl.core.dto.CheckLogin;
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

@WebServlet("/registration.html")
public class RegistrationController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("views/web/registration.jsp");
        rd.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setName(name);
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(3);
        userDTO.setRoleDTO(roleDTO);
        Map<String, Object> property = new HashMap<>();
        property.put("email", userDTO.getEmail());
        Object[] objects = SingletonServiceUtil.getUserServiceInstance().findByProperty(property,null,null,null,null );
        if((long)objects[0] > 0) {
            request.setAttribute(WebConstant.MESSAGE_RESPONSE,"Tài khoản đã tồn tại");
            RequestDispatcher rd = request.getRequestDispatcher("views/web/registration.jsp");
            rd.forward(request, response);
        }
        else{
            SingletonServiceUtil.getUserServiceInstance().saveUser(userDTO);
            SessionUtil.getInstance().putValue(request, WebConstant.LOGIN_NAME, userDTO.getEmail());
            response.sendRedirect("/home.html");
        }
    }
}
