package controller;

import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserService;
import util.EmailUtil;
import util.OtpUtil;

import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private final UserService userService = new UserService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phoneStr = req.getParameter("phone");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        if (!password.equals(confirmPassword)) {
            req.setAttribute("error", "Mật khẩu xác nhận không khớp!");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        if (userService.emailExists(email)) {
            req.setAttribute("error", "Email này đã được đăng ký!");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        Integer phone = null;
        try {
            if (phoneStr != null && !phoneStr.isEmpty()) {
                phone = Integer.parseInt(phoneStr);
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Số điện thoại không hợp lệ!");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        // Sinh mã OTP 6 số
        String otp = OtpUtil.generateOtp();

        // Lưu tạm thông tin đăng ký + OTP vào session (chưa lưu DB)
        HttpSession session = req.getSession();
        session.setAttribute("pendingEmail", email);
        session.setAttribute("pendingFullname", fullname);
        session.setAttribute("pendingPhone", phone);
        session.setAttribute("pendingPassword", password);
        session.setAttribute("otpCode", otp);
        session.setAttribute("otpTime", System.currentTimeMillis());

        // Gửi email OTP
        try {
            EmailUtil.sendOtpEmail(email, otp);
        } catch (MessagingException e) {
            req.setAttribute("error", "Không gửi được email xác nhận. Vui lòng thử lại.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/verify-otp");
    }
}
