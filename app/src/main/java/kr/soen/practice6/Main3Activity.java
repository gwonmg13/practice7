package kr.soen.practice6;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    information selected;
    ImageView imgno;
    TextView etmenu1, etmenu2, etmenu3, tvTel, tvURL, tvRegdate ,txtname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        init();
    }
    void init(){
        txtname = (TextView)findViewById(R.id.txtname);
        etmenu1 = (TextView)findViewById(R.id.etmenu1);
        etmenu2 = (TextView)findViewById(R.id.etmenu2);
        etmenu3 = (TextView)findViewById(R.id.etmenu3);
        tvTel = (TextView)findViewById(R.id.tvTel);
        tvURL = (TextView)findViewById(R.id.tvURL);
        tvRegdate = (TextView)findViewById(R.id.tvRegdate);
        imgno = (ImageView)findViewById(R.id.imgno);

        Intent intent = getIntent();
        selected  = (information)intent.getParcelableExtra("senddata");
        //액티비티로 불러온 정보를 표시
            txtname.setText(selected.getName());
            etmenu1.setText(selected.getMenu1());
            etmenu2.setText(selected.getMenu2());
            etmenu3.setText(selected.getMenu3());
            tvTel.setText(selected.getTelnum());
            tvURL.setText(selected.getHomepage());
            tvRegdate.setText(selected.getCreatetime());
        //이미지 설정
            if(selected.getPic() == 1){
            imgno.setImageDrawable(getResources().getDrawable(R.drawable.chicken));
        }
            else if(selected.getPic() == 2){
            imgno.setImageDrawable(getResources().getDrawable(R.drawable.hambergur));
        }
            else if(selected.getPic() == 3){
            imgno.setImageDrawable(getResources().getDrawable(R.drawable.pizza));
        }

    }
    public void onClick(View v ){
        if(v.getId() == R.id.btnback){
            finish();
        }
        else if(v.getId() == R.id.imageView2){
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:/"+selected.getTelnum()));
            startActivity(intent);
        }
        else if(v.getId() == R.id.imageView3){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+selected.getHomepage()));
            startActivity(intent);
        }
    }
}