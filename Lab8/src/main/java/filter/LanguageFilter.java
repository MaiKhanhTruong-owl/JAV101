package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Locale;

@WebFilter("/*")
public class LanguageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        // Enable UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Get ?lang=vi or ?lang=en
        String langParam = req.getParameter("lang");

        if (langParam != null) {
            // Nếu URL có tham số lang -> đổi ngôn ngữ
            Locale locale = Locale.forLanguageTag(langParam);
            session.setAttribute("lang", locale);
        } else if (session.getAttribute("lang") == null) {
            // Nếu chưa có ngôn ngữ trong session -> mặc định là tiếng Việt
            session.setAttribute("lang", Locale.forLanguageTag("vi"));
        }

        chain.doFilter(request, response);
    }
}

