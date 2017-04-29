package kr.soen.practice6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {
    EditText etname,ettel,etmenu1,etmenu2,etmenu3,etaddr;
    RadioButton radio1,radio2,radio3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        init();
    }
    void init(){
        etname = (EditText)findViewById(R.id.etname);
        ettel = (EditText)findViewById(R.id.ettel);
        etmenu1 = (EditText)findViewById(R.id.etmenu1);
        etmenu2 = (EditText)findViewById(R.id.etmenu2);
        etmenu3 = (EditText)findViewById(R.id.etmenu3);
        etaddr = (EditText)findViewById(R.id.etaddr);
        radio1 = (RadioButton)findViewById(R.id.radio1);
        radio2 = (RadioButton)findViewById(R.id.radio2);
        radio3 = (RadioButton)findViewById(R.id.radio3);
    }
    public void onClick(View v){
        if(v.getId() == R.id.btnAdd){

            int category = 0;
            if(radio1.isChecked()){ category = 1;}
            else if(radio2.isChecked()){ category = 2;}
            else if(radio3.isChecked()){ category = 3;}
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.KOREA);
            String createtime = df.format(new Date());
            Intent intent = getIntent();
            information inputed = intent.getParcelableExtra("senddata2");
            inputed.information(etname.getText().toString(),ettel.getText().toString(),category,etmenu1.getText().toString(),
                    etmenu2.getText().toString(),etmenu3.getText().toString(),etaddr.getText().toString(),createtime);
            intent.putExtra("remakemsg",inputed);
            setResult(RESULT_OK,intent);
            finish();

        } else if ( v.getId() == R.id.btnCancel){
            radio1.setChecked(true);
            etname.setText("");
            ettel.setText("");
            etmenu1.setText("");
            etmenu2.setText("");
            etmenu3.setText("");
            etaddr.setText("");
            finish();
        }
    }
}