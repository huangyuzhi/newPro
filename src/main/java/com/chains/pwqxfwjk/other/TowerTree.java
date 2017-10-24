package com.chains.pwqxfwjk.other;

import java.util.SortedSet;
import java.util.TreeSet;

import com.chains.pwqxfwjk.model.LineInfoFor10kV;

public class TowerTree {
	private TowerNode root;
	private int count;	//节点总数，不算根节点
	public TowerTree() {
		root = new TowerNode();
	}
	public void pushNode(LineInfoFor10kV tower) {
		TowerNode node = new TowerNode(tower);
		if(root.getTowerSet().size() == 0) {	//一个节点也没有
			root.getTowerSet().add(node);
		}else {
			
		}
	}
	public static class TowerNode {
		private String branchName;	//支路名，叶节点为null，跟节点为主线路的名称
		private LineInfoFor10kV tower;
		private SortedSet<TowerNode> towerSet = new TreeSet<TowerNode>(new Line10kvComparator());
		
		public TowerNode() {
		}
		
		public TowerNode(LineInfoFor10kV tower) {
			this.tower = tower;
		}
		/**
		 * @since branchName
		 * @return String
		 */
		
		public String getBranchName() {
			return branchName;
		}
		/**
		 * @since branchName
		 * @param branchName
		 * @return void
		 */
		
		public void setBranchName(String branchName) {
			this.branchName = branchName;
		}
		/**
		 * @since tower
		 * @return LineInfoFor10kV
		 */
		
		public LineInfoFor10kV getTower() {
			return tower;
		}
		/**
		 * @since tower
		 * @param tower
		 * @return void
		 */
		
		public void setTower(LineInfoFor10kV tower) {
			this.tower = tower;
		}
		/**
		 * @since towerSet
		 * @return SortedSet<TowerNode>
		 */
		
		public SortedSet<TowerNode> getTowerSet() {
			return towerSet;
		}
		/**
		 * @since towerSet
		 * @param towerSet
		 * @return void
		 */
		
		public void setTowerSet(SortedSet<TowerNode> towerSet) {
			this.towerSet = towerSet;
		}
	}
	
	public void insert(TowerNode subTree,TowerNode insertNode) {
		if(subTree.getTowerSet().size() == 0) {
			//do nothing
		}else {
			//递归遍历subTree
			for(TowerNode cNode : subTree.getTowerSet()) {
				String towerNumber = cNode.getTower().getTowerNumber();
				int towerdepth = towerNumber.split("-").length;
				int inserNodeDepth = insertNode.getTower().getTowerNumber().split("-").length;
					
			}
		}
	}
}
