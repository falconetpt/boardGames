package com.rfgomes.boardgames.maze.domain;

import java.util.Objects;

public class Point implements Comparable<Point> {
    private int x;
    private int y;
    private double weight;

    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public Point(final int x, final int y, final Double weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(final double weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Point o) {
        return Double.compare( weight, o.weight );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash( x, y );
    }
}
