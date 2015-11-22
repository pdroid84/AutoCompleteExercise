package com.example.android.autocompleteexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView textView, runTimeTextView;
    EditText mEditText;
    List<String> runtimeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get a reference to the AutoCompleteTextView in the layout
        textView = (AutoCompleteTextView) findViewById(R.id.static_autocomplete);
        // Get the string array from resource
        String[] citys = getResources().getStringArray(R.array.city_array);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, citys);
        //set the Threshold to 1 - min number of char to show auto complete
        textView.setThreshold(1);
        //set the Adapter
        textView.setAdapter(adapter);

        /**
         * Code for Runtime array list
         */
        runTimeTextView = (AutoCompleteTextView) findViewById(R.id.runtime_autocomplete);
        mEditText = (EditText) findViewById(R.id.runtime_editText);

        runtimeList = new ArrayList<>();
        runtimeList.add("Item 1");
        runtimeList.add("Item 2");
        runtimeList.add("Item 3");
        runtimeList.add("Item 4");
        runtimeList.add("Item 5");
        bindItemToRuntimeList();
    }

    /**
     * Make a Toast to show the selected static autocomplete text
     * @param v
     */
    public void showText (View v) {
        String staticTextMsg;
        staticTextMsg = textView.getText().toString();
        Toast.makeText(this,"The messages-> "+staticTextMsg,Toast.LENGTH_LONG).show();
    }

    /**
     * Add a new item (entered on the EditText field) to the runtime autocomplete list
     * @param v
     */
    public void addNewItem (View v) {
        String newItem = mEditText.getText().toString();
        runtimeList.add(newItem);
        bindItemToRuntimeList();
    }

    /**
     * Bind the list items to runtime autocomplete list and make a Toast once selected
     */
    private void bindItemToRuntimeList () {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,runtimeList);
        runTimeTextView.setThreshold(1);
        runTimeTextView.setAdapter(adapter);
        runTimeTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),"Item selected-> "+runtimeList.get(position),Toast.LENGTH_LONG).show();
            }
        });
    }
}
