package com.bankManagement.Features;

/**
 * @Description: this class define date format for displaying the date.
 */

import java.time.format.DateTimeFormatter;

public interface DateFormat {
    DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
}
