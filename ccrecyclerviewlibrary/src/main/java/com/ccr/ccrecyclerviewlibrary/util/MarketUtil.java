package com.ccr.ccrecyclerviewlibrary.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

/**
 * @Description 应用商店评分工具类
 * @auth zhangdm
 * @date 2016/2/16 10:22
 */
public class MarketUtil {
    /**
     * 启动到app详情界面
     *
     * @param appPkg App的包名
     */
    public static void launchRateApp(Context context, String appPkg) {
        try {
            if (TextUtils.isEmpty(appPkg))
                return;
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
