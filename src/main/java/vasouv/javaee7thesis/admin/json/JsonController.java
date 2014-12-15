/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vasouv.javaee7thesis.admin.json;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import vasouv.javaee7thesis.courses.Course;
import vasouv.javaee7thesis.courses.sessionbeans.CourseFacade;
import vasouv.javaee7thesis.register.User;
import vasouv.javaee7thesis.register.sessionbeans.UserFacade;

/**
 *
 * @author vasouv
 */
@Named(value = "jsonController")
@RequestScoped
public class JsonController {

    @EJB
    private UserFacade userFacade;

    @EJB
    private CourseFacade courseFacade;

    private String jsonDB;

    public JsonController() {
        jsonDB = "";
    }

    public void buildMyJSON() {
        List<User> dbUsers = userFacade.findAllUsers();

        JsonObjectBuilder builder = Json.createObjectBuilder();
        StringBuilder json = new StringBuilder();
        JsonObjectBuilder object = null;

        JsonArrayBuilder jab = Json.createArrayBuilder();
        
        for (User u : dbUsers) {
            jab.add(builder.add("name", u.getName()).add("email", u.getEmail()).build());
        }
        
        jab.build();
        
        JsonObject finalBuild = Json.createObjectBuilder().add("Users", jab).build();
        
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter)) {
            jsonWriter.writeObject(finalBuild);
        }
        jsonDB = stWriter.toString();

//        try (StringWriter sw = new StringWriter();) {
//            for (User u : dbUsers) {
//                builder.add("name", u.getName()).add("email", u.getEmail()).build();
//                System.out.println(u.getName());
//                builder.add("User", Json.createObjectBuilder()
//                        .add("name", u.getName())
//                        .add("username", u.getUsername()));
//            }
//
//            JsonObject result = builder.build();
//
//            try (JsonWriter writer = Json.createWriter(sw)) {
//                writer.writeObject(result);
//            }
//            json.append(sw.toString());
//            jsonDB = json.toString();
//
//        } catch (IOException ex) {
//            System.out.println(ex);
//        }
    }

    public String getJsonDB() {
        return jsonDB;
    }

    public void setJsonDB(String jsonDB) {
        this.jsonDB = jsonDB;
    }

}
