package ru.otus.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "test")
public class AppConfig implements TestFileNameProvider, TestConfig, LocaleConfig {

    private int rightAnswersCountToPass;

    private Locale locale;

    private Map<String, String> fileNameByLocaleTag;

    public void setLocale(String locale) {
        this.locale = Locale.forLanguageTag(locale);
    }

    @Override
    public int getRightAnswersCountToPass() {
        return rightAnswersCountToPass;
    }

    @Override
    public String getTestFileName() {
        return fileNameByLocaleTag.getOrDefault(locale.toLanguageTag(),fileNameByLocaleTag.get("default"));
    }
}
