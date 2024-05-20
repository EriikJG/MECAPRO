package servlets;

import logica.Cliente;
import logica.Controladora;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import java.io.IOException;

@WebServlet(name = "SvEncontrarUsuario", urlPatterns = {"/SvEncontrarUsuario"})
public class SvEncontrarUsuario  extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cliente usuarioEncontrado = control.encontrarUsuario(Integer.parseInt(request.getParameter("cedula")));

        buscarUsuario(request, response, usuarioEncontrado);

    }

    private static void buscarUsuario(HttpServletRequest request, HttpServletResponse response, Cliente usuarioEncontrado) throws IOException {
        if (null != usuarioEncontrado) {
            HttpSession misession = request.getSession(true);
            misession.setAttribute("propietario", usuarioEncontrado);
            response.sendRedirect("automovil.jsp");
        }
        else {
            response.sendRedirect("usuario.jsp");
        }
    }
    }