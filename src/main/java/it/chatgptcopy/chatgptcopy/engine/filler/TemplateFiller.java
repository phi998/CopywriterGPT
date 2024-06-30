package it.chatgptcopy.chatgptcopy.engine.filler;

import java.util.Map;

public interface TemplateFiller {

    String fillTemplate(String template, Map<String,String> data);

}
