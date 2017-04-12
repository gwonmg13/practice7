package kr.soen.practice6;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by manggi on 2017. 4. 6..
 */

public class information implements Parcelable {
    private String name ="";
    private String telNum ="";
    private String menu1 ="";
    private String menu2 ="";
    private String menu3 ="";
    private String Homepage ="";
    private String update="";
    private String categoryNum = "";

    public information(String name, String telNum, String menu1, String menu2, String menu3, String homepage, String update, String categoryNum){
        this.name = name;
        this.telNum = telNum;
        this.menu1 = menu1;
        this.menu2 = menu2;
        this.menu3 = menu3;
        this.Homepage = homepage;
        this.update = update;
        this.categoryNum = categoryNum;
    }

    protected information(Parcel in) {
        name = in.readString();
        telNum = in.readString();
        menu1 = in.readString();
        menu2 = in.readString();
        menu3 = in.readString();
        Homepage = in.readString();
        update = in.readString();
        categoryNum = in.readString();
    }

    public static final Creator<information> CREATOR = new Creator<information>() {
        @Override
        public information createFromParcel(Parcel in) {
            return new information(in);
        }

        @Override
        public information[] newArray(int size) {
            return new information[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenu1() {
        return menu1;
    }

    public void setMenu1(String menu1) {
        this.menu1 = menu1;
    }

    public String getMenu2() {
        return menu2;
    }

    public void setMenu2(String menu2) {
        this.menu2 = menu2;
    }

    public String getMenu3() {
        return menu3;
    }

    public void setMenu3(String menu3) {
        this.menu3 = menu3;
    }

    public String getHomepage() {
        return Homepage;
    }

    public void setHomepage(String homepage) {
        Homepage = homepage;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getCategoryNum() {
        return categoryNum;
    }

    public void setCategoryNum(String categoryNum) {
        this.categoryNum = categoryNum;
    }

    @Override
    public String toString() {
        return "information{" +
                "name='" + name + '\'' +
                ", categoryNum=" + categoryNum +
                ", telNum='" + telNum + '\'' +
                ", Homepage='" + Homepage + '\'' +
                ", update='" + update + '\'' +
                ", menu1='" + menu1 + '\'' +
                ", menu2='" + menu2 + '\'' +
                ", menu3='" + menu3 + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(telNum);
        dest.writeString(menu1);
        dest.writeString(menu2);
        dest.writeString(menu3);
        dest.writeString(Homepage);
        dest.writeString(update);
        dest.writeString(categoryNum);

    }
}
