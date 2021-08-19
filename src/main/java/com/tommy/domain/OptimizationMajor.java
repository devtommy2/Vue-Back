package com.tommy.domain;

import java.util.List;

/**
 * coding and debug by tommy
 */

public class OptimizationMajor {

    public String getOptimizationMajorSqlString(List<String> stringList, String Level) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("(");

        for(int i = 0; i < stringList.size(); i ++ ) {
            stringBuilder.append("'").append(stringList.get(i)).append("'").append(",");
        }

        stringBuilder.deleteCharAt(stringBuilder.toString().length() - 1);

        stringBuilder.append(")");


        if (Level.equals("本科")) {
            stringBuilder.append(" and level1_name in ('本科')");
        } else if (Level.equals("专科")) {
            stringBuilder.append(" and level1_name in ('专科')");
        } else if (Level.equals("全部")) {
            stringBuilder.append(" and level1_name in ('本科', '专科')");
        }

        String answer = stringBuilder.toString();

        stringBuilder.setLength(0);

        return answer;


    }
}
