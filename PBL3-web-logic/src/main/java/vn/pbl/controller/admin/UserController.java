package vn.pbl.controller.admin;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vn.pbl.command.UserCommand;
import vn.pbl.core.common.util.ExcelPoiUtil;
import vn.pbl.core.common.util.SessionUtil;
import vn.pbl.core.common.util.UploadUtil;
import vn.pbl.core.dto.RoleDTO;
import vn.pbl.core.dto.UserDTO;
import vn.pbl.core.dto.UserImportDTO;
import vn.pbl.core.service.RoleService;
import vn.pbl.core.service.UserService;
import vn.pbl.core.service.impl.RoleServiceImpl;
import vn.pbl.core.service.impl.UserServiceImpl;
import vn.pbl.core.service.utils.SingletonDaoUtil;
import vn.pbl.core.web.common.WebConstant;
import vn.pbl.core.web.utils.FormUtil;
import vn.pbl.core.web.utils.RequestUtil;
import vn.pbl.core.web.utils.SingletonServiceUtil;
import vn.pbl.core.web.utils.WebCommonUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/admin-user-list.html","/ajax-admin-user-edit.html","/admin-user-import.html",
                            "/admin-user-import-validate.html"})

public class UserController extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());
    private final String SHOW_IMPORT_USER = "show_import_user";
    private final String READ_EXCEL = "read_excel";
    private final String VALIDATE_IMPORT = "validate_import";
    private final String LIST_USER_IMPORT = "list_user_import";
    private final String IMPORT_DATA = "import_data";
    ResourceBundle bundle = ResourceBundle.getBundle("ResourcesBundle");
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserCommand command = FormUtil.populate(UserCommand.class, request);
        UserDTO pojo = command.getPojo();
        if(command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)) {
            Map<String, Object> mapProperty = new HashMap<String, Object>();
            RequestUtil.initSearchBean(request,command);
            Object[] objects = SingletonServiceUtil.getUserServiceInstance().findByProperty(mapProperty, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
            command.setListResult((List<UserDTO>) objects[1]);
            command.setTotalItems(Integer.parseInt(objects[0].toString()));
            request.setAttribute(WebConstant.LIST_ITEMS,command);
            if(command.getCrudaction() != null) {
                Map<String, String> mapMessage = buildMapRedirectMessage(bundle);
                WebCommonUtil.addRedirectMessage(request,command.getCrudaction(),mapMessage);
            }
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/list.jsp");
            rd.forward(request, response);
        } else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
            if(pojo != null && pojo.getUserId() != null) {
                command.setPojo(SingletonServiceUtil.getUserServiceInstance().findById(pojo.getUserId()));
            }
            command.setRoles(SingletonServiceUtil.getRoleServiceInstance().findALl());
            request.setAttribute(WebConstant.FORM_ITEM,command);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
            rd.forward(request, response);
        }else if (command.getUrlType() != null && command.getUrlType().equals(SHOW_IMPORT_USER)) {
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/importuser.jsp");
            rd.forward(request, response);
        }else if(command.getUrlType() != null && command.getUrlType().equals(VALIDATE_IMPORT)) {
            List<UserImportDTO> userImportDTOS = (List<UserImportDTO>) SessionUtil.getInstance().getValue(request, LIST_USER_IMPORT);
            command.setUserImportDTOS(returnListUserImport(command, userImportDTOS, request));
            request.setAttribute(WebConstant.LIST_ITEMS, command);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/importuser.jsp");
            rd.forward(request, response);
        }
    }

    private List<UserImportDTO> returnListUserImport(UserCommand command,List<UserImportDTO> userImportDTOS, HttpServletRequest request) {
        command.setMaxPageItems(10);
        RequestUtil.initSearchBean(request,command);
        command.setTotalItems(userImportDTOS.size());
        int fromIndex = command.getFirstItem();
        if(fromIndex > command.getTotalItems()) {
            fromIndex = 0;
            command.setFirstItem(0);
        }
        int toIndex = command.getFirstItem() + command.getTotalItems();
        if(userImportDTOS.size() > 0 ) {
            if(toIndex > userImportDTOS.size()) {
                toIndex = userImportDTOS.size();
            }
        }
        return userImportDTOS.subList(fromIndex,toIndex);
    }

    private Map<String, String> buildMapRedirectMessage(ResourceBundle bundle) {
        Map<String, String> mapMessage = new HashMap<String, String>();
        mapMessage.put(WebConstant.REDIRECT_INSERT,bundle.getString("label.user.message.add.success"));
        mapMessage.put(WebConstant.REDIRECT_UPDATE,bundle.getString("label.user.message.update.success"));
        mapMessage.put(WebConstant.REDIRECT_DELETE,bundle.getString("label.user.message.delete.success"));
        mapMessage.put(WebConstant.REDIRECT_ERROR,bundle.getString("label.message.error"));
        return mapMessage;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UploadUtil uploadUtil = new UploadUtil();
        Set<String> value = new HashSet<String>();
        value.add("urlType");
        Object[] objects = uploadUtil.writeOrUpdateFile(request, value, "excel");
        try {
            UserCommand command = FormUtil.populate(UserCommand.class,request);
            UserDTO pojo = command.getPojo();
            if (command.getUrlType()!= null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
                if(command.getCrudaction() != null && command.getCrudaction().equals(WebConstant.INSERT_UPDATE)) {
                    RoleDTO roleDTO = new RoleDTO();
                    roleDTO.setRoleId(command.getRoleId());
                    pojo.setRoleDTO(roleDTO);   
                    if(pojo != null && pojo.getUserId() != null) {
                        SingletonServiceUtil.getUserServiceInstance().updateUser(pojo);
                        request.setAttribute(WebConstant.MESSAGE_RESPONSE,WebConstant.REDIRECT_UPDATE);
                    }else {
                        SingletonServiceUtil.getUserServiceInstance().saveUser(pojo);
                        request.setAttribute(WebConstant.MESSAGE_RESPONSE,WebConstant.REDIRECT_INSERT);
                    }
                }
                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
                rd.forward(request, response);
            }
            //here
            if(objects != null ) {
                String urlType = null;
                Map<String, String> mapValue = (Map<String, String>) objects[3];
                for(Map.Entry<String, String> item : mapValue.entrySet()) {
                    if(item.getKey().equals("urlType")) {
                        urlType = item.getValue();
                    }
                }
                if(urlType != null && urlType.equals(READ_EXCEL)) {
                    String fileLocation = objects[1].toString();
                    String fileName = objects[2].toString();
                    List<UserImportDTO> excelValues = returnValueFromExcel(fileName, fileLocation);
                    validateData(excelValues);
                    SessionUtil.getInstance().putValue(request, LIST_USER_IMPORT, excelValues);
                    response.sendRedirect("/admin-user-import-validate.html?urlType=validate_import");
                }
            }
            if(command.getUrlType() != null && command.getUrlType().equals(IMPORT_DATA)) {
                List<UserImportDTO> userImportDTOS = (List<UserImportDTO>) SessionUtil.getInstance().getValue(request, LIST_USER_IMPORT);
                SingletonServiceUtil.getUserServiceInstance().saveUserImport(userImportDTOS);
                SessionUtil.getInstance().remove(request,LIST_USER_IMPORT);
                response.sendRedirect("/admin-user-list.html?urlType=url_list");
            }
        }catch (Exception e) {
            log.error(e.getMessage(),e);
            request.setAttribute(WebConstant.MESSAGE_RESPONSE,WebConstant.REDIRECT_ERROR);
        }

    }

    private void validateData(List<UserImportDTO> excelValues) {
        Set<String> stringSet = new HashSet<String>();
        for(UserImportDTO item : excelValues)  {
            validateRequiredField(item);
            validateDuplicate(item, stringSet);
        }
        SingletonServiceUtil.getUserServiceInstance().validateImportUser(excelValues);
    }

    private void validateDuplicate(UserImportDTO item, Set<String> stringSet) {
        String message = item.getError();
        if(! stringSet.contains(item.getEmail())) {
            stringSet.add(item.getEmail());
        }else {
            if(item.isValid()) {
                message += "<br/";
                message+= bundle.getString("label.email.duplicate");
            }
        }
        if(StringUtils.isNotBlank(message)) {
            item.setValid(false);
            item.setError(message);
        }
    }

    private void validateRequiredField(UserImportDTO item) {
        String message = "";
        if(StringUtils.isBlank(item.getEmail())) {
            message += "<br/";
            message += bundle.getString("label.email.notempty");
        }
        if(StringUtils.isBlank(item.getPassword())) {
            message += "<br/";
            message += bundle.getString("label.password.notempty");
        }
        if(StringUtils.isBlank(item.getRoleName())) {
            message += "<br/";
            message += bundle.getString("label.rolename.notempty");
        }
        if(StringUtils.isBlank(message)) {
            item.setValid(false);
        }
        item.setError(message);
    }

    private List<UserImportDTO> returnValueFromExcel(String fileName, String fileLocation) throws IOException{
        Workbook workbook = ExcelPoiUtil.getWorkBook(fileName, fileLocation);
        Sheet sheet = workbook.getSheetAt(0);
        List<UserImportDTO> excelValues = new ArrayList<UserImportDTO>();
        for (int i=1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            UserImportDTO userImportDTO = readDataFromExcel(row);
            excelValues.add(userImportDTO);
        }
        return excelValues;
    }

    private UserImportDTO readDataFromExcel(Row row) {
        UserImportDTO userImportDTO = new UserImportDTO();
        userImportDTO.setEmail(ExcelPoiUtil.getCellValue(row.getCell(0)));
        userImportDTO.setPassword(ExcelPoiUtil.getCellValue(row.getCell(1)));
        userImportDTO.setName(ExcelPoiUtil.getCellValue(row.getCell(2)));
        userImportDTO.setAge(Integer.parseInt(ExcelPoiUtil.getCellValue(row.getCell(3))));
        userImportDTO.setGender(ExcelPoiUtil.getCellValue(row.getCell(4)));
        userImportDTO.setAddress(ExcelPoiUtil.getCellValue(row.getCell(5)));
        userImportDTO.setPhone(ExcelPoiUtil.getCellValue(row.getCell(6)));
        userImportDTO.setRoleName(ExcelPoiUtil.getCellValue(row.getCell(7)));
        userImportDTO.setIsVip(Integer.parseInt(ExcelPoiUtil.getCellValue(row.getCell(8))));
        userImportDTO.setIsActive(Integer.parseInt(ExcelPoiUtil.getCellValue(row.getCell(9))));
        return userImportDTO;
    }
}
