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
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;
public class MainActivity extends FragmentActivity
{

    EditText name,lastname,phonenumber;
    Button load,save;
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
        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                information.setName(name.getText().toString());
                information.setLast_name(lastname.getText().toString());
                information.setPhonenumber(phonenumber.getText().toString());
                myViewModel.insert(information);
                name.setText("");
                lastname.setText("");
                phonenumber.setText("");

            }
        });


        load.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               LiveData< List <Information> > information= myViewModel.getinformation();
               name.setText(information.getValue().get(information.getValue().size()-1).getName());
               lastname.setText(information.getValue().get(information.getValue().size()-1).getLast_name());
               phonenumber.setText(information.getValue().get(information.getValue().size()-1).getPhonenumber());
            }
        });
    }
    void init ()
    {
        name=(EditText)findViewById(R.id.et_1);
        lastname=(EditText)findViewById(R.id.et_2);
        phonenumber=(EditText)findViewById(R.id.et_3);
        load=(Button)findViewById(R.id.bt_1);
        save=(Button)findViewById(R.id.bt_2);

    }

}
