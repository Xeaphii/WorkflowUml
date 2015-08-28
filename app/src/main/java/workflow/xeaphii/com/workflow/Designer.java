package workflow.xeaphii.com.workflow;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 8/28/2015.
 */
public class Designer extends Activity {
    TextView WorkflowName;
    int ActivityId = 1;
    String StepsCountString="", SecondsCountString="", SaySomethingString="";
    Button ActivityButton, ActivitiesButton, IfButton,ElseButton,RepearButton,GenerateButton;
    boolean[] ActiviesCheck;
    private final int ActivitiesCount = 5;
    List<ActivityWorkflowPojo> WorkflowList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.designer);
        WorkflowName= (TextView) findViewById(R.id.workflow_name);
        Intent i = getIntent();
        WorkflowList = new ArrayList<ActivityWorkflowPojo>();
        WorkflowName.setText(i.getStringExtra("name"));
        ActiviesCheck = new boolean[ActivitiesCount];
        ActivityButton = (Button) findViewById(R.id.activity_button);
        ActivitiesButton = (Button) findViewById(R.id.activities_button);
        IfButton = (Button) findViewById(R.id.if_button);
        ElseButton = (Button) findViewById(R.id.else_button);
        RepearButton = (Button) findViewById(R.id.repeat_button);
        GenerateButton = (Button) findViewById(R.id.generate_button);

        ActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Designer.this);
                dialog.setContentView(R.layout.activity_list);
                dialog.setTitle("Choose any activity");
                StepsCountString=""; SecondsCountString=""; SaySomethingString="";

                final RadioButton StepActivity= (RadioButton) dialog.findViewById(R.id.steps_activity);
                final RadioButton WaitActivity= (RadioButton) dialog.findViewById(R.id.wait_activity);
                final RadioButton  TurnLeftActivity= (RadioButton) dialog.findViewById(R.id.left_turn_activity);
                final RadioButton  TurnRightActivity= (RadioButton) dialog.findViewById(R.id.right_turn_activity);
                final RadioButton SaySomethingActivity= (RadioButton) dialog.findViewById(R.id.say_something_activity);

                final EditText  StepsCount= (EditText) dialog.findViewById(R.id.steps_count);
                final EditText  SecondsCount= (EditText) dialog.findViewById(R.id.seconds_count);
                final EditText SaySomethingCount= (EditText) dialog.findViewById(R.id.say_something_text);



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
                        ActivityId = 1;
                        StepsCountString=StepsCount.getText().toString();

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
                        ActivityId = 2;
                        SecondsCountString=SecondsCount.getText().toString();
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
                        ActivityId = 3;
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
                        ActivityId = 4;
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
                        ActivityId = 5;
                        SaySomethingString=SaySomethingCount.getText().toString();
                    }
                });


                Button dialogButton = (Button) dialog.findViewById(R.id.go_button);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        DisplayMetrics metrics;
                        metrics = new DisplayMetrics();
                        getWindowManager().getDefaultDisplay().getMetrics(metrics);

                        LayoutInflater Inflater = (LayoutInflater) Designer.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View view = Inflater.inflate(R.layout.designer, null);
                        LinearLayout layout = (LinearLayout) view.findViewById(R.id.designer_layout);

                        LinearLayout LL = new LinearLayout(Designer.this);
                        LL.setOrientation(LinearLayout.HORIZONTAL);
                        LinearLayout.LayoutParams LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                        LLParams.gravity = Gravity.CENTER;
                        LLParams.setMargins(0, getDPI(20, metrics), 0, 0);
                        LL.setLayoutParams(LLParams);

                        RelativeLayout RL = new RelativeLayout(Designer.this);
                        RelativeLayout.LayoutParams RLParams = new RelativeLayout.LayoutParams(getDPI(150, metrics),getDPI(80, metrics));
                        RL.setBackgroundResource(R.mipmap.custombutton);
                        RL.setLayoutParams(RLParams);

                        Button Tag = new Button(Designer.this);
                        RelativeLayout.LayoutParams but_params = new RelativeLayout.LayoutParams(
                                RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
                        but_params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                        but_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                        Tag.setLayoutParams(but_params);
                        Tag.setText("Temp");
                        Tag.setBackgroundColor(getResources().getColor(android.R.color.transparent));





                        RL.addView(Tag);
                        LL.addView(RL);
                        layout.addView(LL);
                        Toast.makeText(getApplicationContext(),"Click",Toast.LENGTH_LONG).show();

                        ActivityWorkflowPojo TempActivity = new ActivityWorkflowPojo();
                        if(StepActivity.isChecked()){
                            TempActivity.id = 1;
                            TempActivity.ExtraString = StepsCountString;
                        }else if(WaitActivity.isChecked()){
                            TempActivity.id = 2;
                            TempActivity.ExtraString = SecondsCountString;
                        }else if(TurnLeftActivity.isChecked()){
                            TempActivity.id = 3;
                        }else if(TurnRightActivity.isChecked()){
                            TempActivity.id = 4;
                        }else if(SaySomethingActivity.isChecked()){
                            TempActivity.id =5;
                            TempActivity.ExtraString = SaySomethingString;
                        }
                        WorkflowList.add(TempActivity);
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
                StepsCountString=""; SecondsCountString=""; SaySomethingString="";

                for(int i = 0; i < ActiviesCheck.length; ++i){
                    ActiviesCheck[i] = false;
                }

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
                        if(isChecked){
                            ActiviesCheck[0] = true;
                            StepsCount.setEnabled(true);
                            StepsCountString = StepsCount.getText().toString().trim();
                        }else{
                            ActiviesCheck[0] = false;
                            StepsCount.setEnabled(false);
                        }
                    }
                });

                WaitActivity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            ActiviesCheck[1] = true;
                            SecondsCount.setEnabled(true);
                            SecondsCountString = SecondsCount.getText().toString().trim();
                        }else{
                            ActiviesCheck[1] = false;
                            SecondsCount.setEnabled(false);
                        }
                    }
                });

                TurnLeftActivity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            ActiviesCheck[2] = true;
                        }else{
                            ActiviesCheck[2] = false;
                        }
                    }
                });

                TurnRightActivity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            ActiviesCheck[3] = true;
                        }else{
                            ActiviesCheck[3] = false;
                        }
                    }
                });

                SaySomethingActivity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            ActiviesCheck[4] = true;
                            SaySomethingCount.setEnabled(true);
                            SaySomethingString = SaySomethingCount.getText().toString().trim();
                        }else{
                            ActiviesCheck[4] = false;
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

                        if(StepActivity.isChecked()){
                            ActivityWorkflowPojo TempActivity = new ActivityWorkflowPojo();
                            TempActivity.id = 1;
                            TempActivity.ExtraString = StepsCountString;
                            WorkflowList.add(TempActivity);
                        }
                        if(WaitActivity.isChecked()){
                            ActivityWorkflowPojo TempActivity = new ActivityWorkflowPojo();
                            TempActivity.id = 2;
                            TempActivity.ExtraString = SecondsCountString;
                            WorkflowList.add(TempActivity);
                        }
                        if(TurnLeftActivity.isChecked()){
                            ActivityWorkflowPojo TempActivity = new ActivityWorkflowPojo();
                            TempActivity.id = 3;
                            WorkflowList.add(TempActivity);
                        }
                        if(TurnRightActivity.isChecked()){
                            ActivityWorkflowPojo TempActivity = new ActivityWorkflowPojo();
                            TempActivity.id = 4;
                            WorkflowList.add(TempActivity);
                        }
                        if(SaySomethingActivity.isChecked()){
                            ActivityWorkflowPojo TempActivity = new ActivityWorkflowPojo();
                            TempActivity.id =5;
                            TempActivity.ExtraString = SaySomethingString;
                            WorkflowList.add(TempActivity);
                        }

                    }
                });

                dialog.show();
            }
        });
        IfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ElseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        RepearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        GenerateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    public static int getDPI(int size, DisplayMetrics metrics){
        return (size * metrics.densityDpi) / DisplayMetrics.DENSITY_DEFAULT;
    }
}
