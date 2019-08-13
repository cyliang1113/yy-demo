package cn.leo.java.demo.sort;

import java.util.Arrays;

public class MergeSortDemo {
    public static void main(String[] args) {
        int[] src = {4, 3, 12, 6, 8, 1};
        int[] target = mergeSort(src);
        System.out.println(Arrays.toString(src));
        System.out.println(Arrays.toString(target));
    }

    private static int[] mergeSort(int[] src) {
        if (src == null) {
            throw new IllegalArgumentException("");
        }
        if (src.length == 0) {
            return new int[0];
        }

        return sortProcess(src);

    }

    private static int[] sortProcess(int[] src) {
        int srcLen = src.length;

        if (srcLen == 1) {
            return Arrays.copyOf(src, srcLen);
        }
        if (srcLen == 2) {
            if (src[0] <= src[1]) {
                return Arrays.copyOf(src, srcLen);
            } else{
                return new int[]{src[1], src[0]};
            }
        }
        int middleIndex = srcLen / 2;
        int[] frontSegment = sortProcess(Arrays.copyOfRange(src, 0, middleIndex));
        int[] backSegment = sortProcess(Arrays.copyOfRange(src, middleIndex, srcLen));
        int[] target = new int[srcLen];
        int fP = 0;
        int bP = 0;
        for (int i = 0; i < srcLen; i++) {
            if (fP < frontSegment.length && bP < backSegment.length) {
                int fV = frontSegment[fP];
                int bV = backSegment[bP];
                if (fV < bV) {
                    target[i] = fV;
                    fP++;
                } else if (bV < fV) {
                    target[i] = bV;
                    bP++;
                } else {
                    target[i] = fV;
                    i++;
                    target[i] = bV;
                }
            } else if ((fP < frontSegment.length && bP == backSegment.length)) {
                int fV = frontSegment[fP];
                target[i] = fV;
                fP++;
            } else if (fP == frontSegment.length && bP < backSegment.length) {
                int bV = backSegment[bP];
                target[i] = bV;
                bP++;
            } else {
                throw new IllegalStateException("");
            }
        }
        return target;
    }
}
