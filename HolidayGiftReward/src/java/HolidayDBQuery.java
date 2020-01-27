
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

@WebServlet(urlPatterns =
{
    "/HolidayDBQuery"
})
public class HolidayDBQuery extends HttpServlet
{

    Random rand = new Random();
    int Low = 1;
    int High = 3;
    int r = rand.nextInt(High - Low) + Low;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Amazon Giveaway</title>");
            out.println("<script>"
                    + "function reload()"
                    + "{"
                    + "location.reload();"
                    + "}"
                    + "</script>");
            out.println("</head>");
            out.println("<body><center>");
            Calendar c = Calendar.getInstance();
            try
            {
                //now we are going to connect to the database logindb
                //we need 3 parameters to do the connection
                String database = "jdbc:mysql://localhost/HolidayMonthsDB";
                String user = "root";
                String password = "";

                //need to load the jdbc Driver
                Class.forName("com.mysql.jdbc.Driver");

                //to connect we need to create an object of class Connection
                Connection mycon = DriverManager.getConnection(database, user, password);

                //let's create a String variable that holds the SQL select command
                String SQLselect = "select holiday from HolidayMonthstable where month='"
                        + c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + "'";

                //In order to run an SQL select command in JSP we need to create an
                //object of the class Statement
                Statement mystat = mycon.createStatement();

                //we can now run the select command, it will produce an object of class ResultSet
                ResultSet results = mystat.executeQuery(SQLselect);

                if (results.next())
                {
                    String holiday = results.getString("holiday");
                    if (holiday.contains("Easter"))
                    {

                        out.println("<img src ='amazon_reward.jpg'/></br>");
                        String blueegg = ("<img src ='blue_easter_egg.jpg'/>");
                        String redegg = ("<img src ='red_easter_egg.jpg'/>");
                        String greenegg = ("<img src ='green_easter_egg.jpg'/>");
                        String link = ("<a href = 'congrats.jsp'>");
                        String link2 = ("</a>");
                        String faillink = ("<a href = 'failure.jsp'>");
                        if (r == 1)
                        {
                            out.println(link + blueegg + link2);
                            out.println(faillink + redegg + link2);
                            out.println(faillink + greenegg + link2);
                        } else if (r == 2)
                        {
                            out.println(faillink + blueegg + link2);
                            out.println(link + redegg + link2);
                            out.println(faillink + greenegg + link2);
                        } else if (r == 3)
                        {
                            out.println(faillink + blueegg + link2);
                            out.println(faillink + redegg + link2);
                            out.println(link + greenegg + link2);
                        }
                        out.println("<h3>Choose the right egg to get a 50$ amazon gift card.</h3>");
                    } else
                    {
                        out.println("No giveaway happening at this moment.");
                    }
                } else
                {
                    out.println("No giveaway happening at this moment.");
                }
                out.println("</center></body>");
                out.println("</html>");
            } catch (Exception e)
            {
            }
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
