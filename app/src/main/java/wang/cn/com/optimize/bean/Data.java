package wang.cn.com.optimize.bean;

import android.net.Uri;

import java.util.List;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-09
 * @time: 11:19
 */
public class Data {

    private String avatar;
    private String nickname;
    private String createTime;
    private String content;
    private List<Uri> pictureList;
    private List<Uri> pictureThumbList;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Uri> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Uri> pictureList) {
        this.pictureList = pictureList;
    }

    public List<Uri> getPictureThumbList() {
        return pictureThumbList;
    }

    public void setPictureThumbList(List<Uri> pictureThumbList) {
        this.pictureThumbList = pictureThumbList;
    }
}
