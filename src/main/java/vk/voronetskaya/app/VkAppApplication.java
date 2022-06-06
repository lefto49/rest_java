package vk.voronetskaya.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VkAppApplication {
    public static void main(String[] args) {
        if (args.length == 5) {
            LoggerContext ctx = (LoggerContext) (LogManager.getContext(false));
            Configuration config = ctx.getConfiguration();
            Layout<String> layout = PatternLayout.createDefaultLayout(config);
            Appender appender = FileAppender.createAppender(String.format("target/%s", args[4]), "false", "false", "File", "true",
                    "false", "false", "4000", layout, null, "false", null, config);
            appender.start();
            config.addAppender(appender);
        }
        SpringApplication.run(VkAppApplication.class, args);
    }
}
