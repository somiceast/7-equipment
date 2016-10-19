package com.usaydemo.a7_frame_demo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class AdmMainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AdmMainActivity";
    //学生
    private Button addStu, chgStu, delStu,
    //课程
            addCou, chgCou, delCou,
    //成绩
            seeAch, addAch, seeall,
    //教师
            addTea, chgTea, delTea, seeTch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_main);

        addStu = (Button) findViewById(R.id.id_add_student_btn);
        chgStu = (Button) findViewById(R.id.id_chgstu_btn);
        delStu = (Button) findViewById(R.id.id_delstu_btn);

        addCou = (Button) findViewById(R.id.id_addcou_btn);
        chgCou = (Button) findViewById(R.id.id_chgcou_btn);
        delCou = (Button) findViewById(R.id.id_delcou_btn);

        addAch = (Button) findViewById(R.id.id_addach_btn);
//        seeAch = (Button) findViewById(R.id.id_seeach_btn);
//        seeall = (Button) findViewById(R.id.id_seeallscore_btn);

        addTea = (Button) findViewById(R.id.id_addtch_btn);
        chgTea = (Button) findViewById(R.id.id_chgtch_btn);
        delTea = (Button) findViewById(R.id.id_deltch_btn);
//        seeTch = (Button) findViewById(R.id.id_toltch_btn);

        //click
        addStu.setOnClickListener(this);
        chgStu.setOnClickListener(this);
        delStu.setOnClickListener(this);

        addCou.setOnClickListener(this);
        chgCou.setOnClickListener(this);
        delCou.setOnClickListener(this);

        addAch.setOnClickListener(this);
//        seeAch.setOnClickListener(this);
//        seeall.setOnClickListener(this);

        addTea.setOnClickListener(this);
        chgTea.setOnClickListener(this);
        delTea.setOnClickListener(this);
//        seeTch.setOnClickListener(this);

        Log.d(TAG, "onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_add_student_btn:
                mIntent(PAddStuActivity.class);
                break;
            case R.id.id_chgstu_btn:
                mIntent(PChgStuActivity.class);
                break;
            case R.id.id_delstu_btn:
                mIntent(PDelStuActivity.class);
                break;

            case R.id.id_addtch_btn:
                mIntent(PAddTchActivity.class);
                break;
            case R.id.id_chgtch_btn:
                mIntent(PChgTchActivity.class);
                break;
            case R.id.id_deltch_btn:
                mIntent(PDelTchActivity.class);
                break;

//            case R.id.id_toltch_btn:
//                mIntent(PTolTchActivity.class);
//                break;

            case R.id.id_addcou_btn:
                mIntent(PAddCouActivity.class);
                break;
            case R.id.id_chgcou_btn:
                mIntent(PChgCouActivity.class);
                break;
            case R.id.id_delcou_btn:
                mIntent(PDelCouActivity.class);
                break;

            case R.id.id_addach_btn:
                mIntent(PAddAchActivity.class);
                break;
//            case R.id.id_seeach_btn:
//                mIntent(PSeeAchActivity.class);
//                break;
//            case R.id.id_seeallscore_btn:
//                mIntent(PSeeAllScoreActivity.class);
//                break;
        }
    }

    private void mIntent(Class a){
        Intent abc = new Intent(getApplicationContext(),a);
        startActivity(abc);
    }
}
