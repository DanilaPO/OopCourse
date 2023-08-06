package ru.academits.petrushin.range;

import java.util.Arrays;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public String toString () {
        double[] range = {from, to};

        return Arrays.toString(range);
    }

    public Range getIntersection(Range range) {
        if (range.from >= from && range.from <= to) {
            double newFrom = Math.max(range.from, from);
            double newTo = Math.min(range.to, to);

            return new Range(newFrom, newTo);
        }

        return null;
    }

    public Range[] getUnion(Range range) {
        if (range.from <= to && range.to >= from) {
            double newFrom = Math.min(from, range.from);
            double newTo = Math.max(range.to, to);

            return new Range[]{new Range(newFrom, newTo)};
        }

        return new Range[]{new Range(from, to), new Range(range.from, range.to)};
    }

    public Range[] getDifference(Range range) {
        if (from < range.from) {
            if (to < range.from) {
                return new Range[]{new Range(from, to)};
            }

            if (to >= range.from && to <= range.to) {
                return new Range[]{new Range(from, range.from)};
            }

            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        }

        if (range.to < from) {
            return new Range[]{new Range(from, to)};
        }

        if (range.to < to) {
            return new Range[]{new Range(range.to, to)};
        }

        return new Range[]{};
    }
}