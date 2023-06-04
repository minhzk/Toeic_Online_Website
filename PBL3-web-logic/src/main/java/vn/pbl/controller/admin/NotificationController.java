package vn.pbl.controller.admin;

import vn.myclass.core.utils.NotificationBeanUtil;
import vn.pbl.command.ListenGuidelineCommand;
import vn.pbl.command.NotificationCommand;
import vn.pbl.core.dto.NotificationDTO;
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
import java.util.ArrayList;
import java.util.List;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@WebServlet("/admin-notification.html")
public class NotificationController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NotificationCommand command = FormUtil.populate(NotificationCommand.class, request);
        if(command.getCrudaction() != null && command.getCrudaction().equals(WebConstant.REDIRECT_DELETE)) {
            List<Integer> ids = new ArrayList<Integer>();
            for (String item: command.getCheckList()) {
                ids.add(Integer.parseInt(item));
            }
            Integer result = SingletonServiceUtil.getNotificationServiceInstance().delete(ids);
            if (result != ids.size()) {
                command.setCrudaction(WebConstant.REDIRECT_ERROR);
            }
            List<NotificationDTO> notificationDTOList = SingletonServiceUtil.getNotificationServiceInstance().findAll();
            command.setListResult(notificationDTOList);
            command.setTotalItems(notificationDTOList.size());
            request.setAttribute(WebConstant.LIST_ITEMS,command);
            RequestDispatcher rd = request.getRequestDispatcher("views/admin/notification/list.jsp");
            rd.forward(request, response);
        }else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/notification/detail.jsp");
            rd.forward(request, response);
        }else {
        List<NotificationDTO> notificationDTOList = SingletonServiceUtil.getNotificationServiceInstance().findAll();
        command.setListResult(notificationDTOList);
        command.setTotalItems(notificationDTOList.size());
        request.setAttribute(WebConstant.LIST_ITEMS,command);
        RequestDispatcher rd = request.getRequestDispatcher("views/admin/notification/list.jsp");
        rd.forward(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setTitle(title);
        notificationDTO.setContent(content);
        SingletonServiceUtil.getNotificationServiceInstance().add(notificationDTO);
        sendEmail(title,content);
    }
    private void sendEmail(String title, String content){
        // Cấu hình thông tin máy chủ email
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "tuhoctienganh571@gmail.com";
        String password = "rhgkmmmuqbqlwqco";
        String[] toEmails = {"trantant88@gmail.com", "nbinhminh158@gmail.com", "huuhiepskyhero@gmail.com"};
        // Tạo properties để cấu hình kết nối
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Tạo phiên làm việc
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Tạo đối tượng MimeMessage
            MimeMessage message = new MimeMessage(session);

            // Thiết lập thông tin người gửi
            message.setFrom(new InternetAddress(username));

            // Thiết lập thông tin người nhận
//            String recipient = "nbinhminh158@gmail.com";
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

            InternetAddress[] recipients = new InternetAddress[toEmails.length];
            for (int i = 0; i < toEmails.length; i++) {
                recipients[i] = new InternetAddress(toEmails[i]);
            }
            message.setRecipients(Message.RecipientType.TO, recipients);

            // Chuỗi HTML lấy từ CSDL
            String htmlTitle = title;
            String htmlContent = content;
            // Chuyển đổi chuỗi HTML thành định dạng văn bản thuần túy
            Document docContent = Jsoup.parse(htmlContent);
            Document docTitle = Jsoup.parse(htmlTitle);
            Elements subjectEle = docTitle.select("body");
            Elements textEle = docContent.select("body");
            String subject = subjectEle.text();
            String text = textEle.text();
            // Thiết lập tiêu đề email
            //message.setSubject(title);
            message.setSubject(subject);
            // Thiết lập nội dung email
            //String content = "This is a test email from Java.";
            //message.setText(content);
            message.setText(text);
            // Gửi email
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }
}
