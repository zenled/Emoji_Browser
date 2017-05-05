package com.kukumoraketo.emojibrowser.EmojiDisplay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.kukumoraketo.emojibrowser.Emoji.Emoji.EmojiLite;
import com.kukumoraketo.emojibrowser.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zed on 1.5.2017.
 */

public class EmojiDisplayAdapter extends BaseAdapter {

    private List<EmojiLite> myEmoji;
    private Context context;



    public EmojiDisplayAdapter(List<EmojiLite> emoji, Context context){

        this.myEmoji = emoji;
        this.context = context;
    }


    @Override
    public int getCount() {
        return this.myEmoji.size();
    }

    @Override
    public Object getItem(int position) {
        return this.myEmoji.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.emoji_display_single, parent, false);
        }

        EmojiLite currentEmoji = (EmojiLite) getItem(position);

        // sets image
        ImageView imageView = (ImageView) convertView.findViewById(R.id.emojiDisplaySingle_ImageView);
        int image_id = currentEmoji.getDravableId(context);
        Picasso.with(context).load(image_id).resize(256,256).placeholder(R.drawable.em_2754).into(imageView);

        return convertView;
    }
}
