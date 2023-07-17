class Range {
    private double from1;
    private double to1;

    public Range(double from1, double to1) {
        this.from1 = from1;
        this.to1 = to1;
    }

    public double getFrom1() {
        return from1;
    }

    public void setFrom1(double from1) {
        this.from1 = from1;
    }

    public double getTo1() {
        return to1;
    }

    public void setTo1(double to1) {
        this.to1 = to1;
    }

    public String getIntersection(double from2, double to2) {
        if (from2 >= from1 && to2 <= to1) {
            StringBuilder newIntervalPoints = new StringBuilder();
            newIntervalPoints.append(from2)
                    .append(", ")
                    .append(to2);

            return newIntervalPoints.toString();
        }

        if (from1 >= from2 && from1 <= to2) {
            StringBuilder newIntervalPoints = new StringBuilder();
            newIntervalPoints
                    .append(from1)
                    .append(", ")
                    .append(to1);

            return newIntervalPoints.toString();
        }

        return null;
    }

    public double[][] getSetsCombining(double from2, double to2) {
        if (from2 >= from1 && from2 <= to1) {
            if (to2 > to1) {
                return new double[][]{{from1, to2}};
            }

            return new double[][]{{from1, to1}};
        }

        if (from1 >= from2 && from1 <= to2) {
            if (to1 > to2) {
                return new double[][]{{from2, to1}};
            }

            return new double[][]{{from2, to2}};
        }

        return new double[][]{{from1, to1}, {from2, to2}};
    }

    public double[][] getSetsDifference(double from2, double to2) {
        if (from2 <= from1 && to2 < to1) {
            if (to2 + 1 == to1) {
                return new double[][]{{to1}};
            }

            return new double[][]{{to2 + 1, to1}};
        }

        if (from2 > from1 && to2 >= to1) {
            if (from2 - 1 == from1) {
                return new double[][]{{from1}};
            }

            return new double[][]{{from1, from2 - 1}};
        }

        if (from2 > from1) {
            if (from2 - 1 == from1 && to2 + 1 == to1) {
                return new double[][]{{from1}, {to1}};
            }

            if (from2 - 1 != from1 && to2 + 1 == to1) {
                return new double[][]{{from1, from2 - 1}, {to1}};
            }

            if (from2 - 1 == from1 && to2 + 1 != to1) {
                return new double[][]{{from1}, {to2 + 1, to1}};
            }

            return new double[][]{{from1, from2 - 1}, {to2 + 1, to1}};
        }

        return new double[][]{{}};
    }
}