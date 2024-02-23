package com.example.a21110939;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

public class InputControlActivity extends AppCompatActivity {

    Button btnRun;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_custom,menu);
        menu.setHeaderTitle("Context Menu");
        menu.setHeaderIcon(R.mipmap.ic_launcher);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_control);

        Switch wifiSwitch = (Switch) findViewById(R.id.switch1);
        ConstraintLayout bg = (ConstraintLayout) findViewById(R.id.constraintLayout2);

        wifiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    Toast.makeText(InputControlActivity.this, "Wifi On", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(InputControlActivity.this, "Wifi Off", Toast.LENGTH_LONG).show();
                }
            }
        });

        changeBackground(bg);

        handleProgressBar();

        handleCheckBox(bg);

        handleSeekBar();

        handleOpenMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_custom,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void changeBackground(ConstraintLayout bg) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(R.id.radioButton1 == i) {
                    bg.setBackgroundResource(R.drawable.tao_0001);
                } else if(R.id.radioButton2 == i) {
                    bg.setBackgroundResource(R.drawable.tao_0050);
                }
            }
        });
    }

    private void handleProgressBar() {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        btnRun = (Button) findViewById(R.id.button);
        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int curr = progressBar.getProgress();
//                if(curr >= progressBar.getMax()) {
//                    curr = 0;
//                }
//                progressBar.setProgress(curr + 10);
                CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long l) {
                        int curr = progressBar.getProgress();

                        if(curr >= progressBar.getMax()) {
                            curr = 0;
                        }
                        progressBar.setProgress(curr + 10);
                    }
                    @Override
                    public void onFinish() {
                        Toast.makeText(InputControlActivity.this, "Time out", Toast.LENGTH_LONG).show();
                    }
                };
                countDownTimer.start();
            }
        });
    }

    private void handleCheckBox(ConstraintLayout bg) {
        CheckBox chk = (CheckBox) findViewById(R.id.checkBox);
        chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    bg.setBackgroundResource(R.drawable.tao_0034);
                }
            }
        });

    }

    private void handleSeekBar() {
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.d("AAA", "Value: " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("AAA", "Start");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("AAA", "Stop");
            }
        });
    }

    private void handleOpenMenu() {
        Button menuBtn = (Button) findViewById(R.id.menuBtn);
        registerForContextMenu(menuBtn);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(InputControlActivity.this,menuBtn);
                popupMenu.getMenuInflater().inflate(R.menu.menu_custom,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int itemId = item.getItemId();
                        if (itemId == R.id.menuSetting) {
                            Toast.makeText(InputControlActivity.this, "Bạn đang chọn Setting", Toast.LENGTH_LONG).show();
                            return true;
                        } else if (itemId == R.id.menuShare) {
                            menuBtn.setText("Chia sẻ");
                            return true;
                        } else if (itemId == R.id.menuLogout) {
                            // Handle logout functionality here
                            return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }
}