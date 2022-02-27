package autocompleter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/location")
public class AutoComplete extends HttpServlet {
    public AutoComplete() {}

    public void init() throws ServletException {
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        List<String> locations = LocationReader.getLocations(this.getServletContext());
        Iterator iterator = locations.iterator();

        while(iterator.hasNext()) {
            String location = (String)iterator.next();
            printWriter.println(location);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doGet(request, response);
    }
}
