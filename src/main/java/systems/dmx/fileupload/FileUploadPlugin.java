package systems.dmx.fileupload;

import systems.dmx.config.ConfigDefinition;
import systems.dmx.config.ConfigModificationRole;
import systems.dmx.config.ConfigService;
import systems.dmx.config.ConfigTarget;
import systems.dmx.core.model.SimpleValue;
import systems.dmx.core.osgi.PluginActivator;
import systems.dmx.core.service.Inject;
import systems.dmx.core.service.Transactional;
import systems.dmx.files.FilesService;
import systems.dmx.files.StoredFile;
import systems.dmx.files.UploadedFile;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import java.util.logging.Logger;



@Path("/upload")
@Consumes("application/json")
@Produces("application/json")
public class FileUploadPlugin extends PluginActivator implements FileUploadService {

    // ------------------------------------------------------------------------------------------------------- Constants

    private static final int DISK_QUOTA_MB = Integer.getInteger("dmx.filerepo.disk_quota", -1);
    // Note: the default values are required in case no config file is in effect. This applies when DM is started
    // via feature:install from Karaf. The default value must match the value defined in project POM.

    // ---------------------------------------------------------------------------------------------- Instance Variables

    @Inject private FilesService fs;
    @Inject private ConfigService configService;

    private Logger logger = Logger.getLogger(getClass().getName());

    // -------------------------------------------------------------------------------------------------- Public Methods

    // FileUploadService

    @POST
    @Path("/{path}")
    @Consumes("multipart/form-data")
    @Transactional
    @Override
    public StoredFile storeFile(UploadedFile file, @PathParam("path") String repoPath) {
        return fs.storeFile(file, repoPath);
    }

    // Hooks

    @Override
    public void preInstall() {
        configService.registerConfigDefinition(new ConfigDefinition(
            // TODO: can't use AC constants -> cyclic dependency
            // TODO: move registration to AC module?
            ConfigTarget.TYPE_INSTANCES, "dmx.accesscontrol.username",
            mf.newTopicModel("dmx.files.disk_quota", new SimpleValue(DISK_QUOTA_MB)),
            ConfigModificationRole.ADMIN
        ));
    }

    @Override
    public void shutdown() {
        // Note 1: unregistering is crucial e.g. for redeploying the Files plugin. The next register call
        // (at preInstall() time) would fail as the Config service already holds such a registration.
        // Note 2: we must check if the Config service is still available. If the Config plugin is redeployed the
        // Files plugin is stopped/started as well but at shutdown() time the Config service is already gone.
        if (configService != null) {
            configService.unregisterConfigDefinition("dmx.files.disk_quota");
        }
    }
}
