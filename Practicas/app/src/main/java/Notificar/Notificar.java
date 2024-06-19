package Notificar;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.Manifest;
import android.content.pm.PackageManager;

import static android.content.Context.NOTIFICATION_SERVICE;

import com.example.a21100298_lamichoacana.R;

public class Notificar {
    private static final String CHANNEL_ID = "canal";

    public void Notificacion(Context context,String texto,String id) {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "NEW", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        NuevaNotificacion(context,texto,id);
    }

    public void NuevaNotificacion(Context context,String texto,String id) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(com.example.a21100298_lamichoacana.R.drawable.baseline_add_alert_24)
                .setContentTitle("La Michoacana")
                .setContentText(texto + id)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.VIBRATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        managerCompat.notify(1, builder.build());
    }
}
