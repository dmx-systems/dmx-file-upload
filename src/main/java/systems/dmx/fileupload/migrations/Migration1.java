package systems.dmx.fileupload.migrations;

import static systems.dmx.accesscontrol.Constants.*;
import static systems.dmx.config.Constants.*;
import systems.dmx.config.ConfigService;
import static systems.dmx.core.Constants.*;
import static systems.dmx.files.Constants.*;

import systems.dmx.core.Topic;
import systems.dmx.core.service.Inject;
import systems.dmx.core.service.Migration;



/**
 * Adds missing Disk Quota config topics to user accounts.
 * <p>
 * Part of DMX File Upload 1.0.2
 * Runs ALWAYS
 */
public class Migration1 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    @Inject
    private ConfigService cs;

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {
        for (Topic username : dmx.getTopicsByType(USERNAME)) {
            if (username.getRelatedTopic(CONFIGURATION, CONFIGURABLE, DEFAULT, DISK_QUOTA) == null) {
                cs.createConfigTopic(DISK_QUOTA, username);
            }
        }
    }
}
