package Servlets;

import database.tables.EditRoomsTable;
import mainClasses.Room;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet()
public class AddRoom extends HttpServlet {
    private static final long serialVersionUID = 1L; //https://www.codejava.net/coding/java-servlet-and-jsp-hello-world-tutorial-with-eclipse-maven-and-apache-tomcat
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //add a new room
        String roomID = request.getParameter("roomID");
        String capacity = request.getParameter("capacity");
        String equipmenttype = request.getParameter("equipmenttype");
        String adminID = request.getParameter("adminID");

        Room room = new Room();
        room.setRoomID(Integer.parseInt(roomID));
        room.setCapacity(Integer.parseInt(capacity));
        room.setEquipementType(Integer.parseInt(equipmenttype));
        room.setAdminID(Integer.parseInt(adminID));

        try {
            if (EditRoomsTable.databaseToRoom(String.valueOf(room.getRoomID())) != null) {
                EditRoomsTable.addNewRoom(room);
            }else{
                System.out.println("ERROR: THE ROOM INFO YOU PROVIDED ALREADY EXISTS!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/lab_example_web_war_exploded/adminhomepage.html");
    }
}
