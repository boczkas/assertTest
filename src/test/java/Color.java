public enum Color {
    // codes for specyfic colors
    
        ANSI_RED("\u001B[31m"),
        ANSI_GREEN("\u001B[32m"),
        ANSI_YELLOW("\u001b[33m"),
        ANSI_BLUE("\u001B[34m"),
        ANSI_PURPLE("\u001B[35m"),
        ANSI_CYAN("\u001B[36m"),
        ANSI_WHITE("\u001b[37m");

    String code;

    Color(String colorCode) {
        code = colorCode;
    }

    @Override
    public String toString() {
        return this.code;
    }
}

