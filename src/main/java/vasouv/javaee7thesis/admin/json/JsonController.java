/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vasouv.javaee7thesis.admin.json;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
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

        JsonArrayBuilder jab = Json.createArrayBuilder();
        
        for(User u : dbUsers) {
            JsonObjectBuilder job = Json.createObjectBuilder().add("name", u.getUsername());
            jab.add(job);
        }
        
        JsonObject jo = Json.createObjectBuilder().add("users", jab).build();
        
        Map<String,String> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, "");
        JsonWriterFactory factory = Json.createWriterFactory(config);
        
        StringWriter stWriter = new StringWriter();
//        try (JsonWriter jw = Json.createWriter(stWriter)) {
        try (JsonWriter jw = factory.createWriter(stWriter)) {
            jw.writeObject(jo);
        }
        
        jsonDB = stWriter.toString();
    }

    public String getJsonDB() {
        return jsonDB;
    }

    public void setJsonDB(String jsonDB) {
        this.jsonDB = jsonDB;
    }

}
