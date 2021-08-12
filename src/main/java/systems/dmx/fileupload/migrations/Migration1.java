package systems.dmx.fileupload.migrations;

import static systems.dmx.accesscontrol.Constants.*;
import static systems.dmx.files.Constants.*;
import systems.dmx.config.ConfigService;

import systems.dmx.core.Topic;
import systems.dmx.core.service.Inject;
import systems.dmx.core.service.Migration;



/**
 * Adds Disk Quota config topics to user accounts on plugin installation time.
 * <p>
 * Part of DMX File Upload 1.0.1
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
            cs.createConfigTopic(DISK_QUOTA, username);
        }
    }
}
