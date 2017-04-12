package kr.soen.practice6;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    TextView txtname, menu1, menu2, menu3, tvTel, tvURL, tvRegdate;
    ImageView menupicture;
    int _REQUEST_MSG_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        txtname = (TextView)findViewById(R.id.txtname);
        menu1 = (TextView)findViewById(R.id.menu1);
        menu2 = (TextView)findViewById(R.id.menu2);
        menu3 = (TextView)findViewById(R.id.menu3);
        tvTel = (TextView)findViewById(R.id.tvTel);
        tvURL = (TextView)findViewById(R.id.tvURL);
        tvRegdate = (TextView)findViewById(R.id.tvRegdate);
        menupicture = (ImageView)findViewById(R.id.imageView4);

        Intent intent = getIntent();
        information viewinfo = intent.getParcelableExtra("gogo");


        txtname.setText(viewinfo.getName());
        if(viewinfo.getCategoryNum() =="CHICKEN"){
            menupicture.setImageResource(R.drawable.chicken);
        }else if(viewinfo.getCategoryNum() =="PIZZA"){
            menupicture.setImageResource(R.drawable.pizza);

        }else if(viewinfo.getCategoryNum() == "HAMBURGER"){
            menupicture.setImageResource(R.drawable.hambergur);

        }
        menu1.setText(viewinfo.getMenu1());
        menu2.setText(viewinfo.getMenu2());
        menu3.setText(viewinfo.getMenu3());
        tvTel.setText(viewinfo.getTelNum());
        tvURL.setText(viewinfo.getHomepage());
        tvRegdate.setText(viewinfo.getUpdate());

    }
    public void onClick(View v){
        Intent intent = getIntent();
        information viewinfo = intent.getParcelableExtra("gogo");

        switch (v.getId()){

            case R.id.btnback :
                Intent gogomain = new Intent();
                setResult(RESULT_CANCELED,intent);
                finish();
                break;

            case R.id.imageView2 :

                String dailNum = viewinfo.getTelNum();
                Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:/"+dailNum));
                startActivity(dial);

                break;

            case R.id.imageView3 :

                String detailUri = viewinfo.getHomepage();
                Intent detail = new Intent(Intent.ACTION_VIEW, Uri.parse(detailUri));
                startActivity(detail);

                break;
        }



    }

}
