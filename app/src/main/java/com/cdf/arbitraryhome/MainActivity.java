package com.cdf.arbitraryhome;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cdf.arbitraryhome.activities.PopupWindowActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView mExpandableListView;
    private ArrayList<Pair<String, ArrayList<String>>> mData;
    private MainExpandableListAdapter mAdapter;
    private ArrayList<ArrayList<Class<? extends Activity>>> mActivityToJump;

    class MainExpandableListAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return mData.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            if (groupPosition >= mData.size()) {
                return 0;
            }
            return mData.get(groupPosition).second.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            if (groupPosition >= mData.size()) {
                return null;
            }
            return mData.get(groupPosition).first;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            if (groupPosition >= mData.size()) {
                return null;
            }
            Pair<String, ArrayList<String>> group = mData.get(groupPosition);
            if (group == null || group.second == null) {
                return null;
            }
            return group.second.get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null) {
                TextView textView = new TextView(MainActivity.this);
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                textView.setPadding(20, 5, 5, 0);
                textView.setTextSize(20);
                convertView = textView;
            }
            ((TextView) convertView).setText(getGroup(groupPosition).toString());
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
                TextView textView = new TextView(MainActivity.this);
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                textView.setPadding(50, 5, 5, 0);
                textView.setTextSize(15);
                convertView = textView;
            }
            ((TextView) convertView).setText(getChild(groupPosition, childPosition).toString());
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mExpandableListView = (ExpandableListView) findViewById(R.id.main_expandable_list_view);
        initData();
        mAdapter = new MainExpandableListAdapter();
        mExpandableListView.setAdapter(mAdapter);
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if(groupPosition < mActivityToJump.size()) {
                    ArrayList<Class<? extends Activity>> activityInGroup = mActivityToJump.get(groupPosition);
                    if(activityInGroup != null && activityInGroup.size() > childPosition) {
                        Class<? extends Activity> activity = activityInGroup.get(childPosition);
                        if(activity != null) {
                            Intent intent = new Intent(MainActivity.this, activity);
                            startActivity(intent);
                            return true;
                        }
                    }
                }
                Toast.makeText(MainActivity.this, mAdapter.getChild(groupPosition, childPosition).toString(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void initData() {
        mData = new ArrayList<>();
        mActivityToJump = new ArrayList<>();

        // Item1:
        String parentItem = "多类型Window测试";
        ArrayList<String> subItems = new ArrayList<>();
        subItems.add("PopupWindow测试");
        subItems.add("Dialog测试");
        subItems.add("ContextMenu测试");
        ArrayList<Class<? extends Activity>> activities = new ArrayList<>();
        activities.add(PopupWindowActivity.class);

        mData.add(new Pair<String, ArrayList<String>>(parentItem, subItems));
        mActivityToJump.add(activities);

        // Item2:
        parentItem = "PlaceHolder测试";
        subItems = new ArrayList<>();
        subItems.add("测试1");
        subItems.add("测试2");
        subItems.add("测试3");
        subItems.add("测试4");

        mData.add(new Pair<String, ArrayList<String>>(parentItem, subItems));
    }
}
