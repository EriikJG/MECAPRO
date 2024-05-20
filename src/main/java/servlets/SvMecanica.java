package servlets;

import logica.Cliente;
import logica.Controladora;
import logica.Mecanica;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SvMecanica", urlPatterns = {"/SvMecanica"})
public class SvMecanica extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        crearMecanica();
        Mecanica mecanica = control.encontrarMecanica(1);
        HttpSession misession = request.getSession(true);
        misession.setAttribute("mecanica", mecanica);
        response.sendRedirect("inicio.jsp");
    }

    private void crearMecanica() {
        String nombre = "MecaJobs";
        String direccion = "La Ecuatoriana";
        String correo = "mecapro1212@gmail.com";
        List<Cliente> clientes = new ArrayList<>();
        control.crearMecanica(nombre, direccion, correo,clientes);
    }
}