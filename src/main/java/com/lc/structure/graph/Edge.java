package com.lc.structure.graph;

import lombok.*;

/**
 * @author gujixian
 * @since 2022/11/25
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Edge<E> {
    public int weight;
    public Node<E> from;
    public Node<E> to;
}
