package com.htw.vbbs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htw.vbbs.result.CodeMsg;
import com.htw.vbbs.result.Result;
import com.htw.vbbs.domain.Invitation;
import com.htw.vbbs.domain.Store;
import com.htw.vbbs.domain.User;
import com.htw.vbbs.service.InterestService;
import com.htw.vbbs.service.InvitationService;
import com.htw.vbbs.service.StoreService;
import com.htw.vbbs.service.UserService;
import com.htw.vbbs.util.TencentUtil;
import com.htw.vbbs.util.UUIDUtil;
import com.htw.vbbs.vo.InvitationVo;
import com.htw.vbbs.vo.UserVo;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TencentUtil tencentUtil;
    @Autowired
    private InvitationService invitationService;
    @Autowired
    private UserService userService;
    @Autowired
    private InterestService interestService;
    @Autowired
    private StoreService storeService;

    @RequestMapping("/setting")
    public String updateUserInfo (Model model, User user) {

        List<User> inter = interestService.getMyInterest(user.getUserId());

        model.addAttribute("inter", inter);
        model.addAttribute("user", user);
        return "setting";
    }

    @RequestMapping("/blog")
    public String toUserBlog (Model model, User user, @RequestParam(value = "id") int id,
                              @RequestParam(defaultValue = "1",value = "pageNum")int pageNum) {

        User bloguser = userService.getById(id);
        PageHelper.startPage(pageNum, 20);
        List<Invitation> invitationList = invitationService.getUserInvis(id);
        PageInfo<Invitation> page = new PageInfo<>(invitationList);
        page.getList().forEach(item->{
            item.setTrueType(invitationService.getTypeIfno(item.getType()));
        });
        int hasInter = interestService.hasInterest(user.getUserId(), id);

        model.addAttribute("hasInter", hasInter);
        model.addAttribute("pageInfo", page);
        model.addAttribute("buser",bloguser);
        model.addAttribute("user", user);
        return "userblog";
    }

    @PostMapping("/uploadImg")
    @ResponseBody
    public Result<String> uploadImg(@RequestParam(value = "file") MultipartFile file){
        if(file.isEmpty()){
            Result.error(CodeMsg.UPLOAD_IMG_FAIL);
        }
        COSClient cosClient = tencentUtil.getClient();
        String bucketName = tencentUtil.getBucketName();
        // 指定要上传到 COS 上对象键
        String key = tencentUtil.getQianzhui() + "/img/" + UUIDUtil.uuid() + "." + file.getContentType().substring(6);
        File localFile = null;
        try {
            //将 MultipartFile 类型 转为 File 类型
            localFile = File.createTempFile("temp",null);
            file.transferTo(localFile);
        }catch (IOException e){
            e.printStackTrace();
        }
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        return Result.success(tencentUtil.getPath() + key);
    }

    @PostMapping("/interest")
    @ResponseBody
    public Result<Boolean> interest(User user, @RequestParam(value = "id") int id,
                                    @RequestParam(value = "flag") int flag){

        if(flag == 0){
            interestService.interest(user.getUserId(), id);
        }else{
            interestService.cancelInterest(user.getUserId(), id);
        }
        return Result.success(true);
    }

    @RequestMapping("/mycollect")
    public String toUserBlog (Model model, User user,
                              @RequestParam(defaultValue = "1",value = "pageNum")int pageNum) {

        PageHelper.startPage(pageNum, 20);
        List<Store> storeList = storeService.getMyStore(user.getUserId());
        PageInfo<Store> page = new PageInfo<>(storeList);

        List<InvitationVo> invos = new ArrayList<>();
        page.getList().forEach(item->{
            Invitation invi = invitationService.getSimpInvById(item.getInvitationId());
            InvitationVo vo = invitationService.toVo(invi);
            invos.add(vo);
        });

        PageInfo<InvitationVo> pageInfo = new PageInfo<>(invos);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setTotal(page.getTotal());

        List<User> inter = interestService.getMyInterest(user.getUserId());

        model.addAttribute("inter", inter);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("user", user);
        return "mycollect";
    }


    @RequestMapping("/myArticle")
    public String myArticle (Model model, User user,
                              @RequestParam(defaultValue = "1",value = "pageNum")int pageNum) {

        PageHelper.startPage(pageNum, 20);
        List<Invitation> invitationList = invitationService.getUserInvis(user.getUserId());
        PageInfo<Invitation> page = new PageInfo<>(invitationList);
        page.getList().forEach(item->{
            item.setTrueType(invitationService.getTypeIfno(item.getType()));
        });

        List<User> inter = interestService.getMyInterest(user.getUserId());

        model.addAttribute("inter", inter);
        model.addAttribute("pageInfo", page);
        model.addAttribute("user", user);
        return "myArticle";
    }


    @PostMapping("/setting")
    @ResponseBody
    public Result<Boolean> updateInfo(HttpServletRequest request, HttpServletResponse response,
                                      User user, @RequestBody UserVo vo) throws ParseException {
        int re = userService.updateInfo(request, response, user.getUserId(), vo);
        return Result.success(true);
    }

    @PostMapping("/updateImg")
    @ResponseBody
    public Result<Boolean> updateImg(HttpServletRequest request,HttpServletResponse response,
                                     User user, String url){
        int re = userService.updateImg(request, response, user.getUserId(), url);
        return Result.success(true);
    }

    @RequestMapping("/tochat")
    public String toChat (Model model, User user, @RequestParam(value = "id") int id) {

        User toUser = userService.getSimpInfoById(id);
        List<User> inter = interestService.getMyInterest(user.getUserId());

        model.addAttribute("inter", inter);
        model.addAttribute("user", user);
        model.addAttribute("touser", toUser);
        return "chat";
    }
}
