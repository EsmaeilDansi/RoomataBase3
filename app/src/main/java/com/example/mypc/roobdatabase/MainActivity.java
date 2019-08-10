package com.example.mypc.roobdatabase;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.net.Socket;
import java.util.List;
import androidx.room.Room;
public class MainActivity extends AppCompatActivity
{
    MyDatabase myDatabase;
    Socket d=new Socket();
    EditText name,lastname,phonenumber;
    Button load,save;
    final Information information=new Information("","","");
    private MyViewModel myViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myViewModel= ViewModelProviders.of(this).get(MyViewModel.class);
        myViewModel.getinformation().observe(this,information->
        {
            //

        });

        init();
        myDatabase= Room.databaseBuilder(getApplicationContext(),MyDatabase.class,"mydata.db").allowMainThreadQueries().addMigrations().build();

        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                information.setName(name.getText().toString());
                information.setLast_name(lastname.getText().toString());
                information.setPhonenumber(phonenumber.getText().toString());
                myDatabase.myDao().insert(information);
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
                List <Information> information=myDatabase.myDao().getinformation();
                for(Information inf :information)
                {
                    name.setText(inf.getName());
                    lastname.setText(inf.getLast_name());
                    phonenumber.setText(inf.getPhonenumber());
                    Toast.makeText(getApplicationContext(),""+inf.getId(),Toast.LENGTH_LONG).show();
                }

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
