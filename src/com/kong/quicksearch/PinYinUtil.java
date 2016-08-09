package com.kong.quicksearch;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import android.text.TextUtils;

public class PinYinUtil {
	public static String getPinyin(String chinese){
		if(TextUtils.isEmpty(chinese)) return null;
		
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);//设置转化的拼音是大写字母
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);//设置转化的拼音不带声调
		
		char[] charArray = chinese.toCharArray();
		String pinyin = "";
		for (int i = 0; i < charArray.length; i++) {
			if(Character.isWhitespace(charArray[i]))continue;
			if(charArray[i]>127){
				try {
					String[] pinyinArr = PinyinHelper.toHanyuPinyinStringArray(charArray[i],format);
					if(pinyinArr!=null){
						pinyin += pinyinArr[0];
					}else {
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			}else {
				pinyin += charArray[i];
			}
		}
		
		return pinyin;
	}
}
