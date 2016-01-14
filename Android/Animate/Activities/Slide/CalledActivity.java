/*
  Activity Example: The Activity we are going to slide into.
*/

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NextActivity extends Activity implements OnClickListener{

 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        
        Button previousActivity = (Button) findViewById(R.id.previousActivity);
        previousActivity.setOnClickListener(this);
  
    }

 public void onClick(View v) {

  switch (v.getId()) {
  case R.id.previousActivity:
   finish();
   //push from top to bottom
   overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
   //slide from left to right
   //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
   break;

   // More buttons go here (if any) ...

  }
 }

    
}
