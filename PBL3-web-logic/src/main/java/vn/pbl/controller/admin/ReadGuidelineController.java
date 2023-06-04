package vn.pbl.controller.admin;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import vn.pbl.command.ListenGuidelineCommand;
import vn.pbl.command.ReadGuidelineCommand;
import vn.pbl.core.common.util.UploadUtil;
import vn.pbl.core.dto.ListenGuidelineDTO;
import vn.pbl.core.dto.ReadGuidelineDTO;
import vn.pbl.core.web.common.WebConstant;
import vn.pbl.core.web.utils.FormUtil;
import vn.pbl.core.web.utils.RequestUtil;
import vn.pbl.core.web.utils.SingletonServiceUtil;
import vn.pbl.core.web.utils.WebCommonUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/admin-guideline-read-list.html","/admin-guideline-read-edit.html"})
public class ReadGuidelineController extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ReadGuidelineCommand command = FormUtil.populate(ReadGuidelineCommand.class, request);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ResourcesBundle");
        if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)) {
            if (command.getCrudaction() != null && command.getCrudaction().equals(WebConstant.REDIRECT_DELETE)) {
                List<Integer> ids = new ArrayList<Integer>();
                for (String item: command.getCheckList()) {
                    ids.add(Integer.parseInt(item));
                }
                Integer result = SingletonServiceUtil.getReadGuidelineServiceInstance().delete(ids);
                if (result != ids.size()) {
                    command.setCrudaction(WebConstant.REDIRECT_ERROR);
                }
            }
            executeSearchReadGuideline(request, command);
            if (command.getCrudaction() != null) {
                Map<String, String> mapMessage = buidMapRedirectMessage(resourceBundle);
                WebCommonUtil.addRedirectMessage(request, command.getCrudaction(), mapMessage);
            }
            request.setAttribute(WebConstant.LIST_ITEMS, command);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/readguideline/list.jsp");
            rd.forward(request, response);
        } else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
            if (command.getPojo() != null && command.getPojo().getReadGuidelineId() != null) {
                command.setPojo(SingletonServiceUtil.getReadGuidelineServiceInstance().findByReadGuidelineId("readGuidelineId", command.getPojo().getReadGuidelineId()));
            }
            request.setAttribute(WebConstant.FORM_ITEM, command);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/readguideline/edit.jsp");
            rd.forward(request, response);
        }
    }

    private Map<String,String> buidMapRedirectMessage(ResourceBundle resourceBundle) {
        Map<String, String> mapMessage = new HashMap<String, String>();
        mapMessage.put(WebConstant.REDIRECT_INSERT, resourceBundle.getString("label.readguideline.add.success"));
        mapMessage.put(WebConstant.REDIRECT_UPDATE, resourceBundle.getString("label.readguideline.update.success"));
        mapMessage.put(WebConstant.REDIRECT_DELETE, resourceBundle.getString("label.readguideline.delete.success"));
        mapMessage.put(WebConstant.REDIRECT_ERROR, resourceBundle.getString("label.message.error"));
        return mapMessage;
    }

    private void executeSearchReadGuideline(HttpServletRequest request, ReadGuidelineCommand command) {
        Map<String, Object> properties = buildMapProperties(command);
        RequestUtil.initSearchBean(request, command);
        Object[] objects = SingletonServiceUtil.getReadGuidelineServiceInstance().findReadGuidelineByProperties(properties, command.getSortExpression(), command.getSortDirection(),command.getFirstItem(),command.getMaxPageItems());
        command.setListResult((List<ReadGuidelineDTO>) objects[1]);
        command.setTotalItems(Integer.parseInt(objects[0].toString()));
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
        ReadGuidelineCommand command = new ReadGuidelineCommand();
        ResourceBundle bundle = ResourceBundle.getBundle("ResourcesBundle");
        UploadUtil uploadUtil = new UploadUtil();
        Set<String> valueTitle = buildSetValueReadGuideline();
        Object[] objects = uploadUtil.writeOrUpdateFile(request, valueTitle, WebConstant.READGUIDELINE);
        boolean checkStatusUploadImage = (Boolean) objects[0];
        if (!checkStatusUploadImage) {
            response.sendRedirect("/admin-guideline-read-list.html?urlType=url_list&&crudaction=redirect_error");
        } else {
            ReadGuidelineDTO dto = command.getPojo();
            if (StringUtils.isNotBlank(objects[2].toString())) {
                dto.setImage(objects[2].toString());
            }
            Map<String, String> mapValue = (Map<String, String>) objects[3];
            dto = returnValueOfDTO(dto, mapValue);
            if (dto != null) {
                if (dto.getReadGuidelineId() != null) {
                    ReadGuidelineDTO readGuidelineDTO = SingletonServiceUtil.getReadGuidelineServiceInstance().findByReadGuidelineId("readGuidelineId", dto.getReadGuidelineId());
                    if (dto.getImage() == null) {
                        dto.setImage(readGuidelineDTO.getImage());
                    }
                    ReadGuidelineDTO result = SingletonServiceUtil.getReadGuidelineServiceInstance().updateReadGuideline(dto);
                    if (result != null) {
                        response.sendRedirect("/admin-guideline-read-list.html?urlType=url_list&&crudaction=redirect_update");
                    } else {
                        response.sendRedirect("/admin-guideline-read-list.html?urlType=url_list&&crudaction=redirect_error");
                    }
                } else {
                    try {
                        SingletonServiceUtil.getReadGuidelineServiceInstance().saveReadGuideline(dto);
                        response.sendRedirect("/admin-guideline-read-list.html?urlType=url_list&&crudaction=redirect_insert");
                    } catch (ConstraintViolationException e) {
                        log.error(e.getMessage(), e);
                        response.sendRedirect("/admin-guideline-read-list.html?urlType=url_list&crudaction=redirect_error");
                    }
                }
            }
        }
    }

    private ReadGuidelineDTO returnValueOfDTO(ReadGuidelineDTO dto, Map<String, String> mapValue) {
        for (Map.Entry<String, String> item: mapValue.entrySet()) {
            if (item.getKey().equals("pojo.title")) {
                dto.setTitle(item.getValue());
            } else if (item.getKey().equals("pojo.content")) {
                dto.setContent(item.getValue());
            } else if (item.getKey().equals("pojo.readGuidelineId")) {
                dto.setReadGuidelineId(Integer.parseInt(item.getValue().toString()));
            }
        }
        return dto;
    }
    private Set<String> buildSetValueReadGuideline() {
        Set<String> returnValue = new HashSet<String>();
        returnValue.add("pojo.title");
        returnValue.add("pojo.content");
        returnValue.add("pojo.readGuidelineId");
        return returnValue;
    }
}
