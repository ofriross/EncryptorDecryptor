package enums;

public enum EBasicEncryptionType {
    SHIFT_UP {
        public String getTypeName() {
            return "Shift up";
        }
    },
    SHIFT_MULTIPLY {
        public String getTypeName() {
            return "Shift multiply";
        }
    },
    XOR {
        public String getTypeName() {
            return "Xor";
        }
    };

    public abstract String getTypeName();
}
