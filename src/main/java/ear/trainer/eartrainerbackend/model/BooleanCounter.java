package ear.trainer.eartrainerbackend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BooleanCounter {
    private int trueCount;
    private int falseCount;

    public BooleanCounter() {
        falseCount = 0;
        trueCount = 0;
    }

    public void incrementTrue() {
        trueCount++;
    }

    public void incrementFalse() {
        falseCount++;
    }

}
