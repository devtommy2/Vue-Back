package com.tommy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tommy.common.AssembleResponseMsg;
import com.tommy.common.jwt.JJwtUtils;
import com.tommy.domain.*;
import com.tommy.domain.ResponseBody;
import com.tommy.domain.saveOrderUtils.OptimizationSaveOrder;
import com.tommy.domain.utils.SwitchToEnglish;
import com.tommy.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author tommy
 *   code and debug by tommy
 */

@RestController
@RequestMapping("/User")

public class UserController {

    @Autowired
    private UserService userService;

    private String LoginError = "登录失败，请检查您的用户名或密码重新登陆";
    private String RegisterError_1 = "注册失败，该用户名或该准考证号已被其他用户注册";
    private String RegisterError_2 = "Admission ticket number and user name cannot be repeated";

    // 登录的Controller(超管和普通用户的登陆鉴权)    # code by tommy 实现的是token鉴权机制
    @CrossOrigin("http://127.0.0.1:8081/#/Login")
    @RequestMapping(value = "/Login",produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public ResponseBody queryUser(HttpServletRequest request, @RequestBody Map<String, Object> map){



        Map<String,String> all = new HashMap<>();

        JJwtUtils jwtUtils = new JJwtUtils();

        String username_header = request.getHeader("username");

        String identity_header = request.getHeader("identity");

        String tokenStr = request.getHeader("token");


        if (tokenStr != null) {
            JWT jwt = jwtUtils.checkJWT(tokenStr, username_header, identity_header);
            if (jwt.getStatueCode() == 200 && jwt.getStatueMessage().equals("jwt验证成功")) {
                // ***** debug by tommy
                all.put("identity", identity_header);
                all.put("token", tokenStr);
                return new AssembleResponseMsg().success(all);
            } else {
                return new AssembleResponseMsg().failure(200, jwt.getStatueCode(), jwt.getStatueMessage());
            }
        } else {
            int flag = userService.queryUser(map);
            if (flag == 1) {
                String username_login = (String) map.get("username");
                String identity_login = userService.getIdentity(username_login);
                String token_new = jwtUtils.CreateJwt(username_login, identity_login);
                all.put("identity", identity_login);
                all.put("token", token_new);
                all.put("username", username_login);
                return new AssembleResponseMsg().success(all);
            } else {
                return new AssembleResponseMsg().failure(200, 400, LoginError);
            }
        }
    } // 登陆小问题bug解决完成  debug by tommy 2021.8/3 at 20:07


    // 实现用户的注册功能代码：注册代码已被重构：2021/8/6  21:01
    @RequestMapping(value = "/SignUp", produces = "application/json;charset=utf-8")
    public ResponseBody addUser(@RequestBody Map<String, Object> map) {

        String username = (String) map.get("username");

        int flag = userService.queryUserbyUsername(username);

        if (flag == 1 ) {
            return new AssembleResponseMsg().failure(200, 400, RegisterError_1);
        } else { // If signIn one student, Then create two table: username and st_number with column ......
            userService.addUser(map);
            return new AssembleResponseMsg().success("Register success");
        }
    }

    //    更新用户信息 这里需要优化一下
    @RequestMapping(value = "/updateUser", produces = "application/json;charset=utf-8")
    public ResponseBody updateUser(HttpServletRequest request, @RequestBody Map<String, Object> map) {

        // Don't need user to enter String named 'username'

        String token = request.getHeader("token"); // get token from request header
        JJwtUtils jwtUtils = new JJwtUtils(); // Create
        Jws<Claims> jws = jwtUtils.readingJwt(token);
        String username = (String) jws.getBody().get("username");
        map.put("username", username);

        /**
         *
         *  code by tommy at 2021/8/3  14:47
         * 在请求头中的username等身份信息是通过token得到的
         * 避免用户只要知道其他用户的用户名直接修改别人的信息，这里使用了二次校验：反向解析token 后获取到 username， 和输入的
         * 信息进行对比，正确允许修改，反之不允许。
         *
         * 在这里不需要用户输入username了，通过token校验身份。
         */

        userService.updateUser(map);
        return new AssembleResponseMsg().success("Update success");
    }

    //    修改密码：
    @RequestMapping(value = "/changePassword", produces = "application/json;charset=utf-8")
    public ResponseBody changePassword(HttpServletRequest request, @RequestBody Map<String, Object> map) {

        Map<String, Object> username_password = new HashMap<>();
        Map<String, Object> updatePassword = new HashMap<>(); // 储存新密码和username的

        String token = request.getHeader("token");

        JJwtUtils jwtUtils = new JJwtUtils();
        Jws<Claims> jws = jwtUtils.readingJwt(token);

        String username = (String) jws.getBody().get("username");  // 当前登陆状态的用户的username
        String password = (String) map.get("password");

        username_password.put("username", username);
        username_password.put("password", map.get("old_password"));

        updatePassword.put("username", username);
        updatePassword.put("password", password);

        int flag = userService.queryUser(username_password);

        if (flag == 1) {  // 旧密码验证成功
            userService.changePassword(updatePassword);
            return new AssembleResponseMsg().success("Password reset complete.");
        } else {
            return new AssembleResponseMsg().failure(200, 400, "请输入正确的原密码!");
        }
    }

//    向学生展示老师的个人信息以供选择;
    @RequestMapping(value = "/showTeacher", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public PageInfo<Teacher> showTeacher(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Teacher> teacherList = userService.showTeacher();
        PageInfo<Teacher> pageInfo = new PageInfo<>(teacherList);
        return pageInfo;
    }

    /**
     * 前端GET发送teacher_id
     * 后端从请求头中取出 token --->  student_username ---> student_id
     * 通过student_id 在 t_student_information 中插入 teacher_id
     * 通过teacher_id 插入 student_id
     */
    @RequestMapping(value = "/studentSelectTeacher", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public ResponseBody studentSelectTeacher(HttpServletRequest request, Integer teacher_id) {
        String tokenStr = request.getHeader("token");
        JJwtUtils jwtUtils = new JJwtUtils(); // Create
        Jws<Claims> jws = jwtUtils.readingJwt(tokenStr);
        String username = (String) jws.getBody().get("username");
//        update t_student_information set teacher_id = #{teacher_id} where username = #{username}
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("teacher_id", teacher_id);
        userService.insertTeacherId(map);
        return new AssembleResponseMsg().success("添加成功");
    }

    @RequestMapping(value = "/showUniversityByNeed", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public PageInfo<University> showUniversityByNeed(HttpServletRequest request, @RequestBody Map<String, Object> map) {

        String tokenStr = request.getHeader("token");

        List<String> cityList = (List<String>) map.get("Position");

        String manager = (String) map.get("Manage");   // 所属部门

        String level = (String) map.get("Level");   // 985院校  211院校

        String layer = (String) map.get("Layer");   // 本科  专科

        String features = (String) map.get("Features");  // T双一流   F不是

        Integer pageNum = (Integer) map.get("pageNum");

        Integer pageSize = (Integer) map.get("pageSize");


        cityList.removeAll(Collections.singleton(null));  // 取出空字符串

        PageHelper.startPage(pageNum, pageSize);

        int length = cityList.size();

        if (length == 0) {
            OptimizationAllCity optimizationAllCity = new OptimizationAllCity();
            String together = optimizationAllCity.stringTogethers(manager, level, layer, features);
            List<University> universityList = userService.selectAllCitiesUniversity(together);
            PageInfo<University> pageInfo = new PageInfo<>(universityList);

            PageHelper.clearPage();
            return pageInfo;
        } else {
            Optimization optimization = new Optimization();
            String together = optimization.StringTogether(cityList, manager, level, layer, features);
            List<University> universityList = userService.selectCitiesUniversity(together);
            PageInfo<University> pageInfo = new PageInfo<>(universityList);

            PageHelper.clearPage();
            return pageInfo;
        }

    }

    // University_name Fuzzy select
    @RequestMapping(value = "/getUniversityByName", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public List<University> getUniversityByName(HttpServletRequest request, @RequestBody Map<String, Object> map) {

        String Name = (String) map.get("Name");

        if (!Name.equals("")) {
            OptimizationFuzzy optimizationFuzzy = new OptimizationFuzzy();

            String together = optimizationFuzzy.getOptimizationFuzzyString(Name);

            return userService.selectUniversityByFuzzyName(together);
        }
        return null;
    }


    // 显示专业库：
    @RequestMapping(value = "/showMajorByNeeds", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public PageInfo<Major> showMajorByNeeds(HttpServletRequest request, @RequestBody Map<String, Object> map) {

        Integer pageNum = (Integer) map.get("pageNum");

        Integer pageSize = (Integer) map.get("pageSize");

        List<String> majorList = (List<String>) map.get("type_detail");

        majorList.removeAll(Collections.singleton(null));

        String Level = (String) map.get("level1_name");

        int majorSize = majorList.size();

        if (majorSize == 0) {

            String SQLString = new Optimization().getAllMajorNameJustLimitByLevel(Level);
            PageHelper.startPage(pageNum, pageSize);
            List<Major> majorList1 = userService.getMajorJustLimitLevel(SQLString);
            PageInfo<Major> pageInfo = new PageInfo<>(majorList1);
            PageHelper.clearPage();
            return pageInfo;
        } else {

            String majorListMoreDetail = new OptimizationGetDetail().getDetailMajor(majorList);

            PageHelper.startPage(pageNum, pageSize);

            List<Major> majorList1 = userService.getMajorByNeeds(majorListMoreDetail);

            PageInfo<Major> pageInfo = new PageInfo<>(majorList1);

            PageHelper.clearPage();
            return pageInfo;
        }

    }

    @RequestMapping(value = "/showMajorByFuzzy", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public List<Major> showMajorByFuzzy(HttpServletRequest request, @RequestBody Map<String, Object> map) {

        String FuzzyString = (String) map.get("FuzzyString");

        String FuzzySQLString = new OptimizationSearchFuzzyMajor().getOptimizationSearchFuzzyMajor(FuzzyString);

        return userService.getMajorByFuzzySelect(FuzzySQLString);
    }

    // 学生挑选学校
    @RequestMapping(value = "/selectUniversityByStudent", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public ResponseBody selectUniversityByStudent(HttpServletRequest request, @RequestBody Map<String, Object> map) {

        String tokenStr = request.getHeader("token");
        JJwtUtils jwtUtils = new JJwtUtils();
        Jws<Claims> jws = jwtUtils.readingJwt(tokenStr);
        String username = (String) jws.getBody().get("username");

        Integer student_id = userService.getStudentIdByUsername(username);
        Integer university_id = (Integer) map.get("university_id");
        Integer university_nums = userService.getUniversityNums(student_id);

        int flag = userService.quaryUniversityIfExist(student_id, university_id);

        if (university_nums <= 90) {
            if (flag != 0) {
                return new AssembleResponseMsg().failure(200, 400, "该学校已被选择，换个学校试试吧~~~");
            } else {
                userService.insertIntoStudentUniversity(student_id, university_id);
                return new AssembleResponseMsg().success("添加学校成功~~~");
            }
        } else {
            return new AssembleResponseMsg().failure(200, 400, "备选学校数量最大为90个~~~");
        }
    }

    // 学生查看自己选择的学校（不是专业）
    @RequestMapping(value = "/showUniversitySelected", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public List<University> showUniversitySelected(HttpServletRequest request, @RequestBody Map<String, Object> map) {
        String tokenStr = request.getHeader("token");
        JJwtUtils jwtUtils = new JJwtUtils();
        Jws<Claims> jws = jwtUtils.readingJwt(tokenStr);
        String username = (String) jws.getBody().get("username");

        Integer student_id = userService.getStudentIdByUsername(username);

        List<Integer> University_id_list = userService.getUniversityIdByStudentId(student_id);

        if (University_id_list.size() == 0) {
            return null;
        }

        String SQLString = new Optimization().getAllUniversityInfoByUniversityId(University_id_list);

        return userService.getUniversityByUniversityList(SQLString);
    }

    // 删除选择的学校：   需求：需要删除的学校id
    @RequestMapping(value = "/deleteUniversitySelected", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public ResponseBody deleteUniversitySelected(HttpServletRequest request, @RequestBody Map<String, Object> map) {
        String tokenStr = request.getHeader("token");
        JJwtUtils jwtUtils = new JJwtUtils();
        Jws<Claims> jws = jwtUtils.readingJwt(tokenStr);
        String username = (String) jws.getBody().get("username");
        Integer student_id = userService.getStudentIdByUsername(username);
        Integer university_id = (Integer) map.get("university_id");
        if (userService.quaryUniversityIfExist(student_id, university_id) != 0) {
            userService.deleteUniversityIdByStudentId(student_id, university_id);
            return new AssembleResponseMsg().success("删除成功~~");
        } else {
            return new AssembleResponseMsg().success("这个学校已经不在您的院校库啦~~");
        }
    }


    /**
     * 从选择的学校中生成专业
     * 该处理器方法仅供调用按钮处理使用
     */
    @RequestMapping(value = "/showMajorFromSelectUniversity", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public PageInfo<T_Major> showMajorFromSelectUniversity(HttpServletRequest request, @RequestBody Map<String, Object> map) {

        String tokenStr = request.getHeader("token");
        JJwtUtils jwtUtils = new JJwtUtils();
        Jws<Claims> jws = jwtUtils.readingJwt(tokenStr);
        String username = (String) jws.getBody().get("username");
        Integer student_id = userService.getStudentIdByUsername(username);

        Integer pageNum = (Integer) map.get("pageNum");

        Integer pageSize = (Integer) map.get("pageSize");

        Integer lowLevel = (Integer) map.get("lowLevel");

        String batch = (String) map.get("batch");

        List<String> majorList = (List<String>) map.get("majorList");

        List<String> subjectList = (List<String>) map.get("subject");

        List<String> subjectLists = new SwitchToEnglish().switchToEnglish(subjectList);

        // 得到学校的id集合
        List<Integer> University_id_list = userService.getUniversityIdByStudentId(student_id);


        if (University_id_list.size() == 0) {
            return null;
        } else {

            String SQLString = new Optimization().getMajorByUniversitySelected(University_id_list, batch, lowLevel, majorList, subjectLists);

            PageHelper.startPage(pageNum, pageSize);
            List<T_Major> t_majors = userService.getMajorByUniversitySelected(SQLString);
            PageInfo<T_Major> pageInfo = new PageInfo<>(t_majors);

            PageHelper.clearPage();
            return pageInfo;
        }
    }

    /**
     * 按钮：点击 “显示所有”及其默认加载的页面都是这个接口
     * @param request
     * @param map
     * @return
     */

    @RequestMapping(value = "/showMajorWithoutUniversity", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public PageInfo<T_Major> showMajorWithoutUniversity(HttpServletRequest request, @RequestBody Map<String, Object> map) {

        Integer pageNum = (Integer) map.get("pageNum");

        Integer pageSize = (Integer) map.get("pageSize");

        Integer lowLevel = (Integer) map.get("lowLevel");

        String batch = (String) map.get("batch");

        List<String> majorList = (List<String>) map.get("majorList");

        List<String> subjectList = (List<String>) map.get("subject");

        // replaceAll
        List<String> subjectLists = new SwitchToEnglish().switchToEnglish(subjectList);

        // 直接拼接sql语句
        /**
         * select * from t_major where .......
         */

        String SQLString = new Optimization().getMajorWithoutUniversity(majorList, subjectLists, lowLevel, batch);

        PageHelper.startPage(pageNum, pageSize);
        List<T_Major> t_majors = userService.getMajorWithoutUniversity(SQLString);
        PageInfo<T_Major> pageInfo = new PageInfo<>(t_majors);

        return pageInfo;

    }

    // 学生自主添加专业到备选栏中：
    @RequestMapping(value = "/insertMajorIntoDatabase", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public ResponseBody insertMajorIntoDatabase(HttpServletRequest request, @RequestBody Map<String, Object> map) {

        String tokenStr = request.getHeader("token");
        JJwtUtils jwtUtils = new JJwtUtils();
        Jws<Claims> jws = jwtUtils.readingJwt(tokenStr);
        String username = (String) jws.getBody().get("username");
        Integer student_id = userService.getStudentIdByUsername(username);
        Integer major_id = (Integer) map.get("major_id");

        int flag = userService.quaryMajorIfExist(student_id, major_id);

        if (flag != 0) {
            return new AssembleResponseMsg().failure(200, 400, "该专业已经被选择，换个专业看看吧~~~");
        } else {
            userService.insertIntoStudentMajor(student_id, major_id);
            return new AssembleResponseMsg().success("添加专业成功~~~");
        }

    }

    // 显示学生选择的专业
    @RequestMapping(value = "/showMajorSelected", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public List<T_Major> showMajorSelected(HttpServletRequest request, @RequestBody Map<String, Object> map) {
        String tokenStr = request.getHeader("token");
        JJwtUtils jwtUtils = new JJwtUtils();
        Jws<Claims> jws = jwtUtils.readingJwt(tokenStr);
        String username = (String) jws.getBody().get("username");
        Integer student_id = userService.getStudentIdByUsername(username);

        // 获取到学生所选择的所有专业的id
        List<Integer> majorId = userService.getMajorId(student_id);

        if (majorId.size() == 0) {
            return null;
        } else {
            // 根据id 拿到专业信息
            String SQLString = new Optimization().getMajorByMajorIDSQLString(majorId);
            List<T_Major> majorList = userService.getMajorByMajorId(SQLString);
            return majorList;
        }
    }

    // 删除学生选择的专业：
    @RequestMapping(value = "/deleteMajorByStudent", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public ResponseBody deleteMajorByStudent(HttpServletRequest request, @RequestBody Map<String, Object> map) {
        String tokenStr = request.getHeader("token");
        JJwtUtils jwtUtils = new JJwtUtils();
        Jws<Claims> jws = jwtUtils.readingJwt(tokenStr);
        String username = (String) jws.getBody().get("username");
        Integer student_id = userService.getStudentIdByUsername(username);
        Integer major_id = (Integer) map.get("major_id");
        userService.deleteMajorByStudent(student_id, major_id);
        return new AssembleResponseMsg().success("删除成功~~");
    }

    @RequestMapping(value = "/getUserInformation", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public List<UserInformationShow> getUserInformation(HttpServletRequest request, @RequestBody Map<String, Object> map) {
        String tokenStr = request.getHeader("token");
        JJwtUtils jwtUtils = new JJwtUtils();
        Jws<Claims> jws = jwtUtils.readingJwt(tokenStr);
        String username = (String) jws.getBody().get("username");
        return userService.getUserInformation(userService.getStudentIdByUsername(username));
    }


    // 一分一段的查询：
    @RequestMapping(value = "/getScoreLevel", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public List<Integer> getScoreLevel(@RequestBody Map<String, Object> map) {
        Integer score = (Integer) map.get("score");
        return userService.getScoreLevel(score);
    }



//     一键生成96个志愿
    @RequestMapping(value = "/getAutoMajor", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public List<T_Major> getAutoMajor(HttpServletRequest request, @RequestBody Map<String, Object> map) {

        Integer lowLevel = (Integer) map.get("lowLevel");    // 获取到位次

        String batch = (String) map.get("batch");   // 获取到需求，本科和专科

        List<String> majorList = (List<String>) map.get("majorList");

        List<String> subjectList = (List<String>) map.get("subject");    // 选课的数组

        List<String> subjectLists = new SwitchToEnglish().switchToEnglish(subjectList);

//        拼接SQL
        String SQLString = new Optimization().getMajorWithoutUniversity(majorList, subjectLists, lowLevel, batch);

        return userService.getAutoMajor(SQLString);
    }

    // 提交至数据库

    @RequestMapping(value = "/submitAutoMajorToDataBase", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public ResponseBody submitAutoMajorToDataBase(HttpServletRequest request, @RequestBody Map<String, Object> map) {

        String tokenStr = request.getHeader("token");
        JJwtUtils jwtUtils = new JJwtUtils();
        Jws<Claims> jws = jwtUtils.readingJwt(tokenStr);
        String username = (String) jws.getBody().get("username");
        Integer student_id = userService.getStudentIdByUsername(username);

        List<Integer> majorId = (List<Integer>) map.get("majorId");

        if (majorId.size() == 0) {
            return new AssembleResponseMsg().failure(200, 400, "添加失败");
        } else {
            String SQLString = new Optimization().InsertAutoMajorToDatabase(majorId, student_id);

            userService.insertAutoMajorToDatabase(SQLString);

            return new AssembleResponseMsg().success("添加成功，请到个人自动院校库中查看~~~");
        }
    }

    // 删除自动生成的专业：
    @RequestMapping(value = "/deleteMajorByAuto", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public ResponseBody deleteMajorByAuto(HttpServletRequest request, @RequestBody Map<String, Object> map) {
        String tokenStr = request.getHeader("token");
        JJwtUtils jwtUtils = new JJwtUtils();
        Jws<Claims> jws = jwtUtils.readingJwt(tokenStr);
        String username = (String) jws.getBody().get("username");
        Integer student_id = userService.getStudentIdByUsername(username);
        Integer major_id = (Integer) map.get("major_id");
        userService.deleteAutoMajor(student_id, major_id);
        return new AssembleResponseMsg().success("删除成功~~");
    }

    // 查看自动生成的专业:
    @RequestMapping(value = "/showMajorAuto", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public List<T_Major> showMajorAuto(HttpServletRequest request, @RequestBody Map<String, Object> map) {
        String tokenStr = request.getHeader("token");
        JJwtUtils jwtUtils = new JJwtUtils();
        Jws<Claims> jws = jwtUtils.readingJwt(tokenStr);
        String username = (String) jws.getBody().get("username");
        Integer student_id = userService.getStudentIdByUsername(username);

        // 获取到学生所选择的所有专业的id
        List<Integer> majorId = userService.getAutoMajorId(student_id);

        if (majorId.size() == 0) {
            return null;
        } else {
            // 根据id 拿到专业信息
            String SQLString = new Optimization().getMajorByMajorIDSQLString(majorId);
            List<T_Major> majorList = userService.getMajorByMajorId(SQLString);
            return majorList;
        }
    }

    // 更新数据库以保存自选专业的顺序
    @RequestMapping(value = "/saveMajorSelectedOrder", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public ResponseBody saveMajorSelectedOrder(HttpServletRequest request, @RequestBody Map<String, Object> map) {

        String tokenStr = request.getHeader("token");
        JJwtUtils jwtUtils = new JJwtUtils();
        Jws<Claims> jws = jwtUtils.readingJwt(tokenStr);
        String username = (String) jws.getBody().get("username");
        Integer student_id = userService.getStudentIdByUsername(username);

        List<Integer> major_id = (List<Integer>) map.get("major_id");

        List<Integer> old_major_id = userService.getMajorId(student_id);

        String SQLString = new OptimizationSaveOrder().saveSelectedMajorOrder(old_major_id, major_id, student_id);

        userService.saveSelectedMajorOrder(SQLString);

        return new AssembleResponseMsg().success("保存成功~~~");
    }

    @RequestMapping(value = "/saveAutoMajorOrder", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public ResponseBody saveAutoMajorOrder(HttpServletRequest request, @RequestBody Map<String, Object> map) {
        String tokenStr = request.getHeader("token");
        JJwtUtils jwtUtils = new JJwtUtils();
        Jws<Claims> jws = jwtUtils.readingJwt(tokenStr);
        String username = (String) jws.getBody().get("username");
        Integer student_id = userService.getStudentIdByUsername(username);

        List<Integer> major_id = (List<Integer>) map.get("major_id");

        List<Integer> old_major_id = userService.getAutoMajorId(student_id);

        String SQLString = new OptimizationSaveOrder().saveAutoMajorOrder(old_major_id, major_id, student_id);

        userService.saveAutoMajorOrder(SQLString);

        return new AssembleResponseMsg().success("保存成功~~~");
    }
}

