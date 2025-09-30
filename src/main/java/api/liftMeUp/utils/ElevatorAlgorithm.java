package api.liftMeUp.utils;

import java.util.*;

public class ElevatorAlgorithm {
    static int size = 0;
    static int disk_size = 50;

    /**
     * @param arr       Request sequence = {176, 79, 34, 60, 92, 11, 41, 114}
     * @param head      Initial head position = 0
     * @param direction Direction = left (We are moving from right to left)
     */
    public static void SCAN(ArrayList<Integer> arr, int head, String direction) {
        size = arr.size();
        int seek_count = 0;
        int distance, cur_track;
        Vector<Integer> left = new Vector<>(),
                right = new Vector<>();
        Vector<Integer> seek_sequence = new Vector<>();

        // appending end values
        // which has to be visited
        // before reversing the direction
        if (direction == "left")
            left.add(0);
        else if (direction == "right")
            right.add(disk_size - 1);

        for (int i = 0; i < size; i++) {
            if (arr.get(i) < head)
                left.add(arr.get(i));
            if (arr.get(i) > head)
                right.add(arr.get(i));
        }

        // sorting left and right vectors
        Collections.sort(left);
        Collections.sort(right);

        // run the while loop two times.
        // one by one scanning right
        // and left of the head
        int run = 2;
        while (run-- > 0) {
            if ("left".equals(direction) ) {
                for (int i = left.size() - 1; i >= 0; i--) {
                    cur_track = left.get(i);

                    // appending current track to seek sequence
                    seek_sequence.add(cur_track);

                    // calculate absolute distance
                    distance = Math.abs(cur_track - head);

                    // increase the total count
                    seek_count += distance;

                    // accessed track is now the new head
                    head = cur_track;
                }
                direction = "right";
            } else if ("right".equals(direction)) {
                for (int i = 0; i < right.size(); i++) {
                    cur_track = right.get(i);

                    // appending current track to seek sequence
                    seek_sequence.add(cur_track);

                    // calculate absolute distance
                    distance = Math.abs(cur_track - head);

                    // increase the total count
                    seek_count += distance;

                    // accessed track is now new head
                    head = cur_track;
                }
                direction = "left";
            }
        }

        System.out.print("Total number of seek operations = "
                + seek_count + "\n");

        System.out.print("Seek Sequence is" + "\n");

        for (int i = 0; i < seek_sequence.size(); i++)
        {
            System.out.print(seek_sequence.get(i) + "\n");
        }

         // currentPosition = cur_track; set current floor of the elevator if urn again?
        arr = null; //todo to be rmeoved

    }


}

