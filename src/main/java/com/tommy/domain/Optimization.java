package com.tommy.domain;

import java.util.List;

public class Optimization {

    public String StringTogether(List<String> cityList, String manager, String level, String layer, String features, String switchVal) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        Integer len = cityList.size();
        for(int i = 0; i < len; i ++ ) {
            stringBuilder.append("'").append(cityList.get(i)).append("'").append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.toString().length() - 1);

        stringBuilder.append(")");

        if (manager.equals("教育部")) {
            stringBuilder.append(" and Manage in ('教育部')");
        }
        if (level.equals("985院校")) {
            stringBuilder.append(" and s985 in ('T')");
        }
        if (level.equals("211院校")) {
            stringBuilder.append(" and s211 in ('T')");
        }
        if (layer.equals("本科")) {
            stringBuilder.append(" and Level in ('本科')");
        }
        if (layer.equals("高职（专科）")) {
            stringBuilder.append(" and Level in ('专科')");
        }
        if (features.equals("一流大学建设高校")) {
            stringBuilder.append(" and FirstClassUniversity in ('T')");
        }
        if (features.equals("一流学科建设高校")) {
            stringBuilder.append(" and FirstClassDiscipline in ('T')");
        }
        if (switchVal.equals("T")) {
            stringBuilder.append(" and Postgraduate in ('T')");
        }
        String answer = stringBuilder.toString();

        stringBuilder.setLength(0);

        return answer;
    }
}
