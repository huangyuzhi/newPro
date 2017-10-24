package com.chains.pwqxfwjk.other;

import java.util.Comparator;

import org.apache.log4j.Logger;

import com.chains.pwqxfwjk.model.LineInfoFor10kV;

/**
 * 类名称:Line10kvComparator<br>
 * 功能描述: 只比较相同分支的线路的大小                     <br>
 * <br>
 * 创建人:zw<br>
 * 创建时间:2015年11月23日 上午11:30:22<br>
 * 修改人:zw<br>
 * 修改时间:2015年11月23日 上午11:30:22<br>
 * 修改备注:               
 * 
 * @version 1.0.0
 */
public class Line10kvComparator implements  Comparator<TowerTree.TowerNode>{
	protected final Logger logger = Logger.getLogger(this.getClass());
	
/*	public static void main(String[] args) {
		//模拟测试
		String towerNumber = "1";
		String[] towers = towerNumber.split("-");
		System.out.println(towers[0]);
	}*/
	public int compare(TowerTree.TowerNode t1, TowerTree.TowerNode t2) {
		LineInfoFor10kV o1 = t1.getTower();
		LineInfoFor10kV o2 = t2.getTower();
		String o1towerNumber = o1.getTowerNumber();
		String[] o1Towers = o1towerNumber.split("-");
		String o2towerNumber = o2.getTowerNumber();
		String[] o2Towers = o2towerNumber.split("-");
		int minLength = o1Towers.length > o2Towers.length ? o2Towers.length : o1Towers.length;//取o1和o2中数组长度较小的那个
		for(int i = 0; i < minLength; i++) {
			if(o1Towers[i].matches("\\d+") && o2Towers[i].matches("\\d+")) {	//假设字符都是数字
				if(Integer.parseInt(o1Towers[i]) > Integer.parseInt(o2Towers[i])) {
					return 1;
				}else if(Integer.parseInt(o1Towers[i]) < Integer.parseInt(o2Towers[i])) {
					return -1;
				}
			}/*else if(o1Towers[i].matches("\\d+") || o2Towers[i].matches("\\d+")) {	//只有一边为数字
				throw new RuntimeException("只有一边为数字，无法比较");
			}*/else {	
				int result = o1Towers[i].compareTo(o2Towers[i]);
				if(result != 0) {
					return result;
				}
			}
		}
		throw new RuntimeException("未预知的错误");
	}
}
