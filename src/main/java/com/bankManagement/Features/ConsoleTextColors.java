package com.bankManagement.Features;

/**
 * @Description: this is an interface which make the console application
 * more interactive using custom text colors.
 */

public interface ConsoleTextColors {
    String RESET = "\033[0m";
    String CYAN = "\033[0;36m";
    String CYAN_BOLD = "\033[1;36m";
    String PURPLE = "\033[0;35m";
    String PURPLE_BOLD = "\033[1;35m";
    String RED_BOLD = "\033[1;31m";
    String BLUE = "\033[0;34m";
    String BLUE_BOLD = "\033[1;34m";
    String YELLOW = "\033[0;33m";
    String YELLOW_BOLD = "\033[1;33m";
    String GREEN_BOLD = "\033[1;32m";
}
