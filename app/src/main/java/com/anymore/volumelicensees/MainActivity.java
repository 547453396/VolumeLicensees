package com.anymore.volumelicensees;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**仍然需要在清单文件中申请
         * 24项危险权限
         * CALENDAR:    READ_CALENDAR          //允许一个应用程序读取用户日历数据。
         *              WRITE_CALENDAR         //允许应用程序只写用户日历数据。
         * CAMERA:      CAMERA                  //访问摄像头设备必须具备的权限。
         * CONTACTS:    READ_CONTACTS           //允许一个应用程序读取用户联系人列表。
         *              WRITE_CONTACTS          //允许应用程序只写用户联系人数据。
         *              GET_ACCOUNTS            //允许访问账号服务的账号列表。
         * LOCATION:    ACCESS_FINE_LOCATION    //允许一个应用程序通过访问GPS等方式获取较精确的本地位置。
         *              ACCESS_COARSE_LOCATION  //允许一个应用程序通过访问CellID和WiFi热点等方式获取粗略的本地位置。
         * MICROPHONE:  RECORD_AUDIO            //允许一个应用程序录音。
         * PHONE:       READ_PHONE_STATE        //允许读取手机状态。
         *              CALL_PHONE              //允许应用程序不经过用户拨号界面而直接拨号。
         *              READ_CALL_LOG           //读取通话记录
         *              WRITE_CALL_LOG          //更改通话记录
         *              ADD_VOICEMAIL           //允许应用程序添加系统中的语音邮件
         *              USE_SIP                 //允许程序使用SIP视频服务
         *              PROCESS_OUTGOING_CALLS  //允许应用程序监视、修改、忽略拨出的电话
         * SENSORS:     BODY_SENSORS            //传感器
         * SMS:         SEND_SMS                //允许应用程序发送短消息（SMS）。
         *              RECEIVE_SMS             //允许一个应用程序监控收到的短信（SMS），记录或处理之。
         *              READ_SMS                //允许一个应用程序读取手机短消息。
         *              RECEIVE_WAP_PUSH        //允许一个应用程序监测接受的WAP-PSUH消息。
         *              RECEIVE_MMS             //允许一个应用程序监控收到的彩信（MMS），记录或处理之。
         * STORAGE:     READ_EXTERNAL_STORAGE   //读取外置存储
         *              WRITE_EXTRNAL_STORAGE   //只写外置存储
         */
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.CAMERA);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        } else {
            //执行自己需要的任务
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            //执行相应的逻辑
                            Toast.makeText(this, "需要授权", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                }
                break;
            default:
        }


    }
}
