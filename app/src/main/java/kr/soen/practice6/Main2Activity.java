package kr.soen.practice6;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {

    final int _REQUEST_MSG_CODE =10;

    EditText etname,ettel,etmenu1,etmenu2,etmenu3,etaddr;
    RadioButton radio1, radio2, radio3;
    //private ArrayList<information> informations = new ArrayList<information>();
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void onClick(View v){

        if( v.getId() == R.id.btnAdd ){
            Snackbar.make(v, "등록이 완료되었습니다. ", Snackbar.LENGTH_LONG).show();

            etname = (EditText)findViewById(R.id.etname);
            radio1 = (RadioButton)findViewById(R.id.radio1);
            radio2 = (RadioButton)findViewById(R.id.radio2);
            radio3 = (RadioButton)findViewById(R.id.radio3);
            ettel = (EditText)findViewById(R.id.ettel);
            etmenu1 = (EditText)findViewById(R.id.etmenu1);
            etmenu2 = (EditText)findViewById(R.id.etmenu2);
            etmenu3 = (EditText)findViewById(R.id.etmenu3);
            etaddr = (EditText)findViewById(R.id.etaddr);
            String category = "";

            String name = etname.getText().toString();
            if(radio1.isChecked()){
                category = "CHICKEN";
            }else if(radio2.isChecked()){
                category = "PIZZA";
            }else if(radio3.isChecked()){
                category = "HAMBURGER";
            }


            //이거 인트값으로 넣어줘야함.
            String telNum = ettel.getText().toString();
            String menu1 = etmenu1.getText().toString();
            String menu2 = etmenu2.getText().toString();
            String menu3 = etmenu3.getText().toString();
            String addr = etaddr.getText().toString();

            long now = System.currentTimeMillis();
            Date date = new Date(now);
            SimpleDateFormat dateFormat = new  SimpleDateFormat("yyyyMMdd", java.util.Locale.getDefault());
            String strDate = dateFormat.format(date);


            information info = new information( name,telNum,menu1,menu2,menu3,addr,strDate,category);
            System.out.print(info.toString()+"\n");

            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("info_1",info);
            setResult(RESULT_OK,intent);
            finish();


        }else if(v.getId() == R.id.btnCancel){

            Intent maingogo = new Intent(this,MainActivity.class);
            setResult(RESULT_CANCELED,maingogo);
            finish();

        }
    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if(requestCode==_REQUEST_MSG_CODE){
//            if(resultCode==RESULT_OK){
//                String msg = data.getStringExtra("remake_msg");
//
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}
