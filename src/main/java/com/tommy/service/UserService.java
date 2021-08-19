package com.tommy.service;

import com.tommy.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * coding and debug by tommy
 */

public interface UserService {

    int queryUser(Map<String,Object> map);

    int queryUserbyUsername(String username);

    void updateUser(Map<String, Object> map);

    String getIdentity(String username);

    void changePassword(Map<String, Object> map);

    void addUser(Map<String, Object> map);

    List<Teacher> showTeacher();

    void insertTeacherId(Map<String, Object> map);

    List<University> selectAllCitiesUniversity(String condition);

    List<University> selectCitiesUniversity(String condition);

    List<University> selectUniversityByFuzzyName(String Name);

    List<Major> selectMajorByType(String condition);

    List<String> selectTypeDetails(String condition);

    List<Major> getMajorByFuzzySelect(String condition);

    List<Major> getMajorJustLimitLevel(String level);

    List<Major> getMajorByNeeds(String sqlstring);

    Integer getStudentIdByUsername(String username);

    void insertIntoStudentUniversity(Integer student_id, Integer university_id);

    int quaryUniversityIfExist(Integer student_id, Integer university_id);

    List<Integer> getUniversityIdByStudentId(Integer student_id);

    List<University> getUniversityByUniversityList(String SQLString);

    void deleteUniversityIdByStudentId(Integer student_id, Integer university_id);

    Integer getUniversityNums(Integer student_id);

    List<T_Major> getMajorByUniversitySelected(String SQLString);

    List<T_Major> getMajorWithoutUniversity(String SQLString);

    void insertIntoStudentMajor(@Param("student_id") Integer student_id, @Param("major_id") Integer major_id);

    List<Integer> getMajorId(Integer student_id);

    List<T_Major> getMajorByMajorId(String SQLString);

    int quaryMajorIfExist(Integer student_id, Integer major_id);

    void deleteMajorByStudent(Integer student_id, Integer major_id);

    List<UserInformationShow> getUserInformation(Integer student_id);

    int countStudentUniversity(Integer student_id);

    List<Integer> getScoreLevel(Integer score);

    List<T_Major> getAutoMajor(String SQLString);

    void insertAutoMajorToDatabase(String SQLString);

    void deleteAutoMajor(@Param("student_id") Integer student_id, @Param("major_id") Integer major_id);

    List<Integer> getAutoMajorId(Integer student_id);

    void saveSelectedMajorOrder(String SQLString);

    void saveAutoMajorOrder(String SQLString);

}
