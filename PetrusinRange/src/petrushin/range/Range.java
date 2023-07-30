package petrushin.range;

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

    public Range getIntersection(Range range2) {
        if ((range2.from >= from && range2.from <= to) || (from >= range2.from && from <= range2.to)) {
            System.out.printf("Результат пересечения интервалов: [%.1f; %.1f]", Math.max(range2.from, from), Math.min(range2.to, to));

            return new Range(range2.from, range2.to);
        }

        System.out.print("Нет пересечения интервалов");

        return null;
    }

    public Range[] getUnion(Range range2) {
        if (range2.from <= to) {
            System.out.printf("Результат объединения интервалов: [%.1f; %.1f]", Math.min(from, range2.from), Math.max(range2.to, to));

            return new Range[]{new Range(Math.min(from, range2.from), Math.max(range2.to, to))};
        }
        System.out.printf("Результат объединения интервалов: [%.1f; %.1f], [%.1f; %.1f]", from, to, range2.from, range2.to);

        return new Range[]{new Range(from, to), new Range(range2.from, range2.to)};
    }

    public Range[] getDifference(Range range2) {
        if (from < range2.from) {
            if (to < range2.from) {
                System.out.printf("Результат разности интервалов: [%.1f, %.1f]", from, to);

                return new Range[]{new Range(from, to)};
            }

            if (to >= range2.from && to <= range2.to) {
                System.out.printf("Результат разности интервалов: [%.1f, %.1f]", from, range2.from);

                return new Range[]{new Range(from, range2.from)};
            }

            System.out.printf("Результат разности интервалов: [%.1f, %.1f] [%.1f, %.1f]", from, range2.from, range2.to, to);

            return new Range[]{new Range(from, range2.from), new Range(range2.to, to)};
        }

        if (range2.to < from) {
            System.out.printf("Результат разности интервалов: [%.1f, %.1f]", from, to);

            return new Range[]{new Range(from, to)};
        }

        if (range2.to < to) {
            System.out.printf("Результат разности интервалов: [%.1f, %.1f]", range2.to, to);

            return new Range[]{new Range(range2.to, to)};
        }

        System.out.printf("Результат разности интервалов: [  ]");

        return new Range[]{};
    }
}