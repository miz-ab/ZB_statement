package com.zbs.zb.util;

import java.time.LocalDate;

import static com.zbs.zb.constants.Constants.*;

public class Util {
    public int compareDate__(String db_date, String api_date){
        /*
         * 0 => exact the same date
         * 1 => just add statement line, ignore statement header
         * 2 => add both statement header and statement line
         * 3 => invalid date
         * */
        String[] db_date_arr = db_date.split("-");
        String[] api_date_arr = api_date.split("-");

        LocalDate db_local_date_format = LocalDate.of(Integer.parseInt(db_date_arr[0]), Integer.parseInt(db_date_arr[1]), Integer.parseInt(db_date_arr[2]));
        LocalDate api_local_date_format = LocalDate.of(Integer.parseInt(api_date_arr[0]),Integer.parseInt(api_date_arr[1]),Integer.parseInt(api_date_arr[2]));

        if(db_date.equals(api_date)){
            return BOTH_DATES_ARE_EQUAL;
        }

        if(db_local_date_format.getYear() != api_local_date_format.getYear()){
            return INVALID_DATE;
        }

        /*
         * if api day is greater than db day (day only)
         * */

        if((api_local_date_format.getMonthValue() == db_local_date_format.getMonthValue()) && (api_local_date_format.getDayOfMonth() > db_local_date_format.getDayOfMonth())){
            return DAY_DIFFERENCE;
        }

        /*
         * if api date is one month after db date and api day of the month is start day of the month
         * create new statement header record
         *
         * add both statement interface header and statement header line
         * */
        if((db_local_date_format.plusMonths(1).getMonth() == api_local_date_format.getMonth()) &&
                (api_local_date_format.getDayOfMonth() == 1)){
            return DAY_MONTH_DIFFERENCE;
        }

        return INVALID_DATE;
    }
}
