package net.kenro.ji.jin.crystal.util;

import com.intellij.notification.NotificationGroup;
import com.intellij.openapi.wm.ToolWindowId;

public class CrystalConstants {
    public static final String SDK_TYPE_ID = "Crystal SDK";
    public static final String CRYSTAL = "Crystal";
    public static final String EXECUTABLE_NAME = "crystal";
    public static final String CHANGELOG_FILE = "CHANGELOG.md";
    public static final String PATH = "PATH";

    public static final NotificationGroup GO_EXECUTION_NOTIFICATION_GROUP = NotificationGroup.toolWindowGroup("Go Execution", ToolWindowId.RUN);

    public static final String TEST_SUFFIX = "_test";
    public static final String TEST_SUFFIX_WITH_EXTENSION = TEST_SUFFIX + ".cr";
}
