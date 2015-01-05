package vasouv.javaee7thesis.admin.json;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
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

    //Value of the View's inputTextArea
    private String jsonDB;

    public JsonController() {
        jsonDB = "";
    }
    
    public void buildMyJSON() {
        List<User> dbUsers = userFacade.findAllUsers();

        //Creates an Array to hold similar objects (DB user records)
        JsonArrayBuilder jsonUserArray = Json.createArrayBuilder();
        
        //Constructs JSON objects containing the name and email, adds them to the Array
        for(User u : dbUsers) {
            JsonObjectBuilder job = Json.createObjectBuilder()
                    .add("name", u.getUsername()).add("email", u.getEmail());
            jsonUserArray.add(job);
        }
        
        //Final JSON object (represents the Users table)
        JsonObject jsonFinalOutput = Json.createObjectBuilder().add("users", jsonUserArray).build();
        
        //JSON pretty formatting - Taken from:
        //glassfish4\docs\javaee-tutorial\examples\web\jsonp\jsonpmodel\src\main\java\javaeetutorial
        //\jsonpmodel\ObjectModelBean.java
        Map<String,String> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, "");
        JsonWriterFactory factory = Json.createWriterFactory(config);
        
        //Writes the JSON to a StringWriter stream
        //Taken from JavaEE 7 Tutorial - ch. 19.3.4
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jw = factory.createWriter(stWriter)) {
            jw.writeObject(jsonFinalOutput);
        }
        
        jsonDB = stWriter.toString();
    }
    
    // --- GETTERS & SETTERS

    public String getJsonDB() {
        return jsonDB;
    }

    public void setJsonDB(String jsonDB) {
        this.jsonDB = jsonDB;
    }

}
