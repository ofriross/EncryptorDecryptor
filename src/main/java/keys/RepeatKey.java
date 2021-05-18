package keys;

public class RepeatKey extends Key {
    private final int repeatN;
    private Key repeatedKey;

    public RepeatKey(int repeatN, Key repeatedKey) {
        this.repeatN = repeatN;
        this.repeatedKey = repeatedKey;
    }

    public Key getRepeatedKey() {
        return repeatedKey;
    }

    public void setRepeatedKey(Key repeatedKey) {
        this.repeatedKey = repeatedKey;
    }

    @Override
    public String toString() {
        StringBuilder total = new StringBuilder(repeatedKey.toString());
        String base = repeatedKey.toString();
        for (int i = 0; i < repeatN - 1; i++)
            total.append(",").append(base);
        return total.toString();
    }

    @Override
    public int getKeyStrength() {
        return repeatN * repeatedKey.getKeyStrength();
    }

    @Override
    public String getType() {
        return "Repeat of " + repeatN + " times of " + repeatedKey.getType();
    }

    @Override
    public void getNextKey() {
        repeatedKey.getNextKey();
    }
}
