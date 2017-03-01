package mapswebservice.cris_.mapsexample;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

/**
 * Created by cris_ on 28/02/2017.
 */
public class Pop extends Activity{
    private TextView _txtName;
    private TextView _txtDesc;
    private String name,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int heigth = dm.heightPixels;

        getWindow().setLayout((int) (width*.8),(int) (heigth*.6));

        loadData();
        beginComponents();
    }

    private void beginComponents() {
        _txtName = (TextView) findViewById(R.id.txtPopTitle);
        _txtDesc = (TextView) findViewById(R.id.txtPopDesc);
        _txtName.setText(name);
        _txtDesc.setText(desc);
    }

    private void loadData() {
        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            name = extras.getString("name");
            desc = extras.getString("desc");
        }
    }
}
