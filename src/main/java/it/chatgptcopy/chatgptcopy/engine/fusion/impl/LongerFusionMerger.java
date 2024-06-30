package it.chatgptcopy.chatgptcopy.engine.fusion.impl;

import it.chatgptcopy.chatgptcopy.engine.fusion.DataMerger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LongerFusionMerger implements DataMerger {

    @Override
    public Map<String, List<String>> merge(List<Map<String, String>> data) {
        Map<String, List<String>> result = new HashMap<>();

        for(Map<String, String> row: data) {
            for(Map.Entry<String,String> entry: row.entrySet()) {
                String labelName = entry.getKey();
                String dataContent = entry.getValue();

                if(result.containsKey(labelName)) {
                    String actualResultValue = result.get(labelName).get(0);
                    if(dataContent.length() > actualResultValue.length()) {
                        result.get(labelName).clear();
                        result.get(labelName).add(dataContent);
                    }
                } else {
                    result.put(labelName, new ArrayList<>());
                    result.get(labelName).add(dataContent);
                }
            }
        }

        return result;
    }
}
