package systems.dmx.fileupload;

import systems.dmx.core.osgi.PluginActivator;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import java.util.logging.Logger;



@Path("/upload")
@Consumes("application/json")
@Produces("application/json")
public class FileUploadPlugin extends PluginActivator {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    // -------------------------------------------------------------------------------------------------- Public Methods

    // ------------------------------------------------------------------------------------------------- Private Methods
}
