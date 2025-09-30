package api.liftMeUp.utils;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class ElevatorAlgorithm {
    static int size = 0;
    static int disk_size = 50;

    final static int travelTime = 5;

    /**
     * @param arr       Request sequence = {176, 79, 34, 60, 92, 11, 41, 114}
     * @param head      Initial head position = 0
     * @param direction Direction = up (We are moving from down to up)
     */
    public static void SCAN(ArrayList<Integer> arr, int head, String direction) {
        size = arr.size();
        int seek_count = 0;
        int distance, cur_track;
        Vector<Integer> up = new Vector<>(),
                down = new Vector<>();
        Vector<Integer> seek_sequence = new Vector<>();

        // appending end values
        // which has to be visited
        // before reversing the direction
        if (direction == "up")
            up.add(0);
        else if (direction == "down")
            down.add(disk_size - 1);

        for (int i = 0; i < size; i++) {
            if (arr.get(i) < head)
                up.add(arr.get(i));
            if (arr.get(i) > head)
                down.add(arr.get(i));
        }

        // sorting up and down vectors
        Collections.sort(up);
        Collections.sort(down);

        // run the while loop two times.
        // one by one scanning down
        // and up of the head
        int run = 2;
        while (run-- > 0) {
            if ("up".equals(direction) ) {
                for (int i = up.size() - 1; i >= 0; i--) {
                    cur_track = up.get(i);

                    // appending current track to seek sequence
                    seek_sequence.add(cur_track);

                    // calculate absolute distance
                    distance = Math.abs(cur_track - head);

                    // increase the total count
                    seek_count += distance;

                    // accessed track is now the new head
                    head = cur_track;

                //    TimeUnit.SECONDS.sleep(travelTime);
                }
                direction = "down";
            } else if ("down".equals(direction)) {
                for (int i = 0; i < down.size(); i++) {
                    cur_track = down.get(i);

                    // appending current track to seek sequence
                    seek_sequence.add(cur_track);

                    // calculate absolute distance
                    distance = Math.abs(cur_track - head);

                    // increase the total count
                    seek_count += distance;

                    // accessed track is now new head
                    head = cur_track;

                //    TimeUnit.SECONDS.sleep(travelTime);
                }
                direction = "up";
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
     //   arr = null; //todo to be rmeoved

    }


}

