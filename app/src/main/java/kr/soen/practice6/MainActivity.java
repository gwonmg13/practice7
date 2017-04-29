package kr.soen.practice6;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    adapter adapter;
    EditText editText;
    Button b4;
    ArrayList<information> data = new ArrayList<information>();
    ArrayList<information> data2 = new ArrayList<information>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Search();
        setListview();
    }
    public void onClick(View v){
        if(v.getId()==R.id.b1){
            Intent intent = new Intent(this,Main2Activity.class);
            information s1 = new information();
            intent.putExtra("senddata2",s1);
            startActivityForResult(intent,0);
        }
        else if(v.getId() == R.id.b2){
            adapter.setNameAscSort();
        }
        else if(v.getId() == R.id.b3){
            adapter.setPicAscSort();
        }
        else if(v.getId() == R.id.b4){
            if(b4.getText().toString() == "삭제"){
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("")
                        .setIcon(R.drawable.pizza)
                        .setTitle("삭제확인")
                        .setMessage("삭제하시겠습니까?")
                        .setPositiveButton("닫기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapter.turn(false);
                                b4.setText("선택");
                            }
                        })
                        .setNegativeButton("삭제", new DialogInterface.OnClickListener() {  // 확인버튼에 이벤트걸기
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),
                                        "삭제되었습니다.",Toast.LENGTH_SHORT)
                                        .show();
                                //삭제
                                int i = 0;
                                while(i < data.size()){
                                    if(data.get(i).getChecked() == 1){
                                       data.remove(i);
                                    } else {
                                        i++;
                                    }
                                }
                                adapter = new adapter(MainActivity.this,data);
                                listView.setAdapter(adapter);
                                adapter.turn(false);
                                b4.setText("선택");
                            }
                        })
                        .show();
            }else {
                b4.setText("삭제");
                //리스트뷰 선택기능 후 ;
                adapter.turn(true);
            }
        }
    }

    public void setListview(){
        listView = (ListView)findViewById(R.id.listview) ;

        //어댑터 만들기 (데이터와 화면을 연결)
        adapter = new adapter(this,data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                intent .putExtra("senddata",data.get(position));
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0){
            if(resultCode == RESULT_OK){  //
                information input = data.getParcelableExtra("remakemsg");
                this.data.add(input);
                adapter.notifyDataSetChanged();

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void Search(){
        editText = (EditText)findViewById(R.id.editText);
        b4 = (Button)findViewById(R.id.b4);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String search = s.toString();
                adapter.getFilter().filter(search);
            }

            @Override public void afterTextChanged(Editable s) {

            }
        });
    }
}