package com.ccr.ccrecyclerviewlibrary.util;

import android.content.Context;

import com.ccr.ccrecyclerviewlibrary.R;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * 地址解析，将id值解析为对应的省市
 *
 * @author zhangyb@ifenguo.com
 * @createDate 2015年3月10日
 */
public class AddressParse {

    private static List<AreaNode> list;

    public static void init(Context context) {
        if (list == null) {
            String json = context.getString(R.string.address);
            list = JsonUtil.fromJson(json, new TypeToken<List<AreaNode>>() {
            });
        }
    }

    public static List<AreaNode> getInstance() {
        if (list == null) {
            throw new IllegalStateException(AddressParse.class.getSimpleName()
                    + " is not initialized, call getInstance(..) method first.");
        }
        return list;
    }

    /**
     * @param @param  id
     * @param @param  allArea
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: parseAddress
     * @Description: 根据ID值，显示对应的省市
     */
    public static String parseAddress(long id, List<AreaNode> allArea) {
        StringBuffer area = new StringBuffer();
        int position;
        position = (int) (id / 1000000) - 1;
        area.append(allArea.get(position).getName());
        area.append("-");
        List<AreaNode> node = allArea.get(position).getChildren();
        position = (int) (id % 1000000 / 1000) - 1;
        area.append(node.get(position).getName());
        return area.toString();
    }

    /**
     * @param @param  id
     * @param @param  allArea
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: getCity
     * @Description: 根据ID值，显示对应的城市
     */
    public static String getCity(long id, List<AreaNode> allArea) {
        String area = "";
        int position;
        position = (int) (id / 1000000) - 1;
        List<AreaNode> node = allArea.get(position).getChildren();
        position = (int) (id % 1000000 / 1000) - 1;
        area = node.get(position).getName();
        return area;
    }

}
