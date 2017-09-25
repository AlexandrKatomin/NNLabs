package algorithmsForNeuralNetworks.siglal;

import lombok.Getter;

/**
 * Created by Alex on 24.09.2017.
 */
public abstract class Signal {
      @Getter protected int numberForClassA;
      @Getter protected int numberForClassB;

     public abstract int ruleOfHebb(int x, int y);

     public abstract int activationFunction(int s);
}
