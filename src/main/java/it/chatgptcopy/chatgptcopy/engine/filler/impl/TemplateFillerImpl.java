package it.chatgptcopy.chatgptcopy.engine.filler.impl;

import it.chatgptcopy.chatgptcopy.engine.filler.TemplateFiller;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class TemplateFillerImpl implements TemplateFiller {

    @Override
    public String fillTemplate(String template, Map<String, String> data) {
        StringBuilder resultBuilder = new StringBuilder();

        List<String> attrsToConsider = this.getAttributesForDocsList(template);

        for(String attr: attrsToConsider) {
            if(data.containsKey(attr)) {
                resultBuilder.append(attr).append(": ").append(data.get(attr)).append("\n");
            }
        }

        log.info("template={}, result={}", template, resultBuilder.toString());
        return template.replaceAll("%%.*", resultBuilder.toString());
    }

    private List<String> getAttributesForDocsList(String template) {
        Pattern pattern = Pattern.compile("\\[([^\\]]*)\\]");
        Matcher matcher = pattern.matcher(template);

        List<String> resultList = new ArrayList<>();

        while (matcher.find()) {
            String[] resultArray = matcher.group(1).split(",");

            for (String str : resultArray) {
                resultList.add(str.trim());
            }
        }

        return resultList;
    }

}
