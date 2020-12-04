package com.maichu.qys.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义总中奖概率，奖品数量越少，中奖率越高
 * @author lrh
 * @date 2020-12-4
 */
public class ScratchDemo2 {
	// 中奖概率
	private final static double THRESHOLD = 0.1;
	// 礼品总数
	private final static int GIFT_NUM = 200;
	// 模拟抽奖次数
	private final static double NUM = GIFT_NUM/THRESHOLD;
	// 奖品中奖情况
	public static void scratch(){
		List<ScratchGoods> goods = new ArrayList<ScratchGoods>();
		goods.add(new ScratchGoods("一等奖",10,10));
		goods.add(new ScratchGoods("二等奖", 50, 50));
		goods.add(new ScratchGoods("三等奖", 140, 140));
		List<ScratchLucks> lucks = new ArrayList<ScratchLucks>();
		lucks.add(new ScratchLucks("一等奖",0));
		lucks.add(new ScratchLucks("二等奖",0));
		lucks.add(new ScratchLucks("三等奖",0));
		lucks.add(new ScratchLucks("没中",0));
		for (int i = 0; i < NUM; i++) {
			// 产生随机数
			double random = Math.round(Math.random() * (NUM-1));
			// 随机数发生在没中奖的范围
			if(random >= GIFT_NUM){
				System.out.println("第"+i+"次没中奖，随机数是"+random);
				lucks.get(3).setLuck(lucks.get(3).getLuck()+1);
				continue;
			}
			int cur = 0;
			for (int j = 0; j < goods.size(); j++) {
				int next = cur+goods.get(j).getTotal();
				// 随机数落在奖品的区间
				if(cur <= random && random < next){
					// 所落的区间，库存不足
					if(goods.get(j).getStock() <= 0){
						System.out.println("【无库存】第"+i+"次中奖："+goods.get(j).getName()+",随机数是"+random);
						lucks.get(3).setLuck(lucks.get(3).getLuck()+1);
						break;
					}
					// 中奖
					System.out.println("第"+i+"次中奖："+goods.get(j).getName()+",随机数是"+random);
					goods.get(j).setStock(goods.get(j).getStock()-1);
					lucks.get(j).setLuck(lucks.get(j).getLuck()+1);
					break;
				}
				cur = next;
			}
		}
		System.out.println(lucks);
	}
	public static void main(String[] args) {
		scratch();
	}
}
