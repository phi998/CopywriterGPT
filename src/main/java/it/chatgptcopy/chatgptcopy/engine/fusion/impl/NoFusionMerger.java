package it.chatgptcopy.chatgptcopy.engine.fusion.impl;

import it.chatgptcopy.chatgptcopy.engine.fusion.DataMerger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//@Component
public class NoFusionMerger implements DataMerger {

    @Override
    public Map<String, List<String>> merge(List<Map<String, String>> data) {
        return null;
    }
    
}
