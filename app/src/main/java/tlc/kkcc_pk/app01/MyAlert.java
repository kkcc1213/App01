package tlc.kkcc_pk.app01;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by amonrat on 25/10/2559.
 */

public class MyAlert {
    private Context context;
    private String titleString, messageString;

    public MyAlert(Context context, String titleString, String messageString) {
        this.context = context;
        this.titleString = titleString;
        this.messageString = messageString;
    }
    public void myDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
    }
}
