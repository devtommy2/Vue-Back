package com.tommy.domain;

/**
 * coding and debug by tommy
 */

public class OptimizationFuzzy {

    public String getOptimizationFuzzyString(String str) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("'%");
        for(int i = 0; i < str.length(); i ++)  {
            stringBuilder.append(str.charAt(i)).append("%");
        }
        stringBuilder.append("'");

        String answer = stringBuilder.toString();

        stringBuilder.setLength(0);

        return answer;
    }
}
