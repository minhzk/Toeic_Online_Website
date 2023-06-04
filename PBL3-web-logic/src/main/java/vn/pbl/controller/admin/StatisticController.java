package vn.pbl.controller.admin;

import vn.pbl.core.service.utils.SingletonDaoUtil;
import vn.pbl.core.web.utils.SingletonServiceUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = {"/admin-user-statistic.html","/admin-guideline-statistic.html"})
public class StatisticController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if(requestURI.equals("/admin-user-statistic.html")) {
            Long male = SingletonServiceUtil.getUserServiceInstance().maleCount(null, null, null, null, null);
            Long female = SingletonServiceUtil.getUserServiceInstance().femaleCount(null, null, null, null, null);
            Long nullGender = SingletonServiceUtil.getUserServiceInstance().nullGenderCount(null, null, null, null, null);
            request.setAttribute("maleCount", male);
            request.setAttribute("femaleCount", female);
            request.setAttribute("nullGender", nullGender);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/statistics/userstatistic.jsp");
            rd.forward(request, response);
        }else if(requestURI.equals("/admin-guideline-statistic.html")) {
            Integer listenGuidelineCount = SingletonServiceUtil.getListenGuidelineServiceInstance().count();
            Integer readGuidelineCount = SingletonServiceUtil.getReadGuidelineServiceInstance().count();
            request.setAttribute("listenGuidelineCount", listenGuidelineCount);
            request.setAttribute("readGuidelineCount", readGuidelineCount);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/statistics/guidelinestatistic.jsp");
            rd.forward(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
