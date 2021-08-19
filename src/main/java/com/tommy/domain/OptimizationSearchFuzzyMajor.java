package com.tommy.domain;

/**
 * coding and debug by tommy
 */

public class OptimizationSearchFuzzyMajor {

    public String getOptimizationSearchFuzzyMajor(String condition) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("'%");
        for(int i = 0; i < condition.length(); i ++ ) {
            stringBuilder.append(condition.charAt(i)).append("%");
        }
        stringBuilder.append("'");

        String answer = stringBuilder.toString();

        stringBuilder.setLength(0);

        return answer;
    }
}
