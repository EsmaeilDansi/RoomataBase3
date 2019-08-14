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
//androidx ? and android library.
public class MainActivity extends FragmentActivity
{

    EditText nameEditText,lastnameEditText,phonenumberEditText;
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
                information.setName(nameEditText.getText().toString());
                information.setLast_name(lastnameEditText.getText().toString());
                information.setPhonenumber(phonenumberEditText.getText().toString());
                myViewModel.insert(information);
                nameEditText.setText("");
                lastnameEditText.setText("");
                phonenumberEditText.setText("");
            }
        });


        loadBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

               LiveData< List <Information> > informationsLiveData= myViewModel.getinformation();
               informationsLiveData.observe(MainActivity.this, new Observer<List<Information>>() {
                   @Override
                   public void onChanged(List<Information> informations) {
                       nameEditText.setText(informations.get(informations.size()-1).getName());
                       lastnameEditText.setText(informations.get(informations.size()-1).getLast_name());
                       phonenumberEditText.setText(informations.get(informations.size()-1).getPhonenumber());
                   }
               });


            }
        });
    }
    void init ()
    {
        nameEditText=(EditText)findViewById(R.id.et_1);
        lastnameEditText=(EditText)findViewById(R.id.et_2);
        phonenumberEditText=(EditText)findViewById(R.id.et_3);
        loadBtn=(Button)findViewById(R.id.bt_1);
        saveBtn=(Button)findViewById(R.id.bt_2);

    }

}
