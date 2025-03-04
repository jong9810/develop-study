package org.example.priorityqueue;

import java.util.Comparator;

public class Heap<E> {

    private final Comparator<? super E> comparator;
    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    private Object[] array;

    // 생성자 Type 1(초기 공간 할당 X)
    public Heap() {
        this(null);
    }

    public Heap(Comparator<? super E> comparator) {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.comparator = comparator;
    }

    // 받은 인덱스의 부모 노드 인덱스를 반환
    private int getParent(int index) {
        return index / 2;
    }

    // 받은 인덱스의 왼쪽 자식 노드 인덱스를 반환
    private int getLeftChild(int index) {
        return index * 2;
    }

    // 받은 인덱스의 오른쪽 자식 노드 인덱스를 반환
    private int getRightChild(int index) {
        return index * 2 + 1;
    }

    // 생성자 Type 2(초기 공간 할당 O)
    public Heap(int capacity) {
        this(capacity, null);
    }

    public Heap(int capacity, Comparator<? super E> comparator) {
        this.array = new Object[capacity];
        this.size = 0;
        this.comparator = comparator;
    }

    /**
     * @param newCapacity 새로운 용적 크기
     */
    private void resize(int newCapacity) {

        // 새로 만들 배열
        Object[] newArray = new Object[newCapacity];

        // 새 배열에 기존에 있던 배열 요소를 모두 복사해준다.
        for (int i = 1; i <= size; i++) {
            newArray[i] = array[i];
        }

        /**
         * 현재 배열은 GC 처리를 위해 null로 처리한 뒤,
         * 새 배열을 연결해준다.
         */
        this.array = null;
        this.array = newArray;
    }

    public void add(E value) {

        // 배열 용적이 꽉 차 있을 경우 용적을 두 배로 늘려준다.
        if (size + 1 == array.length) {
            resize(array.length * 2);
        }

        siftUp(size + 1, value); // 가장 마지막에 추가되는 위치와 넣을 값(타겟)을 넘겨줌.
        size++;
    }

    /**
     * 상향 선별
     * @param idx 추가할 노드의 인덱스
     * @param target 재배치할 노드
     */
    private void siftUp(int idx, E target) {

        // comparator가 존재할 경우 comparator를 인자로 넘겨준다.
        if (comparator != null) {
            siftUpComparator(idx, target, comparator);
        } else {
            siftUpComparable(idx, target);
        }
    }

    // Comparator를 이용한 sift-up
    private void siftUpComparator(int idx, E target, Comparator<? super E> comp) {

        // root 노드보다 클 때까지만 탐색한다.
        while (idx > 1) {
            int parent = getParent(idx);
            Object parentVal = array[parent];
        }
    }

    // Comparable을 이용한 sift-up
    private void siftUpComparable(int idx, E target) {

    }

}
