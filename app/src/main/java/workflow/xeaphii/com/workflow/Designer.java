package workflow.xeaphii.com.workflow;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 8/28/2015.
 */
public class Designer extends Activity {
    TextView WorkflowName;
    Button ActivityButton, ActivitiesButton, IfButton,ElseButton,RepearButton,GenerateButton,DeleteActivity;

    private final int ActivitiesCount = 5;
    WebView WebDesigner;
    String Activities = "Init";
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.designer);
        WorkflowName= (TextView) findViewById(R.id.workflow_name);
        Intent i = getIntent();
        name = i.getStringExtra("name");
        WorkflowName.setText(i.getStringExtra("name"));
        ActivityButton = (Button) findViewById(R.id.activity_button);
        ActivitiesButton = (Button) findViewById(R.id.activities_button);
        IfButton = (Button) findViewById(R.id.if_button);
        ElseButton = (Button) findViewById(R.id.else_button);
        RepearButton = (Button) findViewById(R.id.repeat_button);
        GenerateButton = (Button) findViewById(R.id.generate_button);
        DeleteActivity= (Button) findViewById(R.id.delete_button);
        WebDesigner = (WebView) findViewById(R.id.web_designer);

        WebDesigner.getSettings().setJavaScriptEnabled(true);
        ActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Designer.this);
                dialog.setContentView(R.layout.activity_list);
                dialog.setTitle("Choose any activity");

                final RadioButton StepActivity = (RadioButton) dialog.findViewById(R.id.steps_activity);
                final RadioButton WaitActivity = (RadioButton) dialog.findViewById(R.id.wait_activity);
                final RadioButton TurnLeftActivity = (RadioButton) dialog.findViewById(R.id.left_turn_activity);
                final RadioButton TurnRightActivity = (RadioButton) dialog.findViewById(R.id.right_turn_activity);
                final RadioButton SaySomethingActivity = (RadioButton) dialog.findViewById(R.id.say_something_activity);

                final EditText StepsCount = (EditText) dialog.findViewById(R.id.steps_count);
                final EditText SecondsCount = (EditText) dialog.findViewById(R.id.seconds_count);
                final EditText SaySomethingCount = (EditText) dialog.findViewById(R.id.say_something_text);


//                StepActivity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                    }
//                });

                StepActivity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(getApplicationContext(),"Click",Toast.LENGTH_LONG).show();
                        StepActivity.setChecked(true);
                        WaitActivity.setChecked(false);
                        TurnLeftActivity.setChecked(false);
                        TurnRightActivity.setChecked(false);
                        SaySomethingActivity.setChecked(false);
                        StepsCount.setEnabled(true);
                        SecondsCount.setEnabled(false);
                        SaySomethingCount.setEnabled(false);


                    }
                });

                WaitActivity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StepActivity.setChecked(false);
                        WaitActivity.setChecked(true);
                        TurnLeftActivity.setChecked(false);
                        TurnRightActivity.setChecked(false);
                        SaySomethingActivity.setChecked(false);
                        StepsCount.setEnabled(false);
                        SecondsCount.setEnabled(true);
                        SaySomethingCount.setEnabled(false);

                    }
                });

                TurnLeftActivity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StepActivity.setChecked(false);
                        WaitActivity.setChecked(false);
                        TurnLeftActivity.setChecked(true);
                        TurnRightActivity.setChecked(false);
                        SaySomethingActivity.setChecked(false);
                        StepsCount.setEnabled(false);
                        SecondsCount.setEnabled(false);
                        SaySomethingCount.setEnabled(false);
                    }
                });

                TurnRightActivity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StepActivity.setChecked(false);
                        WaitActivity.setChecked(false);
                        TurnLeftActivity.setChecked(false);
                        TurnRightActivity.setChecked(true);
                        SaySomethingActivity.setChecked(false);
                        StepsCount.setEnabled(false);
                        SecondsCount.setEnabled(false);
                        SaySomethingCount.setEnabled(false);

                    }
                });

                SaySomethingActivity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StepActivity.setChecked(false);
                        WaitActivity.setChecked(false);
                        TurnLeftActivity.setChecked(false);
                        TurnRightActivity.setChecked(false);
                        SaySomethingActivity.setChecked(true);
                        StepsCount.setEnabled(false);
                        SecondsCount.setEnabled(false);
                        SaySomethingCount.setEnabled(true);
                    }
                });


                Button dialogButton = (Button) dialog.findViewById(R.id.go_button);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
//                        DisplayMetrics metrics;
//                        metrics = new DisplayMetrics();
//                        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//
//                        LayoutInflater Inflater = (LayoutInflater) Designer.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                        View view = Inflater.inflate(R.layout.designer, null);
//                        LinearLayout layout = (LinearLayout) view.findViewById(R.id.designer_layout);
//
//                        LinearLayout LL = new LinearLayout(Designer.this);
//                        LL.setOrientation(LinearLayout.HORIZONTAL);
//                        LinearLayout.LayoutParams LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//                        LLParams.gravity = Gravity.CENTER;
//                        LLParams.setMargins(0, getDPI(20, metrics), 0, 0);
//                        LL.setLayoutParams(LLParams);
//
//                        RelativeLayout RL = new RelativeLayout(Designer.this);
//                        RelativeLayout.LayoutParams RLParams = new RelativeLayout.LayoutParams(getDPI(150, metrics),getDPI(80, metrics));
//                        RL.setBackgroundResource(R.mipmap.custombutton);
//                        RL.setLayoutParams(RLParams);
//
//                        Button Tag = new Button(Designer.this);
//                        RelativeLayout.LayoutParams but_params = new RelativeLayout.LayoutParams(
//                                RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
//                        but_params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//                        but_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
//                        Tag.setLayoutParams(but_params);
//                        Tag.setText("Temp");
//                        Tag.setBackgroundColor(getResources().getColor(android.R.color.transparent));
//
//
//
//
//
//                        RL.addView(Tag);
//                        LL.addView(RL);
//                        layout.addView(LL);
//                        Toast.makeText(getApplicationContext(),"Click",Toast.LENGTH_LONG).show();


                        if (StepActivity.isChecked()) {

                            Activities += CheckIfCondition() + "Walk" +
                                    StepsCount.getText().toString() +
                                    "Steps";
                        } else if (WaitActivity.isChecked()) {
                            Activities += CheckIfCondition() + "Wait" +
                                    SecondsCount.getText().toString() +
                                    "Seconds";
                        } else if (TurnLeftActivity.isChecked()) {
                            Activities += CheckIfCondition() + "TurnLeft";
                        } else if (TurnRightActivity.isChecked()) {
                            Activities += CheckIfCondition() + "TurnRight";
                        } else if (SaySomethingActivity.isChecked()) {
                            Activities += CheckIfCondition() + "Say'" +
                                    SaySomethingCount.getText().toString() +
                                    "'";
                        }
                        WebDesigner.loadUrl("file:///android_asset/html/classes.html" + "?string_input=" +
                                Activities +
                                "");
                    }
                });

                dialog.show();

            }
        });
        ActivitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Designer.this);
                dialog.setContentView(R.layout.activities_list);
                dialog.setTitle("Choose pool of activites");


                final CheckBox StepActivity= (CheckBox) dialog.findViewById(R.id.steps_activity);
                final CheckBox WaitActivity= (CheckBox) dialog.findViewById(R.id.wait_activity);
                final CheckBox  TurnLeftActivity= (CheckBox) dialog.findViewById(R.id.left_turn_activity);
                final CheckBox  TurnRightActivity= (CheckBox) dialog.findViewById(R.id.right_turn_activity);
                final CheckBox SaySomethingActivity= (CheckBox) dialog.findViewById(R.id.say_something_activity);

                final EditText  StepsCount= (EditText) dialog.findViewById(R.id.steps_count);
                final EditText  SecondsCount= (EditText) dialog.findViewById(R.id.seconds_count);
                final EditText SaySomethingCount= (EditText) dialog.findViewById(R.id.say_something_text);

                StepActivity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            StepsCount.setEnabled(true);
                        } else {
                            StepsCount.setEnabled(false);
                        }
                    }
                });

                WaitActivity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            SecondsCount.setEnabled(true);
                        } else {
                            SecondsCount.setEnabled(false);
                        }
                    }
                });


                SaySomethingActivity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            SaySomethingCount.setEnabled(true);
                        } else {
                            SaySomethingCount.setEnabled(false);
                        }
                    }
                });


                Button dialogButton = (Button) dialog.findViewById(R.id.go_button);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        boolean isChecked = false;
                        if(StepActivity.isChecked()){
                            Activities += CheckIfCondition()+"Walk" +
                                    StepsCount.getText().toString() +
                                    "Steps";
                            isChecked = true;

                        }
                        if(WaitActivity.isChecked()){
                            if(isChecked){
                                Activities += " And Wait" +
                                        SecondsCount.getText().toString() +
                                        "Seconds";
                            }else{
                                Activities += CheckIfCondition()+"Wait" +
                                        SecondsCount.getText().toString() +
                                        "Seconds";
                            }

                            isChecked = true;
                        }
                        if(TurnLeftActivity.isChecked()){
                            if(isChecked){
                                Activities += " And TurnLeft";
                            }else{
                                Activities += CheckIfCondition()+"TurnLeft";
                            }

                            isChecked = true;
                        }
                        if(TurnRightActivity.isChecked()){
                            if(isChecked){
                                Activities += " And TurnRight";
                            }else{
                                Activities +=CheckIfCondition()+ "TurnRight";
                            }

                            isChecked = true;
                        }
                        if(SaySomethingActivity.isChecked()){
                            if(isChecked){
                                Activities += " And Say'" +
                                        SaySomethingCount.getText().toString() +
                                        "'";
                            }else{
                                Activities += CheckIfCondition()+"Say'" +
                                        SaySomethingCount.getText().toString() +
                                        "'";
                            }

                            isChecked = true;
                        }
                        WebDesigner.loadUrl("file:///android_asset/html/classes.html" + "?string_input=" +
                                Activities+
                                "");
                    }

                });

                dialog.show();
            }
        });
        IfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(Designer.this);
                dialog.setContentView(R.layout.activity_list);
                dialog.setTitle("Choose any activity for if contition");

                final RadioButton StepActivity = (RadioButton) dialog.findViewById(R.id.steps_activity);
                final RadioButton WaitActivity = (RadioButton) dialog.findViewById(R.id.wait_activity);
                final RadioButton TurnLeftActivity = (RadioButton) dialog.findViewById(R.id.left_turn_activity);
                final RadioButton TurnRightActivity = (RadioButton) dialog.findViewById(R.id.right_turn_activity);
                final RadioButton SaySomethingActivity = (RadioButton) dialog.findViewById(R.id.say_something_activity);

                final EditText StepsCount = (EditText) dialog.findViewById(R.id.steps_count);
                final EditText SecondsCount = (EditText) dialog.findViewById(R.id.seconds_count);
                final EditText SaySomethingCount = (EditText) dialog.findViewById(R.id.say_something_text);


//                StepActivity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                    }
//                });

                StepActivity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(getApplicationContext(),"Click",Toast.LENGTH_LONG).show();
                        StepActivity.setChecked(true);
                        WaitActivity.setChecked(false);
                        TurnLeftActivity.setChecked(false);
                        TurnRightActivity.setChecked(false);
                        SaySomethingActivity.setChecked(false);
                        StepsCount.setEnabled(true);
                        SecondsCount.setEnabled(false);
                        SaySomethingCount.setEnabled(false);


                    }
                });

                WaitActivity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StepActivity.setChecked(false);
                        WaitActivity.setChecked(true);
                        TurnLeftActivity.setChecked(false);
                        TurnRightActivity.setChecked(false);
                        SaySomethingActivity.setChecked(false);
                        StepsCount.setEnabled(false);
                        SecondsCount.setEnabled(true);
                        SaySomethingCount.setEnabled(false);

                    }
                });

                TurnLeftActivity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StepActivity.setChecked(false);
                        WaitActivity.setChecked(false);
                        TurnLeftActivity.setChecked(true);
                        TurnRightActivity.setChecked(false);
                        SaySomethingActivity.setChecked(false);
                        StepsCount.setEnabled(false);
                        SecondsCount.setEnabled(false);
                        SaySomethingCount.setEnabled(false);
                    }
                });

                TurnRightActivity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StepActivity.setChecked(false);
                        WaitActivity.setChecked(false);
                        TurnLeftActivity.setChecked(false);
                        TurnRightActivity.setChecked(true);
                        SaySomethingActivity.setChecked(false);
                        StepsCount.setEnabled(false);
                        SecondsCount.setEnabled(false);
                        SaySomethingCount.setEnabled(false);

                    }
                });

                SaySomethingActivity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StepActivity.setChecked(false);
                        WaitActivity.setChecked(false);
                        TurnLeftActivity.setChecked(false);
                        TurnRightActivity.setChecked(false);
                        SaySomethingActivity.setChecked(true);
                        StepsCount.setEnabled(false);
                        SecondsCount.setEnabled(false);
                        SaySomethingCount.setEnabled(true);
                    }
                });


                Button dialogButton = (Button) dialog.findViewById(R.id.go_button);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                        if (StepActivity.isChecked()) {

                            Activities += CheckIfCondition() + "If(Walk" +
                                    StepsCount.getText().toString() +
                                    "Steps)";
                        } else if (WaitActivity.isChecked()) {
                            Activities += CheckIfCondition() + "If(Wait" +
                                    SecondsCount.getText().toString() +
                                    "Seconds)";
                        } else if (TurnLeftActivity.isChecked()) {
                            Activities += CheckIfCondition() + "If(TurnLeft)";
                        } else if (TurnRightActivity.isChecked()) {
                            Activities += CheckIfCondition() + "If(TurnRight)";
                        } else if (SaySomethingActivity.isChecked()) {
                            Activities += CheckIfCondition() + "If(Say'" +
                                    SaySomethingCount.getText().toString() +
                                    ")'";
                        }
                        ShowActivitiesDialogue();

                    }
                });
                dialog.show();
            }
        });
        ElseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowActivitiesDialogueElse();
            }
        });
        DeleteActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] temp = Activities.split(",");
                Activities = temp[0];
                for (int i = 1; i < temp.length-1; i++) {
                    Activities = Activities + "," + temp[i];
                }
                WebDesigner.loadUrl("file:///android_asset/html/classes.html" + "?string_input=" +
                        Activities +
                        "");
            }
        });
        RepearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(Designer.this);
                dialog.setContentView(R.layout.repeat_dialog);
                dialog.setTitle("Repeat parameters");


                final EditText RepeatCount = (EditText) dialog.findViewById(R.id.count_repeat);


                Button dialogButton = (Button) dialog.findViewById(R.id.go_button);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                        String[] temp = Activities.split(",");
                        temp[temp.length - 1] = "Repeat (" +
                                RepeatCount.getText().toString().trim() +
                                "){ " + temp[temp.length - 1] + " }";
                        Activities = temp[0];
                        for (int i = 1; i < temp.length; i++) {
                            Activities = Activities + "," + temp[i];
                        }
                        WebDesigner.loadUrl("file:///android_asset/html/classes.html" + "?string_input=" +
                                Activities +
                                "");
                    }
                });
                dialog.show();


            }
        });
        GenerateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkExternalMedia();
                writeToSDFile();
            }
        });

    }
    String CheckIfCondition(){
            return ",";
    }
    private void writeToSDFile(){

        // Find the root of the external storage.
        // See http://developer.android.com/guide/topics/data/data-  storage.html#filesExternal

        File root = android.os.Environment.getExternalStorageDirectory();


        // See http://stackoverflow.com/questions/3551821/android-write-to-sd-card-folder

        File dir = new File (root.getAbsolutePath() + "/");
        dir.mkdirs();
        File file = new File(dir, name+".txt");

        try {
            FileOutputStream f = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(f);
            pw.println("Code written\n");

            String[] temp = Activities.split(",");

            for(int i = 1 ; i < temp.length;i++){
                pw.println(temp[i]+"\n");
            }

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void checkExternalMedia(){
        boolean mExternalStorageAvailable = false;
        boolean mExternalStorageWriteable = false;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // Can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // Can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            // Can't read or write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }

    }
    void ShowActivitiesDialogue(){
        final Dialog dialog = new Dialog(Designer.this);
        dialog.setContentView(R.layout.activities_list);
        dialog.setTitle("Choose then part");


        final CheckBox StepActivity= (CheckBox) dialog.findViewById(R.id.steps_activity);
        final CheckBox WaitActivity= (CheckBox) dialog.findViewById(R.id.wait_activity);
        final CheckBox  TurnLeftActivity= (CheckBox) dialog.findViewById(R.id.left_turn_activity);
        final CheckBox  TurnRightActivity= (CheckBox) dialog.findViewById(R.id.right_turn_activity);
        final CheckBox SaySomethingActivity= (CheckBox) dialog.findViewById(R.id.say_something_activity);

        final EditText  StepsCount= (EditText) dialog.findViewById(R.id.steps_count);
        final EditText  SecondsCount= (EditText) dialog.findViewById(R.id.seconds_count);
        final EditText SaySomethingCount= (EditText) dialog.findViewById(R.id.say_something_text);

        StepActivity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    StepsCount.setEnabled(true);
                } else {
                    StepsCount.setEnabled(false);
                }
            }
        });

        WaitActivity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SecondsCount.setEnabled(true);
                } else {
                    SecondsCount.setEnabled(false);
                }
            }
        });


        SaySomethingActivity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SaySomethingCount.setEnabled(true);
                } else {
                    SaySomethingCount.setEnabled(false);
                }
            }
        });


        Button dialogButton = (Button) dialog.findViewById(R.id.go_button);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                boolean isChecked = false;
                if(StepActivity.isChecked()){
                    Activities += CheckIfCondition()+"Then{Walk" +
                            StepsCount.getText().toString() +
                            "Steps";
                    isChecked = true;

                }
                if(WaitActivity.isChecked()){
                    if(isChecked){
                        Activities += " And Wait" +
                                SecondsCount.getText().toString() +
                                "Seconds";
                    }else{
                        Activities += CheckIfCondition()+"Then{Wait" +
                                SecondsCount.getText().toString() +
                                "Seconds";
                    }

                    isChecked = true;
                }
                if(TurnLeftActivity.isChecked()){
                    if(isChecked){
                        Activities += " And TurnLeft";
                    }else{
                        Activities += CheckIfCondition()+"Then{TurnLeft";
                    }

                    isChecked = true;
                }
                if(TurnRightActivity.isChecked()){
                    if(isChecked){
                        Activities += " And TurnRight";
                    }else{
                        Activities +=CheckIfCondition()+ "Then{TurnRight";
                    }

                    isChecked = true;
                }
                if(SaySomethingActivity.isChecked()){
                    if(isChecked){
                        Activities += " And Say'" +
                                SaySomethingCount.getText().toString() +
                                "'";
                    }else{
                        Activities += CheckIfCondition()+"Then{Say'" +
                                SaySomethingCount.getText().toString() +
                                "'";
                    }
                    Activities+="}";
                    isChecked = true;
                }
                WebDesigner.loadUrl("file:///android_asset/html/classes.html" + "?string_input=" +
                        Activities+
                        "");
            }

        });

        dialog.show();
    }
    void ShowActivitiesDialogueElse(){
        final Dialog dialog = new Dialog(Designer.this);
        dialog.setContentView(R.layout.activities_list);
        dialog.setTitle("Choose Else part");


        final CheckBox StepActivity= (CheckBox) dialog.findViewById(R.id.steps_activity);
        final CheckBox WaitActivity= (CheckBox) dialog.findViewById(R.id.wait_activity);
        final CheckBox  TurnLeftActivity= (CheckBox) dialog.findViewById(R.id.left_turn_activity);
        final CheckBox  TurnRightActivity= (CheckBox) dialog.findViewById(R.id.right_turn_activity);
        final CheckBox SaySomethingActivity= (CheckBox) dialog.findViewById(R.id.say_something_activity);

        final EditText  StepsCount= (EditText) dialog.findViewById(R.id.steps_count);
        final EditText  SecondsCount= (EditText) dialog.findViewById(R.id.seconds_count);
        final EditText SaySomethingCount= (EditText) dialog.findViewById(R.id.say_something_text);

        StepActivity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    StepsCount.setEnabled(true);
                } else {
                    StepsCount.setEnabled(false);
                }
            }
        });

        WaitActivity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SecondsCount.setEnabled(true);
                } else {
                    SecondsCount.setEnabled(false);
                }
            }
        });


        SaySomethingActivity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SaySomethingCount.setEnabled(true);
                } else {
                    SaySomethingCount.setEnabled(false);
                }
            }
        });


        Button dialogButton = (Button) dialog.findViewById(R.id.go_button);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                boolean isChecked = false;
                if(StepActivity.isChecked()){
                    Activities += "/Else{Walk" +
                            StepsCount.getText().toString() +
                            "Steps";
                    isChecked = true;

                }
                if(WaitActivity.isChecked()){
                    if(isChecked){
                        Activities += " And Wait" +
                                SecondsCount.getText().toString() +
                                "Seconds";
                    }else{
                        Activities += "/Else{Wait" +
                                SecondsCount.getText().toString() +
                                "Seconds";
                    }

                    isChecked = true;
                }
                if(TurnLeftActivity.isChecked()){
                    if(isChecked){
                        Activities += " And TurnLeft";
                    }else{
                        Activities += "/Else{TurnLeft";
                    }

                    isChecked = true;
                }
                if(TurnRightActivity.isChecked()){
                    if(isChecked){
                        Activities += " And TurnRight";
                    }else{
                        Activities += "/Else{TurnRight";
                    }

                    isChecked = true;
                }
                if(SaySomethingActivity.isChecked()){
                    if(isChecked){
                        Activities += " And Say'" +
                                SaySomethingCount.getText().toString() +
                                "'";
                    }else{
                        Activities += "/Else{Say'" +
                                SaySomethingCount.getText().toString() +
                                "'";
                    }
                    Activities+="}";
                    isChecked = true;
                }
                WebDesigner.loadUrl("file:///android_asset/html/classes.html" + "?string_input=" +
                        Activities+
                        "");
            }

        });

        dialog.show();
    }
}
