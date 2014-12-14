package menon.cs6890.FinalProject.Controller;

import java.io.StringWriter;

import menon.cs6890.FinalProject.UI.UserDialog;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * This class will merge data with a template in order to create a response for the user
 * @author gopalmenon
 *
 */
public class ResponseGenerator {
	
	private Template messageTemplate;
	
	public ResponseGenerator(String templateName) {
		
        //get and initialize an engine
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();
        
		this.messageTemplate = velocityEngine.getTemplate(templateName);
		
	}
	
	public String getResponseForUser(VelocityContext context) {
		
	    //render the template into a StringWriter
        StringWriter writer = new StringWriter();
        this.messageTemplate.merge( context, writer );
        
        //Return the string to the shown to the user
        return UserDialog.RESERVATION_AGENT_PROMPT +  writer.toString();  
		
	}

}
