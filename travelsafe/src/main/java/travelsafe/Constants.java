package travelsafe;

import java.util.Calendar;

/**
 * Created by Dorian on 2/6/2017.
 */
public class Constants {

    //Home insurance
    public static final Long MIN_ESTIMATED_VALUE_HI = 100L;
    public static final Long MAX_ESTIMATED_VALUE_HI = 1000000L;
    public static final Long MIN_AGE_HI = 1800L;
    public static final Long MAX_AGE_HI = (long)(Calendar.getInstance().get(Calendar.YEAR));
    public static final Double MIN_SURFACE_AREA_HI = 10D;
    public static final Double MAX_SURFACE_AREA_HI = 500D;

    //Car insurance
    public static final Integer MIN_YEAR_OF_PRODUCTION_CI = 1800;
    public static final Integer MAX_YEAR_OF_PRODUCTION_CI = Calendar.getInstance().get(Calendar.YEAR);

    //Travel insurance
    public static final Long MIN_NUMBER_OF_PEOPLE_TI = 1L;
    public static final Long MAX_NUMBER_OF_PEOPLE_TI = 20L;
    public static final Long MIN_DURATION_TI = 1L;
    public static final Long MAX_DURATION_TI = 90L;
}
