package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserService;

import java.io.IOException;

@WebServlet("/verify-otp")
public class VerifyOtpController extends HttpServlet {
    private final UserService userService = new UserService();
    private static final long OTP_EXPIRE_MS = 5 * 60 * 1000; // 5 phút

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("otpCode") == null) {
            // Không có OTP đang chờ xác nhận -> quay lại trang đăng ký
            resp.sendRedirect(req.getContextPath() + "/register");
            return;
        }
        req.setAttribute("email", session.getAttribute("pendingEmail"));
        req.getRequestDispatcher("/verify-otp.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("otpCode") == null) {
            resp.sendRedirect(req.getContextPath() + "/register");
            return;
        }

        String inputOtp = req.getParameter("otp");
        String realOtp = (String) session.getAttribute("otpCode");
        Long otpTime = (Long) session.getAttribute("otpTime");

        // Kiểm tra hết hạn
        if (System.currentTimeMillis() - otpTime > OTP_EXPIRE_MS) {
            req.setAttribute("error", "Mã OTP đã hết hạn. Vui lòng đăng ký lại.");
            req.setAttribute("email", session.getAttribute("pendingEmail"));
            req.getRequestDispatcher("/verify-otp.jsp").forward(req, resp);
            return;
        }

        // Kiểm tra đúng mã
        if (!inputOtp.equals(realOtp)) {
            req.setAttribute("error", "Mã OTP không đúng. Vui lòng thử lại.");
            req.setAttribute("email", session.getAttribute("pendingEmail"));
            req.getRequestDispatcher("/verify-otp.jsp").forward(req, resp);
            return;
        }

        // OTP đúng -> lưu user vào DB
        String email = (String) session.getAttribute("pendingEmail");
        String fullname = (String) session.getAttribute("pendingFullname");
        Integer phone = (Integer) session.getAttribute("pendingPhone");
        String password = (String) session.getAttribute("pendingPassword");

        userService.register(email, fullname, phone, password);

        // Xóa dữ liệu tạm khỏi session
        session.removeAttribute("pendingEmail");
        session.removeAttribute("pendingFullname");
        session.removeAttribute("pendingPhone");
        session.removeAttribute("pendingPassword");
        session.removeAttribute("otpCode");
        session.removeAttribute("otpTime");

        req.setAttribute("success", "Xác nhận thành công! Tài khoản đã được tạo, vui lòng đăng nhập.");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
