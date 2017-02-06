package travelsafe;

import java.util.Calendar;

/**
 * Created by Dorian on 2/6/2017.
 */
public class Constants {

    //Home insurance
    public static final Long minEstimatedValueHI = 100L;
    public static final Long maxEstimatedValueHI = 1000000L;
    public static final Long minAgeHI = 1L;
    public static final Long maxAgeHI = 100L;
    public static final Double minSurfaceAreaHI = 10D;
    public static final Double maxSurfaceAreaHI = 500D;

    //Car insurance
    public static final Integer minYearOfProductionCI = 1884;
    public static final Integer maxYearOfProductionCI = Calendar.getInstance().get(Calendar.YEAR);

    //Travel insurance
    public static final Long minNumberOfPeopleTI = 1L;
    public static final Long maxNumberOfPeopleTI = 20L;
    public static final Long minDurationTI = 1L;
    public static final Long maxDurationTI = 90L;
    public static final Long minMaxAmountTI = 1000L;
    public static final Long maxMaxAmountTI = 100000L;



}
