package algorithmsForNeuralNetworks;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 21.09.2017.
 */
public class MainForTest {

    static int countItem=9;

    static List<int[]> arrayA= new ArrayList<int[]>();
    static List<int[]> arrayB = new ArrayList<int[]>();




    public static void main(String[] args) {
        int[] imjA = {1,-1,1,1,1,1,-1,-1,1};
        int[] imjB = {1,1,1,1,-1,1,1,-1,1 };

        int[] imjA2 = {1,0,1,1,1,1,0,0,1};
        int[] imjB2 = {1,1,1,1,0,1,1,0,1 };

        arrayA.add(imjA);
        arrayB.add(imjB);



        TrainingNeuron neuron = new TrainingNeuron(countItem,1,TypeOfSignal.bipolar);
        neuron.algHebb( arrayA,arrayB);







    }
}
