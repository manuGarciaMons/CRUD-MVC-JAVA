
package Controlador;

import Modelo.Empleado;
import ModeloDAO.EmpleadoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.json.JsonObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Controller")
public class Controlador extends HttpServlet {

    String listar="vistas/listar.jsp";
    String add="vistas/add.jsp";
    String edit="vistas/edit.jsp";
    Empleado p=new Empleado();
    EmpleadoDAO dao=new EmpleadoDAO();
    int id;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controlador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controlador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("listar")){
            acceso=listar;            
        }else if(action.equalsIgnoreCase("add")){
            acceso=add;
        }
        else if(action.equalsIgnoreCase("Agregar")){
            String dni = request.getParameter("txtDni");
            String nom = request.getParameter("txtNom");
            String ape = request.getParameter("txtApe");
            String car = request.getParameter("txtCar");
            String sex = request.getParameter("selSex");
            p.setDni(dni);
            p.setNom(nom);
            p.setApe(ape);
            p.setCar(car);
            p.setSex(sex);
            dao.add(p);
            acceso=listar;
        }
        else if(action.equalsIgnoreCase("editar")){
            request.setAttribute("idper",request.getParameter("id"));
            acceso=edit;
        }
        else if(action.equalsIgnoreCase("Actualizar")){
            id=Integer.parseInt(request.getParameter("txtid"));
            String dni=request.getParameter("txtDni");
            String nom=request.getParameter("txtNom");
            String ape = request.getParameter("txtApe");
            String car = request.getParameter("txtCar");
            String sex = request.getParameter("selSex");
            p.setId(id);
            p.setDni(dni);
            p.setNom(nom);
            p.setApe(ape);
            p.setCar(car);
            p.setSex(sex);
            dao.edit(p);
            acceso=listar;
        }
        else if(action.equalsIgnoreCase("eliminar")){
            id=Integer.parseInt(request.getParameter("id"));
            p.setId(id);
            dao.eliminar(id);
            acceso=listar;
        }
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filtro = request.getParameter("filtro");
        int indFiltro = Integer.parseInt(request.getParameter("indFiltro"));
        List<Empleado> empleadosFiltrados = this.filtrarEmpleados(indFiltro, filtro);
        String jsonArray = "";
        jsonArray += "[";
        for(Empleado empleado: empleadosFiltrados){
            jsonArray += "{";
            jsonArray += "\"id\":\"" + empleado.getId() + "\",";
            jsonArray += "\"dni\":\"" + empleado.getDni()+ "\",";
            jsonArray += "\"nombres\":\"" + empleado.getNom()+ "\",";
            jsonArray += "\"apellidos\":\"" + empleado.getApe()+ "\",";
            jsonArray += "\"cargo\":\"" + empleado.getCar()+ "\",";
            jsonArray += "\"sexo\":\"" + empleado.getSex()+"\"";
            jsonArray += "},";
        }
        jsonArray = jsonArray.substring(0, jsonArray.length() - 1);
        jsonArray += "]";
        
        response.getWriter().write(jsonArray);
    }
    
    
    public List<Empleado> filtrarEmpleados(int indFiltro, String valorFiltro){
        EmpleadoDAO dao = new EmpleadoDAO();
        List<Empleado> empleadosFiltrados = dao.buscar(indFiltro, valorFiltro);
        return empleadosFiltrados;
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
