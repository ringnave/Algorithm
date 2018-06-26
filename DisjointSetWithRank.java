package com.jiungkris.algorithm;

public class DisjointSetWithRank {

	public static final int MAX = 100;
	
	// p is parent array.
	public int[] p = new int[MAX];
	
	// rank is a height of a sub-tree. 
	private int[] rank = new int[MAX];
	
	public DisjointSetWithRank() {
		for(int i=0; i<p.length; i++) {
	    	p[i] = -1;
	    }
	}
	
	public void makeSet(int x) {
		p[x] = x;
		rank[x] = 0;
	}
	
	public void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		
		if(rank[px] > rank[py]) p[py] = px;
		else {
			p[px] = py;
			if(rank[px] == rank[py]) rank[py] = rank[py] + 1;
		}
	}
	
	public int findSet(int x) {
		if(p[x] != x) p[x] = findSet(p[x]);
		return p[x];
	}
	
}
