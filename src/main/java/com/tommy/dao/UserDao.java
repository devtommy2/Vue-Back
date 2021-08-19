package com.tommy.dao;

import com.tommy.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * code and debug by tommy
 */

public interface UserDao {

    // 这个dao接口负责了用户和超管的登陆基本查询，存在返回1，反之返回2，具体的判断逻辑在Controller中
    int queryUser(Map<String,Object> map);

//    查询该用户名是否存在，要保证用户名的唯一性
    int queryUserbyUsername(String username);

//     新增用户数据到t_student_information 其中的必填数据为username和password
    int addUserToTable(Map<String, Object> map);    // 在service层进行操作

//    更新用户，不是改密码，这里指的是更新普通信息，按照用户名进行修改
    void updateUser(Map<String, Object> map);

//    修改密码的实现：
    void changePassword(Map<String, Object> map);

//    通过用户名获取用户身份
    String getIdentity(String username);

    void addUser(Map<String, Object> map);

//    List<Major> showMajor();

    List<Teacher> showTeacher();

    void insertTeacherId(Map<String, Object> map);

    List<University> selectAllCitiesUniversity(String condition);

    List<University> selectCitiesUniversity(String condition);

    List<University> selectUniversityByFuzzyName(String Name);

    List<Major> selectMajorByType(String condition);

    List<String> selectTypeDetails(String condition);

    List<Major> getMajorByFuzzySelect(String condition);

    List<Major> getMajorJustLimitLevel(String level);

    Integer getStudentIdByUsername(String username);

    void insertIntoStudentUniversity(@Param("student_id") Integer student_id, @Param("university_id") Integer university_id);

    int quaryUniversityIfExist(@Param("student_id") Integer student_id,@Param("university_id") Integer university_id);

    List<Integer> getUniversityIdByStudentId(Integer student_id);

    List<University> getUniversityByUniversityList(String SQLString);

    List<Major> getMajorByNeeds(String sqlstring);

    Integer getUniversityNums(Integer student_id);

    void deleteUniversityIdByStudentId(@Param("student_id") Integer student_id, @Param("university_id") Integer university_id);

    List<T_Major> getMajorByUniversitySelected(String SQLString);

    List<T_Major> getMajorWithoutUniversity(String SQLString);

    void insertIntoStudentMajor(@Param("student_id") Integer student_id, @Param("major_id") Integer major_id);

    List<Integer> getMajorId(Integer student_id);

    List<T_Major> getMajorByMajorId(String SQLString);

    int quaryMajorIfExist(@Param("student_id") Integer student_id, @Param("major_id") Integer major_id);

    void deleteMajorByStudent(@Param("student_id") Integer student_id, @Param("major_id") Integer major_id);

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
