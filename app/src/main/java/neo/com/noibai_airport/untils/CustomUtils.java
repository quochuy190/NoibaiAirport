package neo.com.noibai_airport.untils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.ArrayList;

import neo.com.noibai_airport.R;
import neo.com.noibai_airport.View.Activity.MainActivity.MainActivity;

/**
 * Created by QQ on 7/17/2017.
 */

public class CustomUtils {
    public static String repace_number(String sString){
        for (int i = 0;i<10;i++){

        }
        return null;
    }

    public static String conventNumber(String hit) {
        String result;
        double iInt = 0;
        double iNumber = Double.parseDouble(hit);
        if (iNumber <= 999) {
            int i = (int) iNumber;
            return "" + i;
        } else if (iNumber > 999 && iNumber < 1000000) {
            iInt = iNumber / 1000;
            double roundOff = (double) Math.round(iInt * 100) / 100;
            result = "" + roundOff + " N";
            return result;
        } else if (iNumber >= 1000000) {
            iInt = iNumber / 1000000;
            double roundOff = (double) Math.round(iInt * 100) / 100;
            result = "" + roundOff + " Tr";
            return result;
        }
        return null;
    }

    public static ArrayList<PhoneContactModel> getAllPhoneContacts(Context context) {

        ArrayList<PhoneContactModel> result = new ArrayList<PhoneContactModel>();
        Cursor phones = context.getContentResolver().query(ContactsContract.
                CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            //  String img = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.))
            phoneNumber = PhoneNumber.convertToVnPhoneNumber(phoneNumber);
            result.add(new PhoneContactModel(name, phoneNumber, ""));
        }
        phones.close();
        return result;
    }


    /**
     * Create and show a simple notification containing the DELIVERED GCM message.
     *
     * @param message GCM message DELIVERED.
     */
    public static void sendNotification(String message, Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Log.e("ChatActivity", "Nhận Notify");
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
/*        Uri defaultSoundUri = Uri.parse("android.resource://"
                + context.getPackageName() + "/" + R.raw.jingle_bells_sms);*/
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(message)
                .setAutoCancel(true)
                // .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}
