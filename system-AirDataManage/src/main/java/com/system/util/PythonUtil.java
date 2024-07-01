package com.system.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Slf4j
public class PythonUtil {
    private static String generatePath() {
        String dir = System.getProperty("user.dir");
        dir += "\\system-AirDataManage\\src\\main\\python\\main.py";
        return dir;
    }

    public static void execute() {
        final String line = "python " + generatePath();
        System.out.println(line);
        final CommandLine cmdLine = CommandLine.parse(line);

        try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            final PumpStreamHandler streamHandler = new PumpStreamHandler(baos);
            final DefaultExecutor executor = new DefaultExecutor();
            executor.setStreamHandler(streamHandler);
            final int exitCode = executor.execute(cmdLine);
            log.info("{} to update the forecast air data.", exitCode == 0 ? "success" : "fail");
        }
        catch (final IOException e) {
            log.error("调用Python脚本出错", e);
        }
    }
}
