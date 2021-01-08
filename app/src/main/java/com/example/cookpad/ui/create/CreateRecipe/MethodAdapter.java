package com.example.cookpad.ui.create.CreateRecipe;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookpad.R;
import com.example.cookpad.ui.create.AdapterItem.ItemMethod;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodAdapter extends RecyclerView.Adapter<MethodAdapter.MyViewHolder> {

    public List<ItemMethod> items;
    public Map<String,Bitmap> images = new HashMap<>();
    public Context context;
    View itemView;

    public MethodAdapter(List<ItemMethod> items,Context context,View.OnClickListener onClickListener) {
        this.items = items;
        this.context = context;
        this.mOnClickListener = onClickListener;
    }

    public void add(ItemMethod itemMethod)
    {
        items.add(itemMethod);
        notifyItemInserted(items.size() - 1);
    }

    public JSONArray getStepList()
    {
        String path = "files/recipes/";
        JSONArray step = new JSONArray();
        for(int i =0;i<items.size();i++)
        {
            JSONObject temp = new JSONObject();
            try{
                temp.put("desc",items.get(i).step);
                for (int j =0;j<3;j++)
                {
                    if(items.get(i).bitmaps.size() > j)
                    {
                        String name = "step" + (i+1) + "img"+ (j+1);
                        temp.put("img"+(j+1), path + name);
                        images.put(name,items.get(i).bitmaps.get(j));
                    }
                    else
                        temp.put("img"+(j+1),"");
                }
            }
            catch (Exception e) {}
            step.put(temp);
        }
        return step;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_method, parent, false);
        return new MyViewHolder(itemView, new MyCustomEditTextListener());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.myCustomEditTextListener.updatePosition(holder.getAdapterPosition());
        holder.number.setText(String.valueOf(Integer.parseInt(items.get(position).getNumber())+1));
        holder.step.setText(items.get(position).getStep());
        holder.recyclerImage = itemView.findViewById(R.id.rv_step_image);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.recyclerImage.setLayoutManager(mLayoutManager);
        holder.recyclerImage.setItemAnimator(new DefaultItemAnimator());
        StepImageAdapter stepImageAdapter = new StepImageAdapter(mOnClickListener);
        stepImageAdapter.setBitmaps(items.get(position).getBitmaps());
        holder.recyclerImage.setAdapter(stepImageAdapter);
    }


    protected View.OnClickListener mOnClickListener;
    public void setOnClickListenerForStepImage(View.OnClickListener onClickListenerForStepImage)
    {
        mOnClickListener = onClickListenerForStepImage;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private class MyCustomEditTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            items.get(position).setStep(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // no op
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView number;
        public EditText step;
        RecyclerView recyclerImage;
        public MyCustomEditTextListener myCustomEditTextListener;
        public MyViewHolder(View view, MyCustomEditTextListener myCustomEditTextListener) {
            super(view);
            number = (TextView) view.findViewById(R.id.tv_number);
            step = (EditText) view.findViewById(R.id.tv_step);
            this.myCustomEditTextListener = myCustomEditTextListener;
            step.addTextChangedListener(myCustomEditTextListener);
            recyclerImage = view.findViewById(R.id.rv_step_image_detail);
        }
    }
}
