package Graph;

import java.util.*;

public class ListGraph<V,E> implements Graph<V,E> {
    private Map<V, Vertex<V,E>> vertices = new HashMap<>();
    private Set<Edge<V,E>> edges = new HashSet<>();

    private static class Vertex<V,E> {
        V value;
        Set<Edge<V,E>> inEdges = new HashSet<>();
        Set<Edge<V,E>> outEdges = new HashSet<>();

        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            return Objects.equals(value, ((Vertex<V,E>)o).value);
        }

        @Override
        public int hashCode() {
            return value == null ? 0 : value.hashCode();
        }
    }

    private static class Edge<V,E>  {
        Vertex<V,E> from;
        Vertex<V,E> to;
        E weight;

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            Edge<V, E> edge = (Edge<V, E>) o;
            return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            return from.hashCode() * 31 + to.hashCode();
        }
    }

    @Override
    public int edgeSize() {
        return edges.size();
    }

    @Override
    public int vertexSize() {
        return vertices.size();
    }

    @Override
    public void addVertex(V v) {
        if(vertices.containsKey(v))
            return;
        vertices.put(v, new Vertex<>(v));
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E weight)    {
        // 判断from、to顶点是否存在
        Vertex<V, E> fromVertex = vertices.get(from);
        if(fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }
        Vertex<V, E> toVertex = vertices.get(to);
        if(toVertex == null){
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }

        Edge<V,E> edge = new Edge<>(fromVertex, toVertex);
        edge.weight = weight;
        // 不管之前有没有这条边都删除，再重新添加（避免分情况处理）
        if(fromVertex.outEdges.remove(edge)){
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);
    }

    @Override
    public void removeVertex(V v) {
        Vertex<V, E> vertex = vertices.remove(v);
        if(vertex == null)
            return;
        for(Iterator<Edge<V,E>> iterator = vertex.outEdges.iterator(); iterator.hasNext();){
            Edge<V,E> edge = iterator.next();
            edge.to.inEdges.remove(edge);
            // 将当前遍历到的元素edge从集合vertex.outEdges中删掉
            iterator.remove();
            edges.remove(edge);
        }
        for (Iterator<Edge<V,E>> iterator = vertex.inEdges.iterator(); iterator.hasNext();){
            Edge<V,E> edge = iterator.next();
            edge.from.outEdges.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }
    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V,E> fromVertex = vertices.get(from);
        if(fromVertex == null)
            return;
        Vertex<V, E> toVeVertex = vertices.get(to);
        if(toVeVertex == null)
            return;
        Edge<V, E> edge = new Edge<>(fromVertex, toVeVertex);
         if(fromVertex.outEdges.remove(edge)){
             toVeVertex.inEdges.remove(edge);
             edges.remove(edge);
         }
    }

    @Override
    public void bfs(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if(beginVertex == null)
            return;

        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        queue.offer(beginVertex);
        visitedVertices.add(beginVertex);

        while (!queue.isEmpty()){
            Vertex<V, E> vertex = queue.poll();
            for (Edge<V, E> edge : vertex.outEdges) {
                if(visitedVertices.contains(edge.to))
                    continue;
                queue.offer(edge.to);
                visitedVertices.add(edge.to);
            }
        }
    }

}
