package it.chatgptcopy.chatgptcopy.engine.fusion;

import java.util.List;
import java.util.Map;

public interface DataMerger {

    Map<String, List<String>> merge(List<Map<String,String>> data);

}
