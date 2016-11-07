
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;


public class TestServlet extends HttpServlet {
 @Override
 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String linkBack = request.getHeader("Referer"); 
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String content = "NO CLIENT DATA";
        String backForm = "<A HREF=\""+linkBack+"\">Click to go back to form</A>";
        if(name != null && age != null){
            content = "The user "+name+" has enter , it has age "+age+"<BR>";
        }  
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n";
        out.println(docType +
        "<html>\n" +
        " <head><title>Test Servlet</title></head>\n" +
        "<body>\n" +
        " <h1>Hello!</h1>\n" +
        content+
        backForm+
        "</body></html>");
 }
}
