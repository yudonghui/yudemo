package com.ydh.yudemo.permissiontest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ydh.yudemo.R;
import com.ydh.yudemo.permissiontest.permission.Action;
import com.ydh.yudemo.permissiontest.permission.AndPermission;
import com.ydh.yudemo.permissiontest.permission.Permission;
import com.ydh.yudemo.permissiontest.permission.Rationale;

import java.util.List;

public class PermissionTestActivity extends AppCompatActivity implements View.OnClickListener {

    private Rationale mRationale;
    private PermissionSetting mSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_test);

        findViewById(R.id.btn_request_camera).setOnClickListener(this);
        findViewById(R.id.btn_request_contact).setOnClickListener(this);
        findViewById(R.id.btn_request_location).setOnClickListener(this);
        findViewById(R.id.btn_request_calendar).setOnClickListener(this);
        findViewById(R.id.btn_request_microphone).setOnClickListener(this);
        findViewById(R.id.btn_request_storage).setOnClickListener(this);
        findViewById(R.id.btn_request_phone).setOnClickListener(this);
        findViewById(R.id.btn_request_sensors).setOnClickListener(this);
        findViewById(R.id.btn_request_sms).setOnClickListener(this);

        findViewById(R.id.btn_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndPermission.permissionSetting(PermissionTestActivity.this)
                        .execute();
            }
        });

        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_request_camera:
                requestPermission(Permission.Group.CAMERA);
                break;
            case R.id.btn_request_contact:
                PopupMenu popupMenu1 = createMenu(v, getResources().getStringArray(R.array.contacts));
                popupMenu1.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int order = item.getItemId();
                        switch (order) {
                            case 0: {
                                requestPermission(Permission.READ_CONTACTS);
                                break;
                            }
                            case 1: {
                                requestPermission(Permission.WRITE_CONTACTS);
                                break;
                            }
                            case 2: {
                                requestPermission(Permission.GET_ACCOUNTS);
                                break;
                            }
                            case 3: {
                                requestPermission(Permission.Group.CONTACTS);
                                break;
                            }
                        }
                        return true;
                    }
                });
                popupMenu1.show();
                break;
            case R.id.btn_request_location:
                requestPermission(Permission.Group.LOCATION);
                break;

            case R.id.btn_request_calendar:
                PopupMenu popupMenu2 = createMenu(v, getResources().getStringArray(R.array.calendar));
                popupMenu2.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int order = item.getOrder();
                        switch (order) {
                            case 0: {
                                requestPermission(Permission.READ_CALENDAR);
                                break;
                            }
                            case 1: {
                                requestPermission(Permission.WRITE_CALENDAR);
                                break;
                            }
                            case 2: {
                                requestPermission(Permission.Group.CALENDAR);
                                break;
                            }
                        }
                        return true;
                    }
                });
                popupMenu2.show();
                break;

            case R.id.btn_request_microphone:
                requestPermission(Permission.Group.MICROPHONE);
                break;
            case R.id.btn_request_storage:
                PopupMenu popupMenu3 = createMenu(v, getResources().getStringArray(R.array.storage));
                popupMenu3.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int order = item.getOrder();
                        switch (order) {
                            case 0: {
                                requestPermission(Permission.READ_EXTERNAL_STORAGE);
                                break;
                            }
                            case 1: {
                                requestPermission(Permission.WRITE_EXTERNAL_STORAGE);
                                break;
                            }
                            case 2: {
                                requestPermission(Permission.Group.STORAGE);
                                break;
                            }
                        }
                        return true;
                    }
                });
                popupMenu3.show();
                break;
            case R.id.btn_request_phone:
                PopupMenu popupMenu4 = createMenu(v, getResources().getStringArray(R.array.phone));
                popupMenu4.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int order = item.getOrder();
                        switch (order) {
                            case 0: {
                                requestPermission(Permission.READ_PHONE_STATE);
                                break;
                            }
                            case 1: {
                                requestPermission(Permission.CALL_PHONE);
                                break;
                            }
                            case 2: {
                                requestPermission(Permission.READ_CALL_LOG);
                                break;
                            }
                            case 3: {
                                requestPermission(Permission.WRITE_CALL_LOG);
                                break;
                            }
                            case 4: {
                                requestPermission(Permission.ADD_VOICEMAIL);
                                break;
                            }
                            case 5: {
                                requestPermission(Permission.USE_SIP);
                                break;
                            }
                            case 6: {
                                requestPermission(Permission.PROCESS_OUTGOING_CALLS);
                                break;
                            }
                            case 7: {
                                requestPermission(Permission.Group.PHONE);
                                break;
                            }
                        }
                        return true;
                    }
                });
                popupMenu4.show();
                break;
            case R.id.btn_request_sensors:
                requestPermission(Permission.Group.SENSORS);
                break;
            case R.id.btn_request_sms:
                PopupMenu popupMenu5 = createMenu(v, getResources().getStringArray(R.array.sms));
                popupMenu5.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int order = item.getOrder();
                        switch (order) {
                            case 0: {
                                requestPermission(Permission.SEND_SMS);
                                break;
                            }
                            case 1: {
                                requestPermission(Permission.RECEIVE_SMS);
                                break;
                            }
                            case 2: {
                                requestPermission(Permission.READ_SMS);
                                break;
                            }
                            case 3: {
                                requestPermission(Permission.RECEIVE_WAP_PUSH);
                                break;
                            }
                            case 4: {
                                requestPermission(Permission.RECEIVE_MMS);
                                break;
                            }
                            case 5: {
                                requestPermission(Permission.Group.SMS);
                                break;
                            }
                        }
                        return true;
                    }
                });
                popupMenu5.show();
                break;
        }
    }

    private void requestPermission(String... permissions) {
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        //授权成功后
                        toast(R.string.successfully);
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(PermissionTestActivity.this, permissions)) {
                            mSetting.showSetting(permissions);
                        }
                    }
                })
                .start();
    }

    private void requestPermission(String[]... permissions) {
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        toast(R.string.successfully);
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        toast(R.string.failure);
                        if (AndPermission.hasAlwaysDeniedPermission(PermissionTestActivity.this, permissions)) {
                            mSetting.showSetting(permissions);
                        }
                    }
                })
                .start();
    }

    /**
     * Create menu.
     */
    private PopupMenu createMenu(View v, String[] menuArray) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        Menu menu = popupMenu.getMenu();
        for (int i = 0; i < menuArray.length; i++) {
            String menuText = menuArray[i];
            menu.add(0, i, i, menuText);
        }
        return popupMenu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home: {
                finish();
                break;
            }
        }
        return true;
    }

    protected void toast(@StringRes int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
