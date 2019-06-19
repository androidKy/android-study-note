package com.ky.demo.adapter;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ky.demo.R;

import java.util.LinkedList;
import java.util.List;

/**
 * description: 适配器模式
 * author: kyXiao
 * created date: 2018/9/20
 */

public class AdapterActivity extends AppCompatActivity {
    private static final String TAG = "AdapterActivity";
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);

        mRecyclerView = findViewById(R.id.recyclerView);

    }


    @Override
    protected void onResume() {
        super.onResume();
        List<String> dataList = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            dataList.add("data index : " + i);
        }
        Log.i(TAG, "onResume: dataSize = " + dataList.size());
        RecyclerAdapter adapter = new RecyclerAdapter();
        adapter.setDataList(dataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(@NonNull RecyclerView.ViewHolder viewHolder) {
                Log.i(TAG, "onViewRecycled: viewType = " + viewHolder.getItemViewType());
            }
        });
    }


    private class RecyclerAdapter<VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {
        private static final int TEXT_HOLDER_TYPE = 1;
        private static final int IMAGE_HOLDER_TYPE = 2;

        private List<String> dataList;
        private Context context;

        public RecyclerAdapter() {

        }

        public RecyclerAdapter(Context context, List<String> dataList) {
            this.dataList = dataList;
            this.context = context;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            Log.i(TAG, "onCreateViewHolder: viewType = " + viewType);
            VH viewHolder = null;
            switch (viewType) {
                case TEXT_HOLDER_TYPE:
                    View view = LayoutInflater.from(AdapterActivity.this).inflate(R.layout.item_layout_text, viewGroup, false);
                    TextViewHolder textViewHolder = new TextViewHolder(view);
                    viewHolder = (VH) textViewHolder;
                    break;
                case IMAGE_HOLDER_TYPE:
                    View imgView = LayoutInflater.from(AdapterActivity.this).inflate(R.layout.item_layout_img, viewGroup, false);
                    ImageViewHolder imageViewHolder = new ImageViewHolder(imgView);
                    viewHolder = (VH) imageViewHolder;
                    break;
            }
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull VH viewHolder, int position) {
            Log.i(TAG, "onBindViewHolder: position = " + position);
            switch (viewHolder.getViewType()) {
                case TEXT_HOLDER_TYPE:
                    ((TextViewHolder) viewHolder).textView.setText(dataList.get(position));
                    break;
                case IMAGE_HOLDER_TYPE:
                    ((ImageViewHolder) viewHolder).imageView.setImageResource(R.mipmap.ic_launcher_round);
                    break;
            }
        }

        @Override
        public int getItemCount() {
            if (dataList != null)
                return dataList.size();
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            if (position % 2 == 0)
                return TEXT_HOLDER_TYPE; //双数是text
            else return IMAGE_HOLDER_TYPE;   //单数是img
        }

        public void setDataList(List<String> dataList) {
            this.dataList = dataList;
        }
    }

    class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        int getViewType() {
            return 0;
        }
    }

    class TextViewHolder extends BaseViewHolder {
        private TextView textView;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }

        @Override
        int getViewType() {
            return RecyclerAdapter.TEXT_HOLDER_TYPE;
        }
    }

    class ImageViewHolder extends BaseViewHolder {
        private ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }

        @Override
        int getViewType() {
            return RecyclerAdapter.IMAGE_HOLDER_TYPE;
        }
    }
}
