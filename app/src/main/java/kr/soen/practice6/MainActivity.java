package kr.soen.practice6;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    TextView change;
    final int _REQUEST_MSG_CODE  = 10;
    final int _REQUEST_MSG_CODE2  = 20;

    int count;
    ArrayList<String> strArray = new ArrayList<String>(); //클래스 객체를 저장하는 어레이배열.
    ArrayList<information> classArray = new ArrayList<information>(); //클래스 객체를 저장하는 어레이배열.
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("나의 맛집");
        listView = (ListView)findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,strArray);
        listView.setAdapter(adapter);
        setListView();


        change = (TextView)findViewById(R.id.change); // 맛집 개수를 바꿔줄 텍스트뷰

        /*
        Intent intent = getIntent();//인텐트 값을 받아온다.
        information in = intent.getParcelableExtra("info_1"); //in은 자료형이 객체이다.

        System.out.print("-----------in은 과연뭘까---------\n"+in);
        if(in==null){
            System.out.print("-----------비어있는거.---------\n");
        }else {
            System.out.print("-----------안비어있어요.---------\n");

            String result = in.toString();
            aeee++;
            System.out.print("----------"+aeee+".---------\n");


            classArray.add(in); // 정보 클래스 어레이배열
            strArray.add(in.getName()); // 제목 어레이 배열

            for (information str : classArray) {
                System.out.println(str.toString());
                System.out.print("--------------------\n");
            }
            for (String str : strArray) {
                System.out.println(str.toString());
                System.out.print("--------------------\n");
            }
            change.setText("맛집 리스트("+strArray.size()+"개)");//맛집 개수 바꿔넣기

        }
*/


    }
    public void onClick(View v){
        if(v.getId() == R.id.btn1){
            Intent main2 = new Intent(this, Main2Activity.class); //클릭하면 메인2로간다.
            startActivityForResult(main2,_REQUEST_MSG_CODE);

        }

    }
    public void setListView(){
        //어댑터 연결

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

              @Override
              public boolean onItemLongClick(AdapterView<?> parent, View view,final int position, long id) {

                  AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                  dlg.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          strArray.remove(position);
                          adapter.notifyDataSetChanged();
                      }
                  });
                  dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {

                      }
                  });
                  dlg.setTitle("삭제확인");
                  dlg.setMessage("선택한 내용을 정말 삭제하실 거에요?");
                  dlg.show();


                  Snackbar.make( view , "삭제가 완료되었습니다. ", Snackbar.LENGTH_LONG).show();

                  return false;
              }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //상세화면으로 이동하기 , 인텐트날리기
                Intent intent3 = new Intent(MainActivity.this, Main3Activity.class); //현재화면의 제어권자, 넘어갈 클래스
                intent3.putExtra("gogo",classArray.get(position));
                //classArray에 있는 그 포지션!! 에 있는 객체를 보낼거야

                startActivityForResult(intent3,_REQUEST_MSG_CODE2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == _REQUEST_MSG_CODE && resultCode == RESULT_OK) {
            count++;
            change.setText("맛집 리스트" + count + "개");
            information in1 = data.getParcelableExtra("info_1");
            classArray.add(in1);
            strArray.add(in1.getName());
            adapter.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}



