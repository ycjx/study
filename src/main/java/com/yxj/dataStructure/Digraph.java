package com.yxj.dataStructure;

/**
 * @author:yuxj
 * @descriptio 有向图
 * @create:2019-04-05 13:43
 */
public class Digraph {

    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    Digraph(int V) {
        this.V = V;
        adj = new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    /**
     * 顶点总数
     *
     * @return
     */
    public int V() {
        return V;
    }

    /**
     * 边的总数
     *
     * @return
     */
    public int E() {
        return E;
    }

    /**
     * 添加一条v->w 边
     *
     * @param v
     * @param w
     */
    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    /**
     * 由v指出的边所连接的所有的顶点
     *
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * 该图的反向图
     *
     * @return
     */
    Digraph reverse() {
        Digraph r = new Digraph(V);
        for(int v=0;v<V;v++){
            for(int w:adj[v]){
                r.addEdge(w,v);
            }
        }
        return r;
    }


}
