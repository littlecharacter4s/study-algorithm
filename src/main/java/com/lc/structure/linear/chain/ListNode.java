package com.lc.structure.linear.chain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gujixian
 * @since 2022/11/24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListNode<E> {
    public E value;
    public ListNode<E> next;
    public ListNode<E> prev;

    public ListNode(E value) {
        this.value = value;
    }
}