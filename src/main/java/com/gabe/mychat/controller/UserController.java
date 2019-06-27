package com.gabe.mychat.controller;

import com.gabe.mychat.mapper.*;
import com.gabe.mychat.pojo.*;
import com.gabe.mychat.util.*;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

/**
 * @author wsw
 * @Package com.gabe.mychat.controller
 * @Description:
 * @date 2019年6月21日 15:21:34
 */
@Controller
public class UserController {
    @Autowired
    private com.gabe.mychat.mapper.userMapper userMapper;
    @Autowired
    private systemMapper systemMapper;
    @Autowired
    private Producer producer;
    @Autowired
    private userUtilMapper userUtilMapper;
    @Autowired
    private normalUserUtilMapper normalUserUtilMapper;
    @Autowired
    private normalUserMapper normalUserMapper;
    @Autowired
    sercurityLogMapper sercurityLogMapper;
    @Autowired
    friendsUtilsMapper friendsUtilsMapper;
    /**
     * 认证异常
     * <p>
     * org.apache.shiro.authc.pam.UnsupportedTokenException 身份令牌异常，不支持的身份令牌
     * org.apache.shiro.authc.UnknownAccountException       未知账户/没找到帐号,登录失败
     * org.apache.shiro.authc.LockedAccountException        帐号锁定
     * org.apache.shiro.authz.DisabledAccountException      用户禁用
     * org.apache.shiro.authc.ExcessiveAttemptsException    登录重试次数，超限。只允许在一段时间内允许有一定数量的认证尝试
     * org.apache.shiro.authc.ConcurrentAccessException     一个用户多次登录异常：不允许多次登录，只能登录一次 。即不允许多处登录
     * org.apache.shiro.authz.AccountException              账户异常
     * org.apache.shiro.authz.ExpiredCredentialsException   过期的凭据异常
     * org.apache.shiro.authc.IncorrectCredentialsException 错误的凭据异常
     * org.apache.shiro.authc.CredentialsException          凭据异常
     * org.apache.shiro.authc.AuthenticationException       上面异常的父类
     */
    @ResponseBody
    @ArchivesLog(operationName = "登录",operationType = "用户基本操作")
    @RequestMapping("/login")
    public R login(@RequestBody Map<String,String> map, HttpSession session, ServletRequest request){
        System.out.println("进入登录...");
        String username=map.get("username");
        String password=map.get("password");
        String code=map.get("code");
        String kaptcha= ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if(!kaptcha.equalsIgnoreCase(code)){
            return R.error("验证码不正确");
        }
      //认证异常处理
        try {
            Subject subject= ShiroUtils.getSubject();
            UsernamePasswordToken token=new UsernamePasswordToken(username,password);
            subject.login(token);
        } catch (UnknownAccountException e) {//错误的账号
            return R.error(e.getMessage());
        }catch (IncorrectCredentialsException e) {//错误的凭证
            return R.error(e.getMessage());
        }catch (LockedAccountException e) {//锁定的账号
            return R.error(e.getMessage());
        }catch (AuthenticationException e) {//以上的父类
            return R.error("账户验证失败");
        }
        //判斷是否是管理员
        int role=Integer.parseInt(map.get("role"));

        session.setAttribute("user",ShiroUtils.getUserEntity());
        session.setAttribute("id",ShiroUtils.getUserEntity().getUserId());
        Map map1 = new HashMap();
        if(ShiroUtils.getUserEntity().getRole()==role){
                if(role==1){
                    map1.put("nickname",ShiroUtils.getUserEntity().getNickname());
                }
                else {
                    if(ShiroUtils.getUserEntity().getStatus()==0){
                        Map<String, Object> msg = new HashMap<>();
                        user user = ShiroUtils.getUserEntity();
                        normalUser normalUser = normalUserUtilMapper.selectUserById(user.getUserId());
                        map1 = UserUtil.completeUser(user, normalUser);
                        map1.remove("password");
                    }
                    else {
                        return R.error("账户已被冻结");
                    }
            }
        }
        else{
            return R.error("权限不对");
        }
        //安全日志
        sercurityLog sercurityLog1=new sercurityLog();
        Date date=new Date();
        long date1=date.getTime();
        String log_id=ShiroUtils.getUserEntity().getUserId()+date1;
        //设置日志id
        sercurityLog1.setLogId(log_id);
        //日记记录时间
        sercurityLog1.setLoginTime(date);
        //用户id
        sercurityLog1.setUserId(ShiroUtils.getUserEntity().getUserId());
        //获取ip
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String ip=NetworkUtil.getIpAddress(httpServletRequest);
        //存入ip
        sercurityLog1.setLoginAddress(ip);
        sercurityLogMapper.insert(sercurityLog1);
        return R.ok().put("data",map1);
    }

    /*
     * 生成图形验证码
     */
    @ResponseBody
    @ArchivesLog(operationName = "获取验证码",operationType = "用户基本操作")
    @RequestMapping("/imgCode" )
    public R imgCode(){
        System.out.println("获取验证码");
        //生成文字验证码
        String text = producer.createText();
        System.out.println("验证码是:"+text);
        //生成图片验证码
        BufferedImage image = producer.createImage(text);

        //保存到shiro session（注意：如果没有securityManager配置，则暂时无法工作，测试时先注释掉）
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        //转base64
        BASE64Encoder encoder = new BASE64Encoder();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
        try {
            ImageIO.write(image, "png", baos);//写入流中
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();//转换成字节
        String png_base64 =  encoder.encodeBuffer(bytes).trim();//转换成base64串
        //删除 \r\n
        png_base64 = "data:image/jpeg;base64,"+png_base64.replaceAll("\n", "").replaceAll("\r", "");
        return R.ok().put("data",png_base64);
    }
    @ResponseBody
    @ArchivesLog(operationName = "登出",operationType = "用户基本操作")
    @RequestMapping("/logout" )
    public R logout(HttpSession session){
        String id=(String)session.getAttribute("id");
        System.out.println("账号"+id+"退出...");
        session.removeAttribute("user");
        session.removeAttribute("id");
        return R.ok("退出成功");
    }
    /**
     * 查询信息
     * @param reMap
     * @return
     * @Description:查询用户信息。要求:1.不能查询到自身。2.长度为12且为全为数字则是id查询，否则是昵称查询。
     * @Description:3.查询到的用户如果已经是该用户的好友则将其标记。
     */
    @ResponseBody
    @ArchivesLog(operationType = "查询信息", operationName = "根据用户昵称或id查询用户")
    @RequestMapping(value = "/selectUserByNickName")
    public Map<String,Object> selectUserByNickName(@RequestBody Map<String,Object> reMap,HttpSession session) {
               //接收参数
                String nickname=(String)reMap.get("value");
                //获取用户自身
                String user_id=(String) session.getAttribute("id");
                user userself=userMapper.selectByPrimaryKey(user_id);
                //判断是否为用户自身
                String username=userself.getNickname();
                if(username.equals(nickname)){
                    return R.ok();
                }
                //如果不是用户自身
                else {
                    List list = new ArrayList();
                    //长度为12且不全为数字，则是昵称查询
                    if (NumberUtil.getNumberFromString(nickname).length() != 12) {
                        List<user> user = userUtilMapper.selectUserByNickName(nickname);
                        Iterator it = user.listIterator();
                        while (it.hasNext()) {
                            user user1 = (user) it.next();
                            normalUser normalUser = normalUserUtilMapper.selectUserById(user1.getUserId());
                            Map map = new HashMap();
                            map = UserUtil.completeUser(user1, normalUser);
                            //判断是否是好友
                            List<friends> friendsList=friendsUtilsMapper.selectByUserId(userself.getUserId());
                            Iterator friendit=friendsList.iterator();
                            while(friendit.hasNext()){
                                friends friends=(friends) friendit.next();
                                if(friends.getFriendId().equals(user1.getUserId())){
                                    map.put("isfriend",true);
                                }
                            }
                            list.add(map);
                        }
                        return R.ok().put("data", list);
                    }
                    //长度为12且为数字，则是id查询
                    else {

                        user user = userMapper.selectByPrimaryKey(nickname);
                        //判断是否是同一用户
                        if(username.equals(user.getNickname())){
                            return R.ok();
                        }
                        normalUser normalUser = normalUserUtilMapper.selectUserById(user.getUserId());
                        Map map = new HashMap();
                        map = UserUtil.completeUser(user, normalUser);
                        //判断是否是好友
                        List<friends> friendsList=friendsUtilsMapper.selectByUserId(userself.getUserId());
                        Iterator friendit=friendsList.iterator();
                        while(friendit.hasNext()){
                            friends friends=(friends) friendit.next();
                            if(friends.getFriendId().equals(user.getUserId())){
                                map.put("isfriend",true);
                            }
                        }
                        list.add(map);
                        return R.ok().put("data", list);
                    }
                }
             /*   }*/
    }
    /**
     * 更新信息
     * @param file
     * @return
     */
    @ResponseBody
    @ArchivesLog(operationName = "更改头像",operationType = "用户基本操作")
    @RequestMapping("/updateImg2" )
    public R updateImg(@RequestBody MultipartFile file,HttpSession session){
        String id=(String)session.getAttribute("id");
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File("src/main/resources/static/img/"+id+".png"
                              /*  file.getOriginalFilename()*/)));
                System.out.println(file.getName());
                String url="/img/"+id+".png";
                user user=(user)session.getAttribute("user");
                user.setImgurl(url);
                userMapper.updateByPrimaryKeySelective(user);
                user.setImgurl(url);
                session.setAttribute("user",user);
                out.write(file.getBytes());
                out.flush();
                out.close();
                return R.ok("上传成功").put("data",url);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return R.error("上传失败"+e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                return R.error("上传失败"+e.getMessage());
            }
        } else {
            return R.error("上传失败");
        }
    }
    /**
     * 更新信息
     * @param file
     * @return
     */
    @ResponseBody
    @ArchivesLog(operationName = "更改头像",operationType = "用户基本操作")
    @RequestMapping("/updateImg" )
    public R updateImg(@RequestBody MultipartFile file,HttpSession session, HttpServletRequest request){
        String id=(String)session.getAttribute("id");
        user user=(user)session.getAttribute("user");
        //目前这里是写死的本地硬盘路径
        String path = "D:/img";
 /*       logger.info("path:" + path);*/
        //获取文件名称
        String fileName = file.getOriginalFilename();
        //获取文件名后缀
        Calendar currTime = Calendar.getInstance();
        String time = String.valueOf(currTime.get(Calendar.YEAR))+String.valueOf((currTime.get(Calendar.MONTH)+1));
        //获取文件名后缀
        String suffix = fileName.substring(file.getOriginalFilename().lastIndexOf("."));
        suffix = suffix.toLowerCase();
        if(suffix.equals(".jpg") || suffix.equals(".jpeg") || suffix.equals(".png")/* || suffix.equals(".gif")*/){
          /*  fileName = UUID.randomUUID().toString()+suffix;*/
            File targetFile = new File(path, id+suffix);
            if(!targetFile.getParentFile().exists()){    //注意，判断父级路径是否存在
                targetFile.getParentFile().mkdirs();
            }
            long size = 0;
            //保存
            try {
                file.transferTo(targetFile);
                size = file.getSize();
            } catch (Exception e) {
                e.printStackTrace();
                return R.error("上传失败！");
            }
            //项目url，这里可以使用常量或者去数据字典获取相应的url前缀；

            //文件获取路径
            String fileUrl = "/img/" + id+suffix;
            user.setImgurl(fileUrl);
            userMapper.updateByPrimaryKeySelective(user);
            user.setImgurl(fileUrl);
            session.setAttribute("user",user);
     /*       logger.info("fileUrl:" + fileUrl);
   */         return R.ok().put("fileUrl", fileUrl);
        }else{
            return R.error("图片格式有误，请上传.jpg、.png、.jpeg格式的文件");
        }

    }
    /**
     * 查询信息
     * @param
     * @return
     */
/*    @ResponseBody
    @ArchivesLog(operationName = "获取用户信息",operationType = "用户基本操作")
    @RequestMapping("/getUserInfo" )
    public R updateImg(HttpSession session){

        return R.ok().put("data",session.getAttribute("user"));
    }*/
    /**
     * 更新信息
     * @param reMap
     * @return
     */
    @ResponseBody
    @ArchivesLog(operationName = "修改用户信息",operationType = "用户基本操作")
    @RequestMapping("/updateUserInfo" )
    public R updateUserInfo(@RequestBody Map<String,Object> reMap,HttpSession session){
        String name=(String)reMap.get("name");
        String nickname=(String)reMap.get("nickname");
        String gender=(String)reMap.get("gender");
        int age=(int)reMap.get("age");
        String address=(String)reMap.get("address");
        user user=(user)session.getAttribute("user");
        normalUser normalUser=normalUserUtilMapper.selectUserById(user.getUserId());
        user.setName(name);
        user.setNickname(nickname);
        normalUser.setGender(gender);
        normalUser.setAddress(address);
        normalUser.setAge(age);
        normalUserExample normalUserExample=new normalUserExample();
        normalUserExample.Criteria  criteria=normalUserExample.createCriteria();
        criteria.andUserIdEqualTo(user.getUserId());
        userMapper.updateByPrimaryKeySelective(user);
        normalUserMapper.updateByExampleSelective(normalUser,normalUserExample);
        session.setAttribute("user",user);
        return R.ok();
    }
}
