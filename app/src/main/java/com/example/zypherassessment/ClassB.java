package com.example.zypherassessment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.ActionMode;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MotionEventCompat;

import com.github.andreilisun.swipedismissdialog.OnCancelListener;
import com.github.andreilisun.swipedismissdialog.OnSwipeDismissListener;
import com.github.andreilisun.swipedismissdialog.SwipeDismissDialog;
import com.github.andreilisun.swipedismissdialog.SwipeDismissDirection;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.squareup.picasso.Picasso;

public class ClassB {

    public static Dialog dialog;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    public static SwipeDismissDialog swipeDismissDialog;
    public static void ShowDialogBox(String title, String image_url, String successUrl, Context context)
    {
       dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_white);
        dialog.setContentView(R.layout.dialog_test_data);
        dialog.setCancelable(false);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.horizontalMargin = 10.0f;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;

        dialog.getWindow().setAttributes(lp);
        dialog.setTitle(title);

        // set the custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.tv_titleTxt);
        text.setText(title);
        ImageView image = (ImageView) dialog.findViewById(R.id.iv_testData);
        Picasso.get().load(image_url).fit().placeholder(context.getDrawable(R.mipmap.ic_launcher)).into(image);
        //image.setImageResource(R.drawable.ic_launcher);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_success);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(successUrl == null || successUrl == "")
                {
                    Toast.makeText(context,"Invalid Success url: "+ successUrl,Toast.LENGTH_SHORT).show();
                }
                try
                {

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(successUrl));
                    context.startActivity(browserIntent);
                }
                catch (Exception e)
                {
                    Toast.makeText(context,"Invalid Success url: "+ successUrl,Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();

    }


    public static void ShowDialogBox1(String title, String image_url, String successUrl, Context context)
    {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        //lp.copyFrom((WindowManager.LayoutParams)swipeDismissDialog.getLayoutParams());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;

        View dialog = LayoutInflater.from(context).inflate(R.layout.dialog_test_data, null);
        swipeDismissDialog = new SwipeDismissDialog.Builder(context)
                .setView(dialog)

                .setOnSwipeDismissListener(new OnSwipeDismissListener() {
                    @Override
                    public void onSwipeDismiss(View view, SwipeDismissDirection direction) {
                    }
                })
                .setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel(View view) {
                        //showDia
                        this.showAgain();
                    }

                    private void showAgain() {
                        ShowDialogBox1(title, image_url,successUrl,context);
                        Toast.makeText(context,"Swipe right or left to dismiss the dialog",Toast.LENGTH_SHORT).show();
                    }
                })

                .build();
        swipeDismissDialog.setLayoutParams(lp);swipeDismissDialog.show();
        TextView text = (TextView) swipeDismissDialog.findViewById(R.id.tv_titleTxt);
        text.setText(title);
        ImageView image = (ImageView) swipeDismissDialog.findViewById(R.id.iv_testData);
        Picasso.get().load(image_url).fit().placeholder(context.getDrawable(R.mipmap.ic_launcher)).into(image);

        Button dialogButton = (Button) swipeDismissDialog.findViewById(R.id.btn_success);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(successUrl == null || successUrl == "")
                {
                    Toast.makeText(context,"Invalid Success url: "+ successUrl,Toast.LENGTH_SHORT).show();
                }
                try
                {

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(successUrl));
                    context.startActivity(browserIntent);
                }
                catch (Exception e)
                {
                    Toast.makeText(context,"Invalid Success url: "+ successUrl,Toast.LENGTH_SHORT).show();
                }
            }
        });

    }




}
