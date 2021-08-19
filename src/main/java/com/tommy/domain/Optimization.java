package com.tommy.domain;

import java.awt.datatransfer.StringSelection;
import java.util.List;

/**
 * coding and debug by tommy
 */

public class Optimization {

    public String StringTogether(List<String> cityList, String manager, String level, String layer, String features) {
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
        } else if (manager.equals("军校")) {
            stringBuilder.append(" and Manage like '%军%'");
        } else if (manager.equals("其他部委")) {
            stringBuilder.append(" and Manage like '%委%' and Manage not like '%军%'");
        } else if (manager.equals("地方")) {
            stringBuilder.append(" and Manage in ('北京市', '天津市', '河北省', '山西省', '辽宁省'," +
                    " '吉林省', '黑龙江省', '上海市', '江苏省', '浙江省', '安徽省', '福建省', '江西省', " +
                    "'山东省', '河南省', '湖北省', '湖南省', '广东省', '海南省', '重庆市', '四川省'," +
                    " '贵州省', '云南省', '西藏自治区', '陕西省', '甘肃省', '青海省', '宁夏回族自治区', " +
                    "'新疆维吾尔自治区')");
        }
        if (level.equals("985院校")) {
            stringBuilder.append(" and f985 in ('T')");
        } else if (level.equals("211院校")) {
            stringBuilder.append(" and f211 in ('T')");
        }

        if (layer.equals("本科")) {
            stringBuilder.append(" and Layer in ('本科')");
        } else if (layer.equals("高职（专科）")) {
            stringBuilder.append(" and Layer in ('专科')");
        } else if (layer.equals("独立学院")) {
            stringBuilder.append(" and Layer in ('独立学院')");
        } else if (layer.equals("中外合作办学")) {
            stringBuilder.append(" and Layer in ('中外合作办学')");
        }

        if (features.equals("T")) {
            stringBuilder.append(" and FirstClass in ('双一流')");
        }
        String answer = stringBuilder.toString();
        stringBuilder.setLength(0);
        return answer;
    }


    public String getAllMajorNameJustLimitByLevel(String Level) {

        StringBuilder stringBuilder = new StringBuilder();
        if (Level.equals("全部")) {
            stringBuilder.append("('本科', '专科')");
        } else if (Level.equals("专科")) {
            stringBuilder.append("('专科')");
        } else if (Level.equals("本科")) {
            stringBuilder.append("('本科')");
        }
        String answer = stringBuilder.toString();
        stringBuilder.setLength(0);
        return answer;
    }

    public String getAllUniversityInfoByUniversityId(List<Integer> universityList) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        for(int i = 0; i < universityList.size(); i ++ ) {
            stringBuilder.append(universityList.get(i)).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.toString().length() - 1);
        stringBuilder.append(")");
        String answer = stringBuilder.toString();
        stringBuilder.setLength(0);
        return answer;
    }


    public String getMajorByUniversitySelected(List<Integer> universityList, String batch, Integer lowLevel,
                                               List<String> majorList, List<String> subjectList) {

        StringBuilder stringBuilder = new StringBuilder();
        if (universityList.size() == 0) {
            stringBuilder.append("1 = 1");
        } else {
            stringBuilder.append("school_id in (");
            for(int i = 0; i < universityList.size(); i ++ ) {
                stringBuilder.append(universityList.get(i)).append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.toString().length() - 1);
            stringBuilder.append(")");
        }

        if (batch.equals("本科")) {
            stringBuilder.append(" and Batch in ('一段')");
        } else if (batch.equals("专科")) {
            stringBuilder.append(" and Batch in ('二段')");
        } else if (batch.equals("全部")) {
            stringBuilder.append(" and Batch in ('一段', '二段')");
        }

        if (majorList.size() == 0) {
            stringBuilder.append(" and 1 = 1");
        } else {
            stringBuilder.append(" and Major in (");
            for(int i = 0; i < majorList.size(); i ++ ) {
                stringBuilder.append("'" + majorList.get(i) + "'").append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.toString().length() - 1);
            stringBuilder.append(")");
        }

        if (lowLevel == 0 ) {
            stringBuilder.append(" and 1 = 1");
        } else {
            stringBuilder.append(" and LowLevel between ");

            Double low = lowLevel * 0.7;
            Double high = lowLevel * 2.0;

            stringBuilder.append(low + " and " + high);
        }



        if (subjectList.size() == 0) {
            stringBuilder.append(" and 1 = 1");
        } else {
            stringBuilder.append(" and (" + subjectList.get(0) + " in " + "('T', 'C')");
            for(int i = 1; i < subjectList.size(); i ++ ) {
                stringBuilder.append(" or " + subjectList.get(i) + " in " + "('T', 'C')");
            }
            stringBuilder.append(")");
        }

        String answer = stringBuilder.toString();

        stringBuilder.setLength(0);

        return answer;
    }

    public String getMajorWithoutUniversity(List<String> majorList, List<String> subjectList, Integer lowLevel, String batch) {
        StringBuilder stringBuilder = new StringBuilder();
        // select * from t_major where ${SQLString}

        if (majorList.size() == 0) {
            stringBuilder.append("1 = 1");
        } else {
            stringBuilder.append(" Major in ");
            stringBuilder.append("(");
            for(int i = 0; i < majorList.size(); i ++ ) {
                stringBuilder.append("'").append(majorList.get(i)).append("'").append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.toString().length() - 1);
            stringBuilder.append(")");
        }

        if (subjectList.size() == 0) {
            stringBuilder.append(" and 1 = 1");
        } else {
            stringBuilder.append(" and (" + subjectList.get(0) + " in " + "('T', 'C')");
            for(int i = 1; i < subjectList.size(); i ++ ) {
                stringBuilder.append(" or " + subjectList.get(i) + " in " + "('T', 'C')");
            }
            stringBuilder.append(")");
        }

        if (lowLevel == 0 ) {
            stringBuilder.append(" and 1 = 1");
        } else {
            stringBuilder.append(" and LowLevel between ");

            Double low = lowLevel * 0.7;
            Double high = lowLevel * 2.8;

            stringBuilder.append(low + " and " + high);
        }


        if (batch.equals("本科")) {
            stringBuilder.append(" and Batch in ('一段')");
        } else if (batch.equals("专科")) {
            stringBuilder.append(" and Batch in ('二段')");
        } else if (batch.equals("全部")) {
            stringBuilder.append(" and Batch in ('一段', '二段')");
        }

        String answer = stringBuilder.toString();

        stringBuilder.setLength(0);

        return answer;
    }

    public String getMajorByMajorIDSQLString(List<Integer> majorIdList) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        for(int i = 0; i < majorIdList.size(); i ++ ) {
            stringBuilder.append(majorIdList.get(i)).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.toString().length() - 1);
        stringBuilder.append(")");

        String answer = stringBuilder.toString();
        stringBuilder.setLength(0);
        return answer;
    }


    public String InsertAutoMajorToDatabase(List<Integer> majorId, Integer student_id) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < majorId.size(); i ++ ) {
            stringBuilder.append("(" + student_id + "," + majorId.get(i) + "),");
        }
        stringBuilder.deleteCharAt(stringBuilder.toString().length() - 1);
        String answer = stringBuilder.toString();
        return answer;
    }
}
