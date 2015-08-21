package com.treslines.pagsegurodemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * Use this class to create alert dialogs on the fly, redirect to google play store and so on...<br/>
 * <br/>Author: Ricardo Ferreira, 8/13/15
 */
public final class AppUtil {

    /**
     * Shows a confirm dialog with a custom message and custom quit button text.
     * If you pass null to btnTxt, "ok" will be shown per default.
     *
     * @param context the apps context This value can't be null.
     * @param msg     the message to be shown. his value can't be null or empty.
     * @param btnTxt  the text to be shown in the quit button. For example "close"
     */
    public static void showConfirmDialog(@NonNull final Context context, @NonNull final String msg, final String btnTxt) {
        if (msg.isEmpty()) {
            throw new IllegalArgumentException("This value can't be empty!");
        }
        final AlertDialog dialog = new AlertDialog.Builder(context).setMessage(msg)
                .setPositiveButton((btnTxt == null ? "Ok" : btnTxt), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    /**
     * Shows a confirm dialog with a custom message, custom buttons and custom actions.
     *
     * @param ctx                the apps context. This value can't be null.
     * @param msg                the message to be shown This value can't be null or empty.
     * @param okBtn              the text to be shown in the ok button. If you pass null, ok will be shown per default
     * @param cancelBtn          the text to be shown in the cancel button. If you pass null, cancel will be shown per default
     * @param okAction           the action to be performed as soon as the user presses the ok button. if you pass null, nothing happens.
     * @param cancelAction       the action to be performed as soon as the user presses the cancel button. if you pass null, nothing happens.
     * @param backPressendAction the action to be performed as soon as the user presses the system back button. if you pass null, nothing happens.
     */
    public static void showOkCancelDialog(@NonNull final Context ctx, @NonNull final String msg, final String okBtn, final String cancelBtn, final AlertAction okAction, final AlertAction cancelAction, final AlertAction backPressendAction) {
        if (msg.isEmpty()) {
            throw new IllegalArgumentException("This value can't be empty!");
        }
        final AlertDialog dialog = new AlertDialog.Builder(ctx).setMessage(msg)
                .setPositiveButton((okBtn == null ? "Ok" : okBtn), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (okAction != null) {
                            okAction.perform();
                        }
                    }
                }).setNegativeButton((cancelBtn == null ? "Cancel" : cancelBtn), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (cancelAction != null) {
                            cancelAction.perform();
                        }
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        dialog.dismiss();
                        if (backPressendAction != null) {
                            backPressendAction.perform();
                        }
                    }
                }).create();
        dialog.show();
    }

    public interface AlertAction {
        void perform();
    }

    /**
     * Use this method to check if a third part app is istalled or not
     * @param ctx the app's context
     * @param packageName the third part app's package name. something like: com.example.package
     * @return true if the app is installed.
     */
    public static boolean isAppInstalled(final Context ctx, final String packageName) {
        PackageManager pm = ctx.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

    /**
     *  Use this method to open the google play store
     * @param activity the app which wants to redirect to the google play store
     * @param googlePlayStoreId the third part app's package name. something like: com.example.package
     * @param requestCode the request code to be used in the method onActivityForResult in the app which called this method.
     */
    public static void navigateToGooglePlayStore(final Activity activity, final String googlePlayStoreId, final int requestCode) {
        try {
            activity.startActivityForResult(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + googlePlayStoreId)), requestCode);
        } catch (android.content.ActivityNotFoundException anfe) {
            // last chance: try again a second time with a different approach
            activity.startActivityForResult(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + googlePlayStoreId)), requestCode);
        }
    }

}
