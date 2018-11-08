package com.ky.plan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ky.plan.R;
import com.ky.plan.model.BaseModel;

import java.util.List;

/**
 * description:
 * author: kyXiao
 * date: 2018/11/8
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    private static final String TAG = "DataAdapter";

    private Context context;
    private List<BaseModel> dataList;

    public DataAdapter(Context context, List<BaseModel> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {

        return new DataViewHolder(LayoutInflater.from(context).inflate(R.layout.item_plan, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder viewHolder, int position) {
        if (dataList == null || dataList.size() == 0) return;
        final BaseModel baseModel = dataList.get(position);
        viewHolder.tvStartTime.setText(baseModel.getStartTime());
        viewHolder.tvTimeLength.setText(baseModel.getTimeLength());
        viewHolder.tvContent.setText(baseModel.getContent());
        viewHolder.pbProgress.setProgress(baseModel.getProgress());

        final int pos = position;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(pos, baseModel);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    static class DataViewHolder extends RecyclerView.ViewHolder {
        TextView tvStartTime;
        TextView tvTimeLength;
        TextView tvContent;
        ProgressBar pbProgress;

        DataViewHolder(View view) {
            super(view);
            tvStartTime = view.findViewById(R.id.tvStartTime);
            tvTimeLength = view.findViewById(R.id.tvTimeLength);
            tvContent = view.findViewById(R.id.tvContent);
            pbProgress = view.findViewById(R.id.pbProgress);
        }
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position, BaseModel baseModel);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
