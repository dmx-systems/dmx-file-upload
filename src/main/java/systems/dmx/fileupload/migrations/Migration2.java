package systems.dmx.fileupload.migrations;

import static systems.dmx.accesscontrol.Constants.*;
import static systems.dmx.config.Constants.*;
import static systems.dmx.core.Constants.*;
import static systems.dmx.files.Constants.*;

import systems.dmx.core.RelatedTopic;
import systems.dmx.core.Topic;
import systems.dmx.core.service.Migration;

import java.util.List;



/**
 * Removes duplicate "Configuration" assocs.
 * These occur if a DMX installation was upgraded to 5.2, and File Upload 1.0.1 was once installed.
 * Note: migration 1 of File Upload 1.0.1 was faulty and has been fixed in 1.0.2.
 * <p>
 * Part of DMX File Upload 1.0.4
 * Runs ALWAYS
 */
public class Migration2 extends Migration {

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public void run() {
        for (Topic username : dmx.getTopicsByType(USERNAME)) {
            List<RelatedTopic> topics = username.getRelatedTopics(CONFIGURATION, CONFIGURABLE, DEFAULT, DISK_QUOTA);
            if (topics.size() > 1) {
                for (int i = 1; i < topics.size(); i++) {
                    topics.get(i).getRelatingAssoc().delete();
                }
            }
        }
    }
}
