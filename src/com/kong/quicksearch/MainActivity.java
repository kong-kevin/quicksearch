package com.kong.quicksearch;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.OvershootInterpolator;
import android.widget.ListView;
import android.widget.TextView;

import com.kong.quicksearch.QuickIndexBar.OnTouchLetterListener;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

public class MainActivity extends Activity {
	private QuickIndexBar quickIndexBar;
	private ListView listview;
	private TextView currentWord;
	
	private ArrayList<Friend> friends = new ArrayList<Friend>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		quickIndexBar = (QuickIndexBar) findViewById(R.id.quickIndexBar);
		listview = (ListView) findViewById(R.id.listview);
		currentWord = (TextView) findViewById(R.id.currentWord);
		
		fillList();
		Collections.sort(friends);
		listview.setAdapter(new MyAdapter(this,friends));
		
		quickIndexBar.setOnTouchLetterListener(new OnTouchLetterListener() {
			@Override
			public void onTouchLetter(String letter) {
				for (int i = 0; i < friends.size(); i++) {
					String firstWord = friends.get(i).getPinyin().charAt(0)+"";
					if(letter.equals(firstWord)){
						listview.setSelection(i);
						break;
					}
				}
				
				showCurrentWord(letter);
			}
		});
		
		
		ViewHelper.setScaleX(currentWord, 0);
		ViewHelper.setScaleY(currentWord, 0);
		
	}
	private boolean isScale = false;
	private Handler handler = new Handler();
	protected void showCurrentWord(String letter) {
		currentWord.setText(letter);
		if(!isScale){
			isScale = true;
			ViewPropertyAnimator.animate(currentWord).scaleX(1f)
			.setInterpolator(new OvershootInterpolator())
			.setDuration(450).start();
			ViewPropertyAnimator.animate(currentWord).scaleY(1f)
			.setInterpolator(new OvershootInterpolator())
			.setDuration(450).start();
		}
		
		handler.removeCallbacksAndMessages(null);
		
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				ViewPropertyAnimator.animate(currentWord).scaleX(0f).setDuration(450).start();
				ViewPropertyAnimator.animate(currentWord).scaleY(0f).setDuration(450).start();
				isScale = false;
			}
		}, 1500);
	}

	private void fillList() {
		friends.add(new Friend("李伟"));
		friends.add(new Friend("BBBBBB"));
		friends.add(new Friend("EEEEEE"));
		friends.add(new Friend("FFFFFF"));
		friends.add(new Friend("GGGGGG"));
		friends.add(new Friend("HHHHHH"));
		friends.add(new Friend("IIIIII"));
		friends.add(new Friend("JJJJJJ"));
		friends.add(new Friend("KKKKKK"));
		friends.add(new Friend("MMMMMM"));
		friends.add(new Friend("NNNNNN"));
		friends.add(new Friend("OOOOOO"));
		friends.add(new Friend("PPPPPP"));
		friends.add(new Friend("QQQQQQ"));
		friends.add(new Friend("RRRRRR"));
		friends.add(new Friend("TTTTTT"));
		friends.add(new Friend("UUUUUU"));
		friends.add(new Friend("VVVVVV"));
		friends.add(new Friend("XXXXXX"));
		friends.add(new Friend("阿三"));
		friends.add(new Friend("阿四"));
		friends.add(new Friend("段誉"));
		friends.add(new Friend("段正淳"));
		friends.add(new Friend("张三丰"));
		friends.add(new Friend("陈坤"));
		friends.add(new Friend("林杰"));
		friends.add(new Friend("陈坤坤"));
		friends.add(new Friend("王二小"));
		friends.add(new Friend("林俊"));
		friends.add(new Friend("张四"));
		friends.add(new Friend("林俊杰"));
		friends.add(new Friend("王二"));
		friends.add(new Friend("张三三"));
		friends.add(new Friend("赵四"));
		friends.add(new Friend("杨坤"));
		friends.add(new Friend("赵子龙"));
		friends.add(new Friend("杨坤坤"));
		friends.add(new Friend("李伟伟"));
		friends.add(new Friend("宋江"));
		friends.add(new Friend("宋江江"));
		friends.add(new Friend("李伟伟伟"));
	}

}
