package algorithmsForNeuralNetworks;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 21.09.2017.
 */
public class Matrix {
    List<Integer> matrix;
    public Matrix(Integer ...mas){
        matrix = new ArrayList<Integer>();
        for(Integer item:mas){
            matrix.add(item);
        }
    }
  
    
}
