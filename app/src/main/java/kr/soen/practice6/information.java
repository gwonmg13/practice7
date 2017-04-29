package kr.soen.practice6;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;

public class information implements Parcelable{
    private String name = "";
    private String telnum = "";
    private int pic = 0;
    private String menu1 = "";
    private String menu2 = "";
    private String menu3 = "";
    private String homepage = "";
    private String createtime = "";
    private int checked = 0;


    protected information(Parcel in) {
        name = in.readString();
        telnum = in.readString();
        pic = in.readInt();
        menu1 = in.readString();
        menu2 = in.readString();
        menu3 = in.readString();
        homepage = in.readString();
        createtime = in.readString();
        checked = in.readInt();
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



    public information(){
        this.name = "";
        this.telnum = "";
        this.pic = 0;
        this.menu1 = "";
        this.menu2 = "";
        this.menu3 = "";
        this.homepage = "";
        this.createtime = "";
        this.checked = 0;
    }
    public void information(String name,String telnum,int pic, String menu1, String menu2 , String menu3, String homepage ,String createtime){
        this.name = name;
        this.telnum = telnum;
        this.pic = pic;
        this.menu1 = menu1;
        this.menu2 = menu2;
        this.menu3 = menu3;
        this.homepage = homepage;
        this.createtime = createtime;
    }

    public String getTelnum() {return this.telnum;}
    public int getPic(){return this.pic;}
    public String getMenu1() {return this.menu1;}
    public String getMenu2() {return this.menu2;}
    public String getMenu3() {return this.menu3;}
    public String getHomepage() {return this.homepage;}
    public String getCreatetime(){return this.createtime;}
    public String getName() {return this.name;}
    public int getChecked() {return this.checked;}

    public void turnChecked() {
        if(this.checked == 0){
            this.checked = 1;
        } else {
            this.checked = 0;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(telnum);
        dest.writeInt(pic);
        dest.writeString(menu1);
        dest.writeString(menu2);
        dest.writeString(menu3);
        dest.writeString(homepage);
        dest.writeString(createtime);
        dest.writeInt(checked);
    }


}



