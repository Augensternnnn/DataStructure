package Graph;

/**
 * 图
 */
public interface Graph<V,E> {
    int edgeSize();
    int vertexSize();

    // 添加一个顶点
    void addVertex(V v);

    // 删除一个顶点
    void removeVertex(V v);

    // 添加一条边
    void addEdge(V from, V to );
    void addEdge(V from, V to, E weight);

    // 删除一条边
    void removeEdge(V from, V to);

    /**
     * 广度优先搜索
     */
    void bfs(V begin);
}