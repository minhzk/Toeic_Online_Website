package vn.pbl.controller.web;

import org.apache.commons.lang.StringUtils;
import vn.pbl.command.ReadGuidelineCommand;
import vn.pbl.core.common.util.SessionUtil;
import vn.pbl.core.dto.CommentReadDTO;
import vn.pbl.core.dto.ReadGuidelineDTO;
import vn.pbl.core.web.common.WebConstant;
import vn.pbl.core.web.utils.FormUtil;
import vn.pbl.core.web.utils.RequestUtil;
import vn.pbl.core.web.utils.SingletonServiceUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/danh-sach-huong-dan-doc.html","/noi-dung-bai-huong-dan-doc.html"})
public class ReadGuidelineController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ReadGuidelineCommand command = FormUtil.populate(ReadGuidelineCommand.class, request);
        if (request.getParameter("readguidelineid") != null) {
            String readGuidelineStr = request.getParameter("readguidelineid");
            ReadGuidelineDTO existReadGuideline = SingletonServiceUtil.getReadGuidelineServiceInstance().findByReadGuidelineId("readGuidelineId", Integer.parseInt(readGuidelineStr));
            command.setPojo(existReadGuideline);
            Object[] obj= SingletonServiceUtil.getCommentReadServiceInstance().findAllCommentByProperties(null,null,null,null,null,existReadGuideline.getReadGuidelineId());
            List<CommentReadDTO> comment = (List<CommentReadDTO>)obj[1];
            request.setAttribute(WebConstant.LIST_COMMENT,comment);
            request.setAttribute(WebConstant.FORM_ITEM, command);
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/readguideline/detail.jsp");
            rd.forward(request, response);
        } else {
            executeSearchReadGuideline(request, command);
            request.setAttribute(WebConstant.LIST_ITEMS, command);
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/readguideline/list.jsp");
            rd.forward(request, response);
        }
    }

    private void executeSearchReadGuideline(HttpServletRequest request, ReadGuidelineCommand command) {
        Map<String, Object> properties = buildMapProperties(command);
        command.setMaxPageItems(3);
        RequestUtil.initSearchBeanManual(command);
        Object[] objects = SingletonServiceUtil.getReadGuidelineServiceInstance().findReadGuidelineByProperties(properties, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
        command.setListResult((List<ReadGuidelineDTO>) objects[1]);
        command.setTotalItems(Integer.parseInt(objects[0].toString()));
        command.setTotalPages((int) Math.ceil((double) command.getTotalItems() / command.getMaxPageItems()));
    }

    private Map<String,Object> buildMapProperties(ReadGuidelineCommand command) {
        Map<String, Object> properties = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(command.getPojo().getTitle())) {
            properties.put("title", command.getPojo().getTitle());
        }
        return properties;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String readGuidelineId = request.getParameter("readGuidelineId");
        String email = (String) SessionUtil.getInstance().getValue(request, WebConstant.LOGIN_NAME);
        String content = request.getParameter("content");
        CommentReadDTO commentReadDTO = SingletonServiceUtil.getCommentReadServiceInstance().saveComment(email,Integer.parseInt(readGuidelineId),content);
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/readguideline/detail.jsp");
        rd.forward(request, response);
    }
}
