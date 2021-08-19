package com.tommy.domain;

/**
 * coding and debug by tommy
 */

public class OptimizationAllCity {

    public String stringTogethers(String manager, String level, String layer, String features) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("1 = 1");

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
}
