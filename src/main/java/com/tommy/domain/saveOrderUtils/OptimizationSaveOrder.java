package com.tommy.domain.saveOrderUtils;

import java.util.List;

/**
 * code and debug by tommy
 */

public class OptimizationSaveOrder {
    // 保存自选专业
    public String saveSelectedMajorOrder(List<Integer> old_major_id, List<Integer> majorList, Integer student_id) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < majorList.size(); i ++ ) {
            stringBuilder.append(" WHEN " + old_major_id.get(i) + " THEN " + majorList.get(i));
        }
        stringBuilder.append("END WHERE student_in in " + student_id);

        String answer = stringBuilder.toString();

        stringBuilder.setLength(0);

        return answer;
    }

    // 保存自动生成的专业顺序
    public String saveAutoMajorOrder(List<Integer> old_major_id, List<Integer> majorList, Integer student_id) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < majorList.size(); i ++ ) {
            stringBuilder.append(" WHEN " + old_major_id.get(i) + " THEN " + majorList.get(i));
        }
        stringBuilder.append("END WHERE student_in in " + student_id);

        String answer = stringBuilder.toString();

        stringBuilder.setLength(0);

        return answer;
    }

}
