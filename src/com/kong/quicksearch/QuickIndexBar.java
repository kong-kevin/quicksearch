package com.kong.quicksearch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class QuickIndexBar extends View{
	private String[] indexArr = { "A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z" };
	private Paint paint;
	private int width;
	private float cellHeight;
	public QuickIndexBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public QuickIndexBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public QuickIndexBar(Context context) {
		super(context);
		init();
	}
	
	private void init(){
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.WHITE);
		paint.setTextSize(16);
		paint.setTextAlign(Align.CENTER);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		width = getMeasuredWidth();
		cellHeight = getMeasuredHeight()*1f/indexArr.length;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for (int i = 0; i < indexArr.length; i++) {
			float x = width/2;
			float y = cellHeight/2 + getTextHeight(indexArr[i])/2 + i*cellHeight;
			
			paint.setColor(lastIndex==i?Color.BLACK:Color.WHITE);
			
			canvas.drawText(indexArr[i], x, y, paint);
		}
	}
	private int lastIndex = -1;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			float y = event.getY();
			int index = (int) (y/cellHeight);
			if(lastIndex!=index){
				if(index>=0 && index<indexArr.length){
					if(listener!=null){
						listener.onTouchLetter(indexArr[index]);
					}
				}
			}
			lastIndex = index;
			break;
		case MotionEvent.ACTION_UP:
			lastIndex = -1;
			break;
		}
		invalidate();
		return true;
	}

	/**
	 * 获取文本的高度
	 * @param text
	 * @return
	 */
	private int getTextHeight(String text) {
		Rect bounds = new Rect();
		paint.getTextBounds(text,0,text.length(), bounds);
		return bounds.height();
	}
	
	private OnTouchLetterListener listener;
	public void setOnTouchLetterListener(OnTouchLetterListener listener){
		this.listener = listener;
	}
	/**
	 * 触摸字母的监听器
	 * @author Administrator
	 *
	 */
	public interface OnTouchLetterListener{
		void onTouchLetter(String letter);
	}

}
