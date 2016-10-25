package tlc.kkcc_pk.app01;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

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
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.bird48);
        builder.setTitle(titleString);
        builder.setMessage(messageString);
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
