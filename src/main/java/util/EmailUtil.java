package util;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailUtil {
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String FROM_EMAIL = "your_email@gmail.com";
    private static final String APP_PASSWORD = "abcdefghijklmnop";
    public static void sendOtpEmail(String toEmail, String otp) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, APP_PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Mã xác nhận đăng ký tài khoản - QLBH Book Store");
        message.setText(
                "Xin chào,\n\n" +
                        "Mã OTP xác nhận đăng ký tài khoản của bạn là: " + otp + "\n" +
                        "Mã có hiệu lực trong 5 phút.\n\n" +
                        "Nếu bạn không thực hiện yêu cầu này, vui lòng bỏ qua email này."
        );

        Transport.send(message);
    }
}
