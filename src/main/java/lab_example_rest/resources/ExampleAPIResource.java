//package lab_example_rest.resources;
//
//import database.init.InitDatabase;
//import com.google.gson.Gson;
//import java.sql.SQLException;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import java.util.ArrayList;
//
///**
// * REST Web Service
// *
// * @author man
// */
//@Path("api")
//public class ExampleAPIResource {
//
//    @POST
//    @Path("/database")
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response createDB() {
//        InitDatabase init = new InitDatabase();
//        try {
//            init.initDatabase();
//            Response.Status status = Response.Status.CREATED;
//            return Response.status(status).type("application/json").entity("{\"msg\":\"Database succesfully created.\"}").build();
//        } catch (SQLException ex) {
//            Response.Status status = Response.Status.CONFLICT;
//            return Response.status(status).type("application/json").entity("{\"msg\":\"Database already exists.\"}").build();
//        } catch (ClassNotFoundException ex) {
//            Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
//            return Response.status(status).type("application/json").entity("{\"msg\":\"Fail.\"}").build();
//        }
//    }
//
//    @DELETE
//    @Path("/database")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response dropDB() {
//        InitDatabase init = new InitDatabase();
//        try {
//            init.dropDatabase();
//            Response.Status status = Response.Status.OK;
//            return Response.status(status).type("application/json").entity("{\"msg\":\"Database succesfully dropped.\"}").build();
//        } catch (SQLException ex) {
//            Response.Status status = Response.Status.CONFLICT;
//            return Response.status(status).type("application/json").entity("{\"msg\":\"Database doesn't exist.\"}").build();
//        } catch (ClassNotFoundException ex) {
//            Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
//            return Response.status(status).type("application/json").entity("{\"msg\":\"Fail.\"}").build();
//        }
//    }
//
//    @POST
//    @Path("/user")
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response addUser(String user) {
//        Gson gson = new Gson();
//        SimpleUser u = gson.fromJson(user, SimpleUser.class);
//
//        EditSimpleUserTable esut = new EditSimpleUserTable();
//        try {
//            if (u.getUsername().isEmpty() || u.getPassword().isEmpty() || u.getEmail().isEmpty()) {
//                Response.Status status = Response.Status.BAD_REQUEST;
//                return Response.status(status).type("application/json").entity("{\"msg\":\"You forgot something :)\"}").build();
//            } else {
//                try {
//                    if (esut.usernameInDataBase(u.getUsername())) {
//                        Response.Status status = Response.Status.CONFLICT;
//                        return Response.status(status).type("application/json").entity("{\"msg\":\"User already exists in DataBase.\"}").build();
//                    } else {
//                        esut.addNewSimpleUser(u.getUsername(), u.getEmail(), u.getPassword());
//                        Response.Status status = Response.Status.CREATED;
//                        return Response.status(status).type("application/json").entity("{\"msg\":\"User succesfully added to DataBase.\"}").build();
//                    }
//                } catch (SQLException ex) {
//                    Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
//                    return Response.status(status).type("application/json").entity("{\"msg\":\"Fail.\"}").build();
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
//            return Response.status(status).type("application/json").entity("{\"msg\":\"Fail.\"}").build();
//        }
//    }
//
//    @POST
//    @Path("/doctor")
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response addDoc(String doc) {
//        Gson gson = new Gson();
//        Doctor d = gson.fromJson(doc, Doctor.class);
//
//        EditDoctorTable edt = new EditDoctorTable();
//        try {
//            if (d.getUsername().isEmpty() || d.getPassword().isEmpty() || d.getEmail().isEmpty()) {
//                Response.Status status = Response.Status.BAD_REQUEST;
//                return Response.status(status).type("application/json").entity("{\"msg\":\"You forgot something :)\"}").build();
//            } else {
//                try {
//                    if (edt.usernameInDataBase(d.getUsername())) {
//                        Response.Status status = Response.Status.CONFLICT;
//                        return Response.status(status).type("application/json").entity("{\"msg\":\"Doctor already exists in DataBase.\"}").build();
//                    } else {
//                        edt.addNewDoctor(d.getUsername(), d.getEmail(), d.getPassword());
//                        Response.Status status = Response.Status.CREATED;
//                        return Response.status(status).type("application/json").entity("{\"msg\":\"Doctor succesfully added to DataBase.\"}").build();
//                    }
//                } catch (SQLException ex) {
//                    Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
//                    return Response.status(status).type("application/json").entity("{\"msg\":\"Fail.\"}").build();
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
//            return Response.status(status).type("application/json").entity("{\"msg\":\"Fail.\"}").build();
//        }
//    }
//
//    @POST
//    @Path("/login")
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response login(String user) {
//        Gson gson = new Gson();
//        User u = gson.fromJson(user, User.class);
//
//        EditDoctorTable edt = new EditDoctorTable();
//        EditSimpleUserTable esut = new EditSimpleUserTable();
//        try {
//            Doctor doc = edt.databaseToDoctor(u.getUsername(), u.getPassword());
//            SimpleUser su = esut.databaseToSimpleUser(u.getUsername(), u.getPassword());
//
//            if (doc != null) {
//                Response.Status status = Response.Status.OK;
//                return Response.status(status).type("application/json").entity("{\"type\":\"doctor\",\"msg\":\"Welcome back Dr. " + doc.getUsername() + "\"}").build();
//            } else if (su != null) {
//                Response.Status status = Response.Status.OK;
//                return Response.status(status).type("application/json").entity("{\"type\":\"user\",\"msg\":\"Welcome back " + su.getUsername() + "\"}").build();
//            } else {
//                Response.Status status = Response.Status.UNAUTHORIZED;
//                return Response.status(status).type("application/json").entity("{\"type\":\"\",\"msg\":\"No user/doctor with those credentials in DataBase.\"}").build();
//            }
//        } catch (SQLException | ClassNotFoundException ex) {
//            Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
//            return Response.status(status).type("application/json").entity("{\"type\":\"\",\"msg\":\"Fail.\"}").build();
//        }
//    }
//
//    @GET
//    @Path("/users")
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response getUsers() {
//        try {
//            EditSimpleUserTable esut = new EditSimpleUserTable();
//            ArrayList<SimpleUser> su = esut.databaseToSimpleUsers();
//
//            Response.Status status = Response.Status.OK;
//            String json = new Gson().toJson(su);
//            return Response.status(status).type("application/json").entity(json).build();
//        } catch (SQLException | ClassNotFoundException ex) {
//            Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
//            return Response.status(status).type("application/json").entity("{\"msg\":\"Fail.\"}").build();
//        }
//    }
//}
