package com.geekym.care4u.introduction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.geekym.care4u.R;

public class SliderAdapter extends PagerAdapter {
    //Declaring Variables
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    int images[] = {   //Images to be displayed
            R.drawable.slide1,
            R.drawable.slide2,
            R.drawable.slide3
    };

    int headings[] ={   //Heading Lines of Slides

            R.string.first_slide_title,
            R.string.second_slide_title,
            R.string.third_slide_title
    };

    int description[] = {       //Description of Slides

            R.string.first_slide_desc,
            R.string.second_slide_dec,
            R.string.third_slide_dec
    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slides_layout,container,false);
        ImageView imageView = view.findViewById(R.id.slider_image);
        TextView heading = view.findViewById(R.id.slider_heading);
        TextView desc = view.findViewById(R.id.slider_des);
        imageView.setImageResource(images[position]);
        heading.setText(headings[position]);
        desc.setText(description[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
