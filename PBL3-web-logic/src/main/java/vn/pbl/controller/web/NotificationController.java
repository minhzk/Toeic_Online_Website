package vn.pbl.controller.web;

import vn.pbl.core.dto.NotificationDTO;
import vn.pbl.core.web.common.WebConstant;
import vn.pbl.core.web.utils.SingletonServiceUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/notification.html")
public class NotificationController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<NotificationDTO> notificationDTO = SingletonServiceUtil.getNotificationServiceInstance().findAll();
        request.setAttribute(WebConstant.LIST_ITEMS,notificationDTO);
        RequestDispatcher rd = request.getRequestDispatcher("views/web/notification/detail.jsp");
        rd.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
