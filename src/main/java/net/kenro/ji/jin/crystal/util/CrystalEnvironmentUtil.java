package net.kenro.ji.jin.crystal.util;

import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.PathUtil;
import org.jetbrains.annotations.NotNull;

public class CrystalEnvironmentUtil {
    private CrystalEnvironmentUtil() {
    }

    @NotNull
    public static String getBinaryFileNameForPath(@NotNull String path) {
        String resultBinaryName = FileUtil.getNameWithoutExtension(PathUtil.getFileName(path));
        return SystemInfo.isWindows ? resultBinaryName + ".exe" : resultBinaryName;
    }

}
