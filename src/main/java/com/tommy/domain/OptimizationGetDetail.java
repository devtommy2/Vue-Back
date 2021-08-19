package com.tommy.domain;

import java.util.List;

/**
 * coding and debug by tommy
 */

public class OptimizationGetDetail {

    public String getDetailMajor(List<String> majorList) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        for(int i = 0; i < majorList.size(); i ++ ) {
            stringBuilder.append("'").append(majorList.get(i)).append("'").append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.toString().length() - 1);
        stringBuilder.append(")");

        String answer = stringBuilder.toString();

        stringBuilder.setLength(0);

        return answer;
    }
}