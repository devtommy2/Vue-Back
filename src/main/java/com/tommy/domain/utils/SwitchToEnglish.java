package com.tommy.domain.utils;

import java.util.Collections;
import java.util.List;

/**
 * code and debug by tommy
 */

public class SwitchToEnglish {

    public List<String> switchToEnglish(List<String> subjectList) {
        Collections.replaceAll(subjectList, "物理", "Physics");
        Collections.replaceAll(subjectList, "化学", "Chemical");
        Collections.replaceAll(subjectList, "生物", "Biology");
        Collections.replaceAll(subjectList, "政治", "Politics");
        Collections.replaceAll(subjectList, "历史", "History");
        Collections.replaceAll(subjectList, "地理", "Geography");
        return subjectList;
    }
}
