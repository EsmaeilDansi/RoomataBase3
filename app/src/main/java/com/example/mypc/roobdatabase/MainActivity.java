package com.example.mypc.roobdatabase;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.net.Socket;
import java.util.List;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;
public class MainActivity extends FragmentActivity
{

    EditText nameEdittext,lastnameEdittext,phonenumberEdittext;
    Button loadBtn,saveBtn;
    final Information information=new Information("","","");
    private MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        myViewModel= ViewModelProviders.of(this).get(MyViewModel.class);
        myViewModel.getinformation().observe(this,information->
        {
            //
            //
        });
        saveBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                information.setName(nameEdittext.getText().toString());
                information.setLast_name(lastnameEdittext.getText().toString());
                information.setPhonenumber(phonenumberEdittext.getText().toString());
                myViewModel.insert(information);
                nameEdittext.setText("");
                lastnameEdittext.setText("");
                phonenumberEdittext.setText("");

            }
        });


        loadBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               LiveData< List <Information> > information= myViewModel.getinformation();
               information.observe(MainActivity.this, new Observer<List<Information>>() {
                   @Override
                   public void onChanged(List<Information> information) {
                       nameEdittext.setText(information.get(information.size()-1).getName());
                       lastnameEdittext.setText(information.get(information.size()-1).getLast_name());
                       phonenumberEdittext.setText(information.get(information.size()-1).getPhonenumber());
                   }
               });

            }
        });
    }
    void init ()
    {
        nameEdittext=(EditText)findViewById(R.id.et_1);
        lastnameEdittext=(EditText)findViewById(R.id.et_2);
        phonenumberEdittext=(EditText)findViewById(R.id.et_3);
        loadBtn=(Button)findViewById(R.id.bt_1);
        saveBtn=(Button)findViewById(R.id.bt_2);

    }

}
